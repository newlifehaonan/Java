package group2;

import java.util.Arrays;

import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class WordStatistics {
	public static int[] countwordlength(String[] file) {
		int[] b =new int[file.length];
			for(int i=0;i< file.length;i++) {
				b[i]=file[i].length();
			}
		return b;
	}
	
	public static double computemedian(int[] values) {
		Arrays.sort(values);
		double median = 0;
		int middle = 0;
		if (values.length%2==0) {
			middle = values.length/2;
			median =(double) (values[middle] + values[middle-1])/2;
		}
		else {
			middle = (values.length-1)/2;
			median = values[middle];
		}
		return median;
	}
	
	public static double computemean(int[] values) {
		int sum=0;
		for (int value : values) {
			sum+=value;
		}
		double mean = (double) sum/values.length;
		return mean;
	}
	
	public static String[] userinput() {
		StdOut.print("Please enter the pathname or URL of the book file: ");
		String textSource = StdIn.readLine();

        final In in = new In(textSource);
        if (!in.exists ()) {
        	StdOut.println("Unable to open the text source " + textSource);
            System.exit (1);
        }
        final String bookText = in.readAll();
        String[] bookWords = bookText.split("\\s+");
        return bookWords;
	}
	
	public static void main(String[] args) {
		String[] URL =userinput();
		int[] wordlengtharray =countwordlength(URL);
		double median =computemedian(wordlengtharray);
		double mean = computemean(wordlengtharray);
		StdOut.println("The average length of the words in the file is " + mean);
		StdOut.println("The median length of the words in the file is " + median);
	}
}

