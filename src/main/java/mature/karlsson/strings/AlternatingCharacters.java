package mature.karlsson.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AlternatingCharacters {

//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int t = in.nextInt();
//		List<String> testCases = new ArrayList<>(t);
//		for (; t>0; t--)
//			testCases.add(in.next());
//			
//		
//		for (String s : testCases){
//			List<Character> sList = new ArrayList<>();
//			for (char c : s.toCharArray())
//				sList.add(c);
//			
//			int numRemoved = 0;
//			for (int i=1; i<sList.size(); i++)
//				if (sList.get(i).equals(sList.get(i-1))){
//					sList.remove(i);
//					numRemoved++;
//					i--;
//				}
//			System.out.println(numRemoved);
//		}
//	}
	
	public static void main (String[] args){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		List<String> testCases = new ArrayList<>(t);
		for (; t>0; t--)
			testCases.add(in.next());
		
		for (String testCase : testCases){
			String s = testCase.replaceAll("(\\w)\\1+", "$1");
			System.out.println(testCase.length()-s.length());
		}
	}

}
