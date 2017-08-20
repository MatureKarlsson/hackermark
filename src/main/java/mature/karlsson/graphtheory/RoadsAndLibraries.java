package mature.karlsson.graphtheory;

import java.util.Scanner;

public class RoadsAndLibraries {

    private static int q, n, m, clib, croad = 0;

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);
        q = in.nextInt();
        for (int i=1; i<=q; i++) {
            initHackerLand(in);

        }
    }

    private static void initHackerLand(Scanner in) {
        n = in.nextInt(); //number of cities
        m = in.nextInt(); //number of roads
        clib = in.nextInt(); //cost of a library
        croad = in.nextInt(); //cost of a road


    }


}
