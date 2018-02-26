package HW5;

import java.util.Random;

import algs34.LinearProbingHashST;
import algs34.SeparateChainingHashST;
import stdlib.StdOut;
import stdlib.Stopwatch;

public class TestFlight {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSepareteChain(300000);
		testlinearprobing(300000);
		
	}
	
	
	//This function can generate both airport and flight name, by convention airport name is 3 Capitalized letter, flight name is 2 Capitalized letter
	private static String generatename(int size) {
		char[] namearray = new char[size];
        StringBuilder Flight = new StringBuilder();
		Random r = new Random();
		
		int max=90;
        int min=65;
        
        for(int i =0; i<size; i++) {
        		namearray[i] = (char) ((char) r.nextInt(max)%(max-min+1) + min);
        		Flight.append(namearray[i]);
        }
       
		String X =Flight.toString();
		return X;
		
	}
	
	//Duplicated name in array ?
	private static boolean contain(String x, String[] y) {
		if(y.length == 0) return false;
		for(int i = 0; i< y.length; i++) {
			if(y[i] == null) continue;
			if(y[i].equals(x)) {
				return true;
			}
		}
		return false;
	}
	
	//This function generate name pool for both airport and flight  
	private static String[] generatenamepool(int poolsize, int namesize) {
		String[] namepool = new String[poolsize];
		for(int i =0; i < poolsize; i++) {
			String name = generatename(namesize);
			
			if(contain(name,namepool)) {
				name = generatename(namesize);
			}
			
			namepool[i] = name;
		}
		return namepool;
	}

	//This function generate number in user specified range
	private static int generatenumber(int lo, int hi) {
		//Generate a number range at [1000, 9999] as flight number
		Random r = new Random();
		int max= hi;
        int min= lo;
        
        int number = r.nextInt(max)%(max-min+1) + min;
        
        return number;	
	}	
	
	private static String Randomchoose(String[] array) {
        int hi= array.length;
        Random r = new Random();
        int index = r.nextInt(hi);
        return array[index];
	}
	
	public static Flight generateflight() {
		String[] Airportpool = generatenamepool(50,3);
		String[] Flightnamepool = generatenamepool(30,2);
		
		int number = generatenumber(1000,9999);
		String Fname = Randomchoose(Flightnamepool);
		String origin = Randomchoose(Airportpool);
		String destination = Randomchoose(Airportpool);
		
		while(origin.equals(destination)) {
			destination = Randomchoose(Airportpool);
		}
		
		Flight NewFlight = new Flight(Fname,number,origin,destination);	
		return NewFlight;
	}
	
//  test function
	public static void testSepareteChain(int size){ 
		int i = 0;
		SeparateChainingHashST<Flight, Integer> st = new SeparateChainingHashST<>();
		Flight [] saveFlightdata = new Flight[size];
		
		while(i < size) {
			Flight x = generateflight();
			saveFlightdata[i] =x;
			int val = generatenumber(0,13);
			st.put(x, val);
			i++;
		}
		
		StdOut.println("table size: " + st.size());
		StdOut.println("I      II      III");
		
	    // do the experiment for varying sizes of N
		int reps = 10;  

		for (int N=1024; N < 300000; N*=2) {
			Stopwatch sw = new Stopwatch();
			SeparateChainingHashST<Flight,Integer> tmp = new SeparateChainingHashST<>();
			for (int r = 1; r <= reps; r++) {  
				SeparateChainingHashST<Flight,Integer> test = new SeparateChainingHashST<>();
				
				int count = 1;				     
				for ( Flight key: st.keys()) {  
//					test.put(key,1);
					//when test mixture put and get, use the following statement.
					test.put(key, st.get(key));           
					if (count++ == N) break;
				}
				tmp = test;
			}
			Stopwatch sw2 = new Stopwatch();
			
			for(int j =0; j<N/100;j++) {
				Flight np1 = generateflight();
				tmp.put(np1, 1);
			}
			
			Stopwatch sw3 = new Stopwatch();
			for(int q =0; q<N/100;q++) {
				tmp.get(saveFlightdata[q]);
			}
			
			
			StdOut.format("%.4f-%.4f-%.5f\n",sw.elapsedTime ()/reps,sw2.elapsedTime ()/reps,sw3.elapsedTime ()/reps);
		}
	}

	
	public static void testlinearprobing(int size) {
		int i =0;
		LinearProbingHashST<Flight, Integer> lp = new LinearProbingHashST<>();
		Flight [] saveFlightdata = new Flight[size];
		while(i < size) {
			Flight x = generateflight();
			saveFlightdata[i] =x;
			int val = generatenumber(0,13);
			lp.put(x, val);
			i++;
		}
		
		StdOut.println("table size: " + lp.size());
		StdOut.println("I      II");


	    // do the experiment for varying sizes of N
		int reps = 10;  

		for (int N=1024; N < 300000; N*=2) {
			Stopwatch sw = new Stopwatch();
			LinearProbingHashST<Flight, Integer> tmp = new LinearProbingHashST<>();
			for (int r = 1; r <= reps; r++) {  
				LinearProbingHashST<Flight, Integer> test = new LinearProbingHashST<>();
				int count = 1;				    
				for ( Flight key: lp.keys()) { 
//					test.put(key,1);
					//when test mixture put and get, use the following statement.
					test.put(key, lp.get(key));           
					if (count++ == N) break;
				}  
			}
			Stopwatch sw2 = new Stopwatch();
			
			for(int j =0; j<N/100;j++) {
				Flight np1 = generateflight();
				tmp.put(np1, 1);
			}
			
			Stopwatch sw3 = new Stopwatch();
			for(int q =0; q<N/100;q++) {
				tmp.get(saveFlightdata[q]);
			}
			
			StdOut.format("%.4f-%.4f-%.5f\n",sw.elapsedTime ()/reps,sw2.elapsedTime ()/reps,sw3.elapsedTime ()/reps);
		}
	}
}
