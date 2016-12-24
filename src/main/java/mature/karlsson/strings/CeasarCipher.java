package mature.karlsson.strings;

import java.util.Scanner;

public class CeasarCipher {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int k = in.nextInt();
        in.close();
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++){
        	sb.append(rotate(s.charAt(i), k));
        }
        
        System.out.println(sb.toString());
    }
    
	private static char rotate(char c, int k){
		char result = c;
		int cCode = (byte)c;
		
		//if ASCII letter (a-z, A-Z)
		if ((cCode>=97)&&(cCode<=122) || ((cCode>=65)&&(cCode<=90))){
			int base = (cCode>=97?97:65); //lower case letters ASCII codes start at 97. Upper case letters ASCII codes start at 65
			
			int baseCode = (cCode-base+k%26)%26; //26 letters in English alphabet
			
			result = (char)(base+baseCode);
		}
		return result;
	}
	
}
