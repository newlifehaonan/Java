/* CSC 403
 * 
 * Haonan(Harry) Chen
 * 
 * Get help from anyone?  put that here
 * 
 * Homework 1 Driver 
 * 
 * Instructions:  using sizeTest  as a template, create additional functions to test
 *                the member functions in your LinkedListST implementation.
 *                AND
 *                create a reasonable set of test cases for each; 
 *                call your testing functions from main
 *                
 */
package HW1;

import stdlib.StdIn;
import stdlib.StdOut;

public class hw1Driver {
		
		public static void main(String[] args)
		{			
			// To do:   call you testing functions with your test cases. 
			//  label each test case with a comment describing what you are testing for.
			StdOut.println("1.Test size()");
			StdOut.println("a.test size on an empty ST");
			sizeTest("",0);						// test size on an empty ST (symbol table)
			StdOut.println("b.test size on a non-empty ST");
			sizeTest("abcde",5);					// test size on a non-empty ST
			
			StdOut.println("\n2.Test secondMaxKey()");
			StdOut.println("a.test secondMaxKey on ST which size > 1");
			secondMaxKeyTest("abcdefg","f");		//test secondMaxKey on ST which size > 1
			StdOut.println("b.test secondMaxKey on ST which size =1");
			secondMaxKeyTest("A",null);			//test secondMaxKey on ST which size =1
			StdOut.println("c.test secondMaxKey on an empty ST 1");
			secondMaxKeyTest("",null);			//test secondMaxKey on an empty ST 
			
			StdOut.println("\n3.Test rank()");
			StdOut.println("a.test rank on non-empty ST");
			rankTest("bcdef","f",4);				//test rank on a non-empty ST
			rankTest("bcdef","a",0);				//test rank on non-empty ST
			
			StdOut.println("\n4.Test floor()");
			StdOut.println("a.test floor on ST with which target key included");
			floorTest("stuvwxy","y","y");		//test floor on ST with which target key included
			StdOut.println("b.test floor on ST with which target key NOT included");
			floorTest("stuvwxy","z","y");		//test floor on ST with which target key NOT included
			StdOut.println("c.test floor on ST with which floor-key does not exist");
			floorTest("stuvwxy","a",null);		//test floor on ST with which floor-key does not exist
			
			StdOut.println("\n5.Test inverse()");
			StdOut.println("a.test inverse on ST with no duplicate value");
			inverseTest("abcdefg","1234567");	//test inverse on ST with no duplicate value
			StdOut.println("b.test inverse on ST with duplicate value");
			inverseTest("abcdefg","1234555");	//test inverse on ST with duplicate value

		}
		
		/*
		 * Test Function
		 */
		
		/*
		 * Test for Size function
		 */
		public static void sizeTest( String vals, int answer ) {
			
			// create and populate the table from the input string vals
			LinkedListST<String,Integer> aList = new LinkedListST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the size function
			int result = aList.size();
			//report result
			if ( result == answer)  // test passes
				StdOut.format("sizeTest: Correct  String %s Answer: %d\n", vals,result);
			else
				StdOut.format("sizeTest: *Error*  String %s Answer: %d\n", vals,result);
		}
		
		/*
		 * Test for SecondMaxKey function
		 */
		public static void secondMaxKeyTest(String vals, String answer) {
			LinkedListST<String,Integer> aList = new LinkedListST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the SecondMaxKey function
			String result = aList.secondMaxKey();
			//report result
			if(result==null && answer == null) {
				StdOut.format("secondMaxKey: Correct  String %s Answer:null\n",vals);
				return;
			}
			if(result==null && answer != null) {
				StdOut.format("secondMaxKey: *Error*  String %s Answer:null\n",vals);
				return;
			}
			if (result.equals(answer))  // test passes
				StdOut.format("secondMaxKey: Correct  String %s Answer: %s\n", vals,result);
			else
				StdOut.format("secondMaxKey: *Error*  String %s Answer: %s\n", vals,result);
		}
		/*
		 * Test for rank function
		 */
		public static void rankTest(String vals, String input, int answer) {
			LinkedListST<String,Integer> aList = new LinkedListST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the rank function
			int result = aList.rank(input);
			//report result
			if (result == answer)  // test passes
				StdOut.format("rank(%s): Correct  String %s Answer: %d\n", input, vals,result);
			else
				StdOut.format("rank(%s): *Error*  String %s Answer: %d\n", input,vals,result);
		}
		
		/*
		 * Test for floor function
		 */
		public static void floorTest(String vals, String input, String answer) {
			LinkedListST<String,Integer> aList = new LinkedListST<String,Integer>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(vals.substring(i, i+1),i);
			}
			//  call the floor function
			String result = aList.floor(input);
			//report result
			if(result==null && answer == null) {
				StdOut.format("floor(%s): Correct  String %s Answer:null\n",input, vals);
				return;
			}
			
			if(result==null && answer != null) {
				StdOut.format("floor(%s): *Error*  String %s Answer:null\n",input, vals);
				return;
			}
			
			if (result.equals(answer))  // test passes
				StdOut.format("floor(%s): Correct  String %s Answer: %s\n", input, vals,result);
			else
				StdOut.format("floor(%s): *Error*  String %s Answer: %s\n", input,vals,result);
		}
		/*
		 * Test for inverse function
		 */
		public static void inverseTest(String keys, String vals) {
			LinkedListST<String,String> aList = new LinkedListST<String,String>();
			for (int i=0; i < vals.length(); i++) {
				aList.put(keys.substring(i, i+1),vals.substring(i, i+1));
			}
			//  call the inverse function
			LinkedListST<String,String> result = aList.inverse();
			//print
			StdOut.println("Before Inversion");
			for (String s : aList.keys())
				StdOut.println(s + " " + aList.get(s));
			StdOut.println("After Inversion");
			for (String s : result.keys())
				StdOut.println(s + " " + result.get(s));
		}
		
}
