package mature.karlsson.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Gemstones {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		List<String> rocks = new ArrayList<>();
		for (int i=1; i<=n; i++)
			rocks.add(in.next());
		in.close();
		
		Map<Character, List<Boolean>> elements = new HashMap<>();
		for (int i=0; i<rocks.size(); i++){
			String rock = rocks.get(i);
			for (char c : rock.toCharArray()){
				//initialize the character record if character is seen for the first time 
				if (!elements.containsKey(c)){
					List<Boolean> l = new ArrayList<>(n);
					for (int k=0; k<n; k++)
						l.add(false);
					elements.put(c, l);
				}
				elements.get(c).set(i, true);
			}
		}	
		
		int gemsCount = 0;
		for (List<Boolean> elem : elements.values()){
			boolean isGem = true;
			for (boolean b : elem)
				isGem &= b;
			if (isGem)
				gemsCount++;
		}
			
		
		System.out.println(gemsCount);
	}

}
