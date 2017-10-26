package group5;

import stdlib.StdOut;

public class test {

	public static void main(String[] args) {
		XMLToken x = new XMLToken("<html>");
		StdOut.println(x.token);
		StdOut.println(x.tokenlist[0]);
		StdOut.println(x.isOpeningTag());
	}

}
