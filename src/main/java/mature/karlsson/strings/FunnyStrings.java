package mature.karlsson.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FunnyStrings {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		List<String> testCases = new ArrayList<>(t);
		for (int i=1; i<=t; i++)
			testCases.add(in.next());
		in.close();
		
		for (String s : testCases){
			boolean isFunny = true;
			for (int i=1; isFunny&&(i<=Math.ceil(s.length()/2)); i++){
//				System.out.println(s.charAt(i)+" - "+s.charAt(i-1));
//				System.out.println(s.charAt(s.length()-1-i)+" - "+s.charAt(s.length()-i));
				int ldif = Math.abs(s.charAt(i)-s.charAt(i-1));
				int rdif = Math.abs(s.charAt(s.length()-1-i)-s.charAt(s.length()-i));
//				System.out.println("ldif = "+ldif+". rdif = "+rdif);
				isFunny = ldif==rdif;
			}
			System.out.println(isFunny?"Funny":"Not Funny");
		}
	}

}
