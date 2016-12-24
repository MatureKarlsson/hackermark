package mature.karlsson.strings;

import java.util.Scanner;

public class SuperReducedString {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String origin = in.next();
        in.close();
        
        String result = origin;
        do{
        	origin = result;
        	result = origin.replaceAll("(\\w)\\1", "");
        } while (!result.equals(origin));
        
        System.out.println("".equals(result)?"Empty String":result);
    }
}
