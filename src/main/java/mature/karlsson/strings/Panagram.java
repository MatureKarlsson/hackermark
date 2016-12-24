package mature.karlsson.strings;

import java.util.Scanner;

public class Panagram {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().toLowerCase();
        in.close();
        
        boolean isPanagram = true;
        for (int i = 0; isPanagram && (i < ALPHABET.length()); i++){
        	isPanagram = input.contains(ALPHABET.substring(i, i+1));
        }
        
        System.out.println(isPanagram?"pangram":"not pangram");
    }
	
}
