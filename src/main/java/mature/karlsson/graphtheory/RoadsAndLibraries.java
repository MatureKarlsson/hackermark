package mature.karlsson.graphtheory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class RoadsAndLibraries {

    private int q, n, m, clib, croad = 0;
    //every integer in the array is a city (adjMatrix[0] is city #1)
    //every bit in an integer is accessibility from the city to another one (bit 0 set to 1 means city #1 is accessible from this one)
    private int[] adjMatrix;
    //every bit in the integer means city has been visited or not (bit 0 set to 1 means city #1 has been visited)
    private int visitedCities;

    public static void main(String... args) throws FileNotFoundException {
        if (args.length > 0) {
            //it is expected to be a file name to be used as STDIN, manual input otherwise
            System.setIn(new FileInputStream(args[0]));
        }

        new RoadsAndLibraries().process();
    }

    private void process() {
        Scanner in = new Scanner(System.in);
        q = in.nextInt();
        for (int i=1; i<=q; i++) {
            initQuery(in);
            long theCost;

            if (clib <= croad) {
                //just build a library in each city if libraries are cheaper then roads
                theCost = (long)clib * (long)n;
            } else {
                Collection<Integer> connectedGraphs = new ArrayList<>();
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


    private int bfs(int cstart) {
        Queue<Integer> queue = new ArrayDeque<>(n);
        queue.add(cstart);
        addVisitedCity(cstart);
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

    private void addVisitedCity(int cnum) {
        visitedCities = visitedCities | 1 << (cnum - 1);
    }

    private void initQuery(Scanner in) {
        n = in.nextInt(); //number of cities
        m = in.nextInt(); //number of roads
        clib = in.nextInt(); //cost of a library
        croad = in.nextInt(); //cost of a road

        adjMatrix = new int[n];
        for (int i = 1; i <= m; i++) {
            int ui = in.nextInt();
            int vi = in.nextInt();
            adjMatrix[ui] = adjMatrix[ui] | 1 << (vi - 1);
            adjMatrix[vi] = adjMatrix[vi] | 1 << (vi - 1);
        }

        visitedCities = 0;
    }


}
