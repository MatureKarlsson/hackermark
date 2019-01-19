package mature.karlsson.graphtheory;

import java.io.*;
import java.util.*;

public class SherlockAndCost {

    // Complete the cost function below.
    static int cost(int[] b) {
        int[] a = new int[b.length];
        buildA(a, b, 0, b.length-1);
        return calculateCost(a);
    }

    static void buildA(int[] a, int[] b, int bFirst, int bLast) {
        int ind = findMaxInd(b, bFirst, bLast);
        setA(a, ind, b[ind]);
        setB(b, ind);

        if ((bLast - bFirst) > 1) {
            buildA(a, b, bFirst, ind-1);
            buildA(a, b, ind+1, bLast);
        }
    }

    /**
     * Sets as follows
     * <ul>
     *     <li>b[ind] = 0</li>
     *     <li>b[ind+1] = 0</li>
     *     <li>b[ind-1] = 0</li>
     * </ul>
     */
    static void setB(int[] b, int ind) {
        safeSet(b, ind, 0, false);
        safeSet(b, ind+1, 0, false);
        safeSet(b, ind-1, 0, false);
    }

    /**
     * Sets as follows
     * <ul>
     *     <li>a[ind] = b[ind]</li>
     *     <li>a[ind+1] = 1</li>
     *     <li>a[ind-1] = 1</li>
     * </ul>
     */
    static void setA(int[] a, int ind, int value) {
        safeSet(a, ind, value, true);
        safeSet(a, ind+1, 1, true);
        safeSet(a, ind-1, 1, true);
    }




    static int calculateCost(int[] a) {
        int cost = 0;
        for (int i = 1; i < a.length; i++) {
            cost += Math.abs(a[i] - a[i-1]);
        }
        return cost;
    }

    static int findMaxInd(int[] b, int first, int last) {
        int max = 0;
        int ind = 0;

        for (int i = first; i <= last; i++) {
            if (b[i] > max) {
                max = b[i];
                ind = i;
            }
        }
        return ind;
    }

    static void safeSet(int[] arr, int ind, int value, boolean ifZeroOnly) {
        if (ind >= 0 && ind < arr.length && (!ifZeroOnly || arr[ind] == 0)) {
            arr[ind] = value;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] B = new int[n];

            String[] BItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int BItem = Integer.parseInt(BItems[i]);
                B[i] = BItem;
            }

            int result = cost(B);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}