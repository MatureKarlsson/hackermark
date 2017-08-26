package mature.karlsson.graphtheory;

import java.util.*;

public class RoadsAndLibraries {

    private static int q, n, m, clib, croad = 0;
    private static boolean[][] adjMatrix;
    private static boolean[] visitedCities;
    private static Queue<Integer> queue;

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);
        q = in.nextInt();
        for (int i=1; i<=q; i++) {
            initHackerLand(in);

            Collection<Integer> connectedGraphs = new ArrayList<>();
            //for every not visited city
            for (int cnum = 0; cnum < n; cnum++)
                if (!visitedCities[cnum])
                    connectedGraphs.add(bfs(cnum));
        }
    }

    private static int bfs(int cstart) {
        queue = new ArrayDeque<>(n);
        queue.add(cstart);
        int ctot = 0;

        while (queue.peek() != null) {
            int cnum = queue.remove();
            ctot++;
            for (int i = 0; i < n; i++)
                if (adjMatrix[cnum][i] && !visitedCities[i]) {
                    queue.add(i);
                    visitedCities[i] = true;
                }
        }

        return ctot;
    }

    private static void initHackerLand(Scanner in) {
        n = in.nextInt(); //number of cities
        m = in.nextInt(); //number of roads
        clib = in.nextInt(); //cost of a library
        croad = in.nextInt(); //cost of a road

        //initialize road matrix
        adjMatrix = new boolean[n][n];
        for (int i = 1; i <= m; i++) {
            int ui = in.nextInt();
            int vi = in.nextInt();
            adjMatrix[ui][vi] = true;
            adjMatrix[vi][ui] = true;
        }
        //initialize visitedCities
        visitedCities = new boolean[n];
    }


}
