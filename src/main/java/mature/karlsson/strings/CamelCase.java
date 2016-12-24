package mature.karlsson.strings;

import java.util.Scanner;

public class CamelCase {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        in.close();
        String[] matches = s.split("[A-Z]");
        System.out.println(matches.length);
    }
	
}
