package mature.karlsson.graphtheory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class RoadsAndLibraries {

    private int q, n, m, clib, croad = 0;
    private List<Set<Integer>> graph;

    private boolean[] visitedCities;

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

        int connectedComponentsNum = 0;
        for (int qi=1; qi<=q; qi++) {
            initQuery(in);
            for (int ci = 0; ci < n; ci++) {
                //if a city has not been visited yet then build a connected component from it
                dfsTraversal(ci);
                connectedComponentsNum++;
            }

            //if a library costs less than a road then just build a library in each of the graph's connected components
            //otherwise build a single library (in one of the segments) and link the remaining with a road
            int cost = clib <= croad ?
                    connectedComponentsNum*clib :
                    clib + (connectedComponentsNum-1)*croad;
            System.out.println(cost);
        }
    }

    private void dfsTraversal(int city) {
        if (!visitedCities[city]) {
            visitedCities[city] = true;
            for (Integer adjCity : graph.get(city)) {
                dfsTraversal(adjCity);
            }
        }
    }

    private void initQuery(Scanner in) {
        n = in.nextInt(); //number of cities
        m = in.nextInt(); //number of roads
        clib = in.nextInt(); //cost of a library
        croad = in.nextInt(); //cost of a road

        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        for (int i = 1; i <= m; i++) {
            int ui = in.nextInt();
            int vi = in.nextInt();
            graph.get(ui-1).add(vi-1);
            graph.get(vi-1).add(ui-1);
        }

        visitedCities = new boolean[n];
    }

}
