package group5;

import algs13.Stack;
import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

public class ParseXML {

	public static void main(String[] args) {
		//Get XML file//
		StdOut.print("Enter the URL: ");
		String target = StdIn.readString();
		In input = new In(target);
		
		String[] content =input.readAllStrings();
		XMLToken[] container =new XMLToken[content.length];
		
		Stack<XMLToken> Collection = new Stack<XMLToken>();
		
		//Casting XMLToken on String//
		for(int i=0;i<content.length;i++) {
			XMLToken item = new XMLToken(content[i]);
			container[i] = item;
		}
		
		//Check Whether XML tag is legal//
		for(XMLToken item : container) {
			if(item.isOpeningTag()) {
				StdOut.println(item.token);
				Collection.push(item);
			}
			else if(item.isClosingTag()) {
				StdOut.println(item.token);
				if(Collection.isEmpty()) {
					throw new RuntimeException(item.token +" has no matching opening tag");
				}
				
				XMLToken CorrespondOpenTag = Collection.pop();
				if(!CorrespondOpenTag.tagName().equals(item.tagName())) {
					throw new RuntimeException(item.token +"tag does not match its opening tag");	
				}
			}
			else StdOut.println(item.token);
		}
		
		if(!Collection.isEmpty()) {
			try {
				throw new RuntimeException("there are opening tags has no matching closing tag");
			}catch(Exception e){
				e.printStackTrace();
			}
			for(XMLToken rest : Collection) {
			StdOut.println(rest.token);
			}
		}
	}
}
