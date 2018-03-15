package mature.karlsson.dynamicprogramming;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChangeProblem {

    private static int[] coins;
    private static long[][] changes;

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        coins = new int[m];
        for (int i = 0; i < m; i++) {
            coins[i] = in.nextInt();
        }
        Arrays.sort(coins);

        changes = new long[n+1][m];
        for (int i = 0; i < changes.length; i++)
            for (int j = 0; j < changes[i].length; j++)
                changes[i][j] = -1;

        for (int i = 0; i < changes.length; i++) {
            for (int j = 1; j <= changes[i].length; j++) {
                changes[i][j-1] = changesCount(i, j);
            }
        }

        System.out.println(changes[n][m-1]);
    }

    private static long changesCount(int sum, int numCoins) {
        if (sum < 0 || numCoins < 1) return 0;
        if (sum == 0) return 1;
        if (changes[sum][numCoins-1] > -1) return changes[sum][numCoins-1];

        return changesCount(sum, numCoins - 1) + changesCount(sum - coins[numCoins-1], numCoins);
    }

}
