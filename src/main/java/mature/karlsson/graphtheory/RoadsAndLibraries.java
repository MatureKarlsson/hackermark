package mature.karlsson.graphtheory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * <a href=https://www.hackerrank.com/challenges/torque-and-development/problem>Roads and Libraries challenge</a>
 */
public class RoadsAndLibraries {

    private static class City {

        HashSet<City> linkedCities;
        long num;
        boolean visited;

        City(int num) {
            this.num = num;
            this.linkedCities = new HashSet<>();
            this.visited = false;
        }

        @Override
        public String toString() {
            return "City{" +
                    "num=" + num +
                    ", visited=" + visited +
                    '}';
        }

    }

    //q - number of queries
    //n - number of cities
    //m - number of roads
    //clib - cost of building a library
    //croad - cost of repairing a road
    private long q, n, m, clib, croad = 0;

    private List<City> cities = new ArrayList<>();
    private Scanner in = new Scanner(System.in);
    private Queue<City> queue = new ArrayDeque<>();

    public static void main(String... args) throws FileNotFoundException {
        if (args.length > 0) {
            //it is expected to be a file name to be used as STDIN, manual input otherwise
            System.setIn(new FileInputStream(args[0]));
        }

        new RoadsAndLibraries().process();
    }

    private void process() {
        q = in.nextInt();
        for (int qi=1; qi <= q; qi++) {
            initQuery();

            long cost = 0;
            if (croad > clib) {
                //if road is more expensive than library then build a library in each city
                cost = clib*n;
            } else {
                //build library per connected segment and repair roads
                for (City city : cities) {
                    if (!city.visited) {
                        queue.clear();
                        queue.offer(city);
                        city.visited = true;
                        int citiesInSegment = 1;

                        City vCity;
                        while ((vCity = queue.poll()) != null) {
                            for (City linkedCity : vCity.linkedCities) {
                                if (!linkedCity.visited) {
                                    citiesInSegment++;
                                    linkedCity.visited = true;
                                    queue.offer(linkedCity);
                                }
                            }
                        }

                        cost += clib + (citiesInSegment - 1) * croad;
                    }
                }
            }

            System.out.println(cost);
        }
    }

    private void initQuery() {
        n = in.nextInt(); //number of cities
        m = in.nextInt(); //number of roads
        clib = in.nextInt(); //cost of a library
        croad = in.nextInt(); //cost of a road

        cities.clear();
        for (int i = 1; i <= n; i++) {
            cities.add(new City(i));
        }

        for (int i = 1; i <= m; i++) {
            int ui = in.nextInt();
            int vi = in.nextInt();
            City u = cities.get(ui-1);
            City v = cities.get(vi-1);
            u.linkedCities.add(v);
            v.linkedCities.add(u);
        }
    }


}
