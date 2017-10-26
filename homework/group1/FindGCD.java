package group1;
/*
 * Authors: Harry Chen
 */
import stdlib.StdIn;
import stdlib.StdOut;

public class FindGCD {
	public static int getGCD(int a, int b) { 
		if (a % b == 0) { 
			return b; 
		}
		return getGCD(b, a % b); 
		} 
	public static void main(String[] args) {
		StdOut.print("Enter the First Number: ");
		int n = Integer.parseInt(StdIn.readLine());
		StdOut.print("Enter the Second Number: ");
		int k = Integer.parseInt(StdIn.readLine());
		StdOut.printf("The Greatest Common Divisor Between "+ n +" and " + k +" is "+getGCD(n,k));
	}
}






