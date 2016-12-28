package mature.karlsson.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwoCharacters {

public static void main(String[] args){
	Scanner in = new Scanner(System.in);
	int len = in.nextInt();
	String s = in.next();
	in.close();
	
	//empty string and single character cannot be converted to a matching string
	if (len<=1){
		System.out.println(0);
		System.exit(0);
	}
	
	List<Character> chars = new ArrayList<>();
	for (int i=0; i<s.length();i++){
		char c = s.charAt(i);
		if (!chars.contains(c))
			chars.add(c);
	}
	
	int max = 0;
	int i = 1;
	for (Character firstChar : chars){
		Iterator<Character> iterIn = chars.iterator();
		//skipping i first entries as these combinations have been checked already in previous cycles
		for (int j=0; j<i; j++, iterIn.next());
		
		while (iterIn.hasNext()){
			Character secondChar = iterIn.next();
			String candidate = s.replaceAll("[^"+firstChar+secondChar+"]", ""); //the regex should be like [^ab]
			//checking the candidate has no duplicated values, it can be abab, but not aaba or abba, etc.
			if (!Pattern.compile("(\\w)\\1{1,}").matcher(candidate).find())
				max = max>=candidate.length()?max:candidate.length();
		}
		i++;
	}
	
	System.out.println(max);
}
	
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int len = in.nextInt();
//		String s = in.next();
//		in.close();
//		
//		//empty string and single character cannot be converted to a matching string
//		if ((len<=1) || (s.length()<=1)){
//			System.out.println(0);
//			System.exit(0);
//		}
//		
//		//calculating occurrences per character
//		Map<Character, Integer> occurrences = new HashMap<>();
//		for (int i=0; i<s.length(); i++){
//			char c = s.charAt(i);
//			if (!occurrences.containsKey(c))
//				occurrences.put(c, countOccurences(c, s));
//		}
//		System.out.println(occurrences);
//		
//		//sorting characters by the number of occurrences
//		List <Entry<Character, Integer>> sortedOccurrences = new ArrayList<>(occurrences.entrySet());
//		Collections.sort(sortedOccurrences, new Comparator<Entry<Character, Integer>>(){
//			@Override
//			public int compare(Entry<Character, Integer> e1, Entry<Character, Integer> e2){
//				//descending order is needed
//				return e2.getValue().compareTo(e1.getValue());
//			}
//		});
//		System.out.println(sortedOccurrences);
//
//		int i = 1;
//		for (Iterator<Entry<Character, Integer>> iterOut = sortedOccurrences.iterator(); iterOut.hasNext();){
//			Character firstChar = iterOut.next().getKey();
//			
//			Iterator<Entry<Character, Integer>> iterIn = sortedOccurrences.iterator();
//			//skipping i first entries as these combinations have been checked already in previous cycles
//			for (int j=0; j<i; j++, iterIn.next());
//			
//			while (iterIn.hasNext()){
//				Character secondChar = iterIn.next().getKey();
//				String candidate = s.replaceAll("[^"+firstChar+secondChar+"]", ""); //the regex should be like [^ab]
//				System.out.println(firstChar+" "+secondChar+" "+candidate.length()+" "+candidate);
//				//checking the candidate has no duplicated values, it can be abab, but not aaba or abba, etc.
//				if (!Pattern.compile("(\\w)\\1{1,}").matcher(candidate).find()){
//					System.out.println(candidate.length());
//					System.exit(0);
//				}
//			}
//			i++;
//		}
//		
//		System.out.println(0);
//	}
	
	//counts occurrences of a char c in string s
	private static int countOccurences(char c, String s){
		return s.replaceAll("[^"+c+"]", "").length();
	}
	
	private static String removeDuplicates(String origin){
		//word character followed by the same character 1 or more times
		Pattern pattern = Pattern.compile("(\\w)\\1{1,}");
		
		String result = origin;
		do{
			origin = result;
			Matcher matcher = pattern.matcher(origin);
			
			//building regex to match all duplicated characters
			StringBuffer regex = new StringBuffer("[");
			while (matcher.find())
				regex.append(matcher.group(1));
			regex.append("]");
			System.out.println(regex.toString());
			
			//regex.length == 2 means it equals to an empty character class ("[]")
			if (regex.length() > 2)
				result = origin.replaceAll(regex.toString(), "");
			
		} while (!result.equals(origin));
		
		return origin;
	}

}
