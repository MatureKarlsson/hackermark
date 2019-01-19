package mature.karlsson.graphtheory;

import java.io.*;
import java.util.*;

public class SherlockAndCost {

    // Complete the cost function below.
    static int cost(int[] b) {
        int[] result = {0, 0};
        for (int i = 0; i < b.length; i++) {
            result = calculateSubCost(b, i, result);
        }
        return Math.max(result[0], result[1]);
    }

    static int[] calculateSubCost(int[] ar, int idx, int[] prevResult) {
        final int LAST_IS_VALUE = 0;
        final int LAST_IS_ONE = 1;

        int[] result = {0, 0};

        if (idx <= 0)
            return result;

        if (idx == 1) {
            result[LAST_IS_VALUE] = ar[idx] - 1;
            result[LAST_IS_ONE] = ar[idx-1] - 1;
            return result;
        }

        int oneValue = ar[idx] - 1;
        int valueOne = ar[idx-1] - 1;
        int valueValue = Math.abs(ar[idx] - ar[idx-1]);
        result[LAST_IS_VALUE] = Math.max(
                prevResult[LAST_IS_ONE] + oneValue,
                prevResult[LAST_IS_VALUE] + valueValue
        );
        result[LAST_IS_ONE] = prevResult[LAST_IS_VALUE] + valueOne;

        return result;
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