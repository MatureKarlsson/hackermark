package mature.karlsson.strings;

import java.util.Scanner;

public class MarsExploration {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String actual = in.next();
		in.close();
		
		StringBuffer expected = new StringBuffer();
		while (expected.length()<actual.length())
			expected.append("SOS");
		
		int jammed = 0;
		for (int i=0; i<actual.length(); i++)
			if (expected.charAt(i)!=actual.charAt(i))
				jammed++;
		
		System.out.println(jammed);
	}

}
