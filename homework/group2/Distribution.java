package group2;

import stdlib.StdIn;
import stdlib.StdOut;

public class Distribution {
	public static double computeAverage(double[] numbers) {
		double sum=0.0;
		for (double number:numbers) {
			sum += number;
		}
		double average =(double) sum/numbers.length;
		return average;
	}

	public static double computeStd(double[] numbers) {
		double average = computeAverage(numbers);
		double sum =0.0;
		for (double number:numbers) {
			sum += Math.pow(number-average,2);
		}
		double std = Math.sqrt(sum/numbers.length);
		return std;
	}
	
	public static double computepercentage(double[] numbers,int distance) {
		int count =0;
		double std = computeStd(numbers);
		double mean =computeAverage(numbers);
		for (double number :numbers) {
			if(number > mean-distance*std && number < mean+distance*std ) {
				count ++;
			}
		}
		double percentage = (double)100* count/numbers.length;
		return percentage;
	}
	
	public static void main(String[] agrs) {
		StdIn.fromFile("data/normaldistribution.txt");
		double[] values = StdIn.readAllDoubles();
		double mean = computeAverage(values);
		double std = computeStd(values);
		double firstquantile = computepercentage(values,1);
		double secondquantile = computepercentage(values,2);
		double thirdquantile = computepercentage(values,3);
		StdOut.println("Statistics on the file data/normaldistribution.txt");
		StdOut.println("The mean is " + mean);
		StdOut.println("The standard deviation is " + std);
		StdOut.println("Percentage of values 1 SD away from mean: "+ firstquantile);
		StdOut.println("Percentage of values 2 SD away from mean: "+ secondquantile);
		StdOut.println("Percentage of values 3 SD away from mean: "+ thirdquantile);
	}
}
