package mature.karlsson.graphtheory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class RoadsAndLibraries {

    private static int q, n, m, clib, croad = 0;
    private static boolean[][] adjMatrix;
    private static boolean[] visitedCities;

    public static void main(String... args) throws FileNotFoundException {
//        int a = 100000;
//        int b = 100000;
//        long c = a*b;
//        System.out.println(c);

        if (args.length > 0) {//it is expected to be a file name to be used as STDIN, manual input otherwise
            System.setIn(new FileInputStream(args[0]));
        }

        adjMatrix = new boolean[100000][100000];
        visitedCities = new boolean[100000];
        Scanner in = new Scanner(System.in);
        q = in.nextInt();
        for (int i=1; i<=q; i++) {
            initHackerLand(in);
            long theCost;

            if (clib <= croad) //it is cheaper to build a library in every city if road cost is not lower
                theCost = (long)clib*(long)n;
            else { //one library per segment, and roads to connect cities
                Collection<Integer> connectedGraphs = new ArrayList<>();
                //for every not visited city
                for (int cnum = 0; cnum < n; cnum++)
                    if (!visitedCities[cnum])
                        connectedGraphs.add(bfs(cnum));
                theCost = connectedGraphs.size()*clib; //one library per segment
                for (int ccnt : connectedGraphs)
                    theCost += (long)(ccnt-1)*(long)croad; //road for every city in segment without library
            }

            System.out.println(theCost);
        }
    }

    private static int bfs(int cstart) {
        Queue<Integer> queue = new ArrayDeque<>(n);
        queue.add(cstart);
        visitedCities[cstart] = true;
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
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                adjMatrix[i][j] = false;
        //fill in road matrix
        for (int i = 1; i <= m; i++) {
            int ui = in.nextInt();
            int vi = in.nextInt();
            adjMatrix[ui - 1][vi - 1] = true;
            adjMatrix[vi - 1][ui - 1] = true;
        }
        //initialize visitedCities
        for (int i = 0; i < n; i++)
            visitedCities[i] = false;

    }


}
