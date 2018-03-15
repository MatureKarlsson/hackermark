package mature.karlsson.dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class Equal {

    private static int[] dist;
    private static int[] give = {1, 3, 5};

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();

        for (int tnum = 1; tnum <= t; tnum++) {
            int n = in.nextInt();
            dist = new int[n];
            for (int i = 0; i < dist.length; i++) {
                dist[i] = in.nextInt();
            }

            int bestRate = 0;
            int[] bestOption = new int[dist.length];
            for (int exclude = 0; exclude < dist.length; exclude++) {
                for (int append : give) {
                    int[] option = new int[dist.length];
                    for (int i = 0; i < dist.length; i++) {
                        option[i] = i == exclude ? dist[i] : dist[i] + append;
                    }

                }
            }


        }
    }

    private static int maxSimilarValues(int[] arr) {
        Arrays.sort(arr);
        int val = -1;
        int bestResult = 0;
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (val == arr[i]) {
                result++;
            } else {
                bestResult = bestResult >= result ? bestResult : result;
                result = 1;
                val = arr[i];
            }
        }

        return bestResult;
    }

//            for (int exclude = 0; exclude < dist.length; exclude++) {
//                for (int o = 0; o < give.length; o++) {
//                    System.out.printf("Excluding %d. Adding %d. ", exclude+1, give[o]);
//                    for (int i = 0; i < dist.length; i++) {
//                        if (i == exclude) {
//                            System.out.printf("%2d(%d, %d) ", dist[i], dist[i]%3, dist[i]%5);
//                        } else {
//                            int newVal = dist[i]+give[o];
//                            System.out.printf("%2d(%d, %d) ", newVal, newVal%3, newVal%5);
//                        }
//                    }
//                    System.out.println();
//                }
//            }
//            System.out.print("\n\n\n");



//            for (int exclude = 0; exclude < dist.length; exclude++) {
//                for (int o = 0; o < give.length; o++) {
//                    System.out.printf("Excluding %d. Adding %d. ", exclude, give[o]);
//                    int mod3 = 0;
//                    int mod5 = 0;
//                    for (int i = 0; i < dist.length; i++) {
//                        if (i == exclude) {
//                            System.out.printf("%3d*", dist[i]);
//                            mod3 += dist[i]%3;
//                            mod5 += dist[i]%5;
//                        } else {
//                            int newVal = dist[i]+give[o];
//                            System.out.printf("%3d ", newVal);
//                            mod3 += newVal%3;
//                            mod5 += newVal%5;
//                        }
//                    }
//                    System.out.printf(". mod3 = %2d, mod5 = %2d\n", mod3, mod5);
//                }
//            }
//            System.out.print("\n\n\n");
//        }
//    }

}
