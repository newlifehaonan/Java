package group3;

import algs13.Stack;
import stdlib.StdIn;
import stdlib.StdOut;

public class BalancedPunctuation {
	
	public static void main(String[] args) {
	StdOut.print("Enter String:");
	String input = StdIn.readLine();
	String [] S = input.split("");
	String object = new String();
	Stack<String> collection = new Stack<String>();
	
	for(String item : S) {
		if (item.equals("(")) collection.push(item);
		else if (item.equals("{")) collection.push(item);
		else if (item.equals("[")) collection.push(item);
		else if (item.equals(")"))
				{
				 if(collection.isEmpty()) throw new RuntimeException("Unbalanced String");
				 object = collection.pop();
				 if (!object.equals("("))  throw new RuntimeException("Unbalanced String");
				}
		else if (item.equals("}"))
				{
			     if(collection.isEmpty()) throw new RuntimeException("Unbalanced String");
			     object = collection.pop();
				 if (!object.equals("{"))  throw new RuntimeException("Unbalanced String");
				}
		else if (item.equals("]"))
				{
			     if(collection.isEmpty()) throw new RuntimeException("Unbalanced String");
			     object = collection.pop();
				 if (!object.equals("["))  throw new RuntimeException("Unbalanced String");
				}
		else continue;
	}
	StdOut.print("Balanced");
}
}
