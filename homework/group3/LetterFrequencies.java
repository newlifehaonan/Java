package group3;
import java.text.NumberFormat;

import stdlib.*;


public class LetterFrequencies {
	public static void main(String[] args) {
		StdOut.print("Enter URL:");
		String textsource = StdIn.readLine();
		final In input = new In(textsource);
		String letter = input.readAll();
		
		char[] ch=letter.toCharArray();
		int count,j=1;
        char var='a';
 
        while(j<=26)
        {
           NumberFormat numberFormat1 = NumberFormat.getNumberInstance();  
           count=0;
                for(int i=0; i<letter.length(); i++)
                {
                    if(ch[i]==var || ch[i]==var-32)
                    {
                        count++;
                    }
                }
                StdOut.println(var+ "   " + numberFormat1.format(count));
                var++;
                j++;
        }
	}
}
