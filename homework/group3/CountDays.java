package group3;

import stdlib.StdIn;
import stdlib.StdOut;

public class CountDays {
	public static void main(String[] args) {
		StdOut.print("Please enter the first date: ");
		String input1 = StdIn.readLine();
		Group3Date first = new Group3Date(input1);
		StdOut.print("Please enter the second date: ");
		String input2 = StdIn.readLine();
		Group3Date second = new Group3Date(input2);
		
		StdOut.print(first.countDaysUntil(second));
		
	}
}
