package group3;

import stdlib.StdOut;

public class testgroup3date {

	public static void main(String[] args) {
		Group3Date startDate = new Group3Date("10/1/2017");
		Group3Date endDate = new Group3Date("11/1/2017");
		int count = startDate.countDaysUntil(endDate);
		StdOut.println(count);
		count = endDate.countDaysUntil(startDate);
		StdOut.println(count);
	}
}
