package group5;

public class XMLToken {
	//local variable//
	public String token;
	public String[] tokenlist;
	
	//constructor//
	public XMLToken(String token) {
		this.token=token;
		this.tokenlist  = token.split("");
	}
	
	//isTag?//
	public boolean isTag() {
		return tokenlist[0].equals("<") && tokenlist[tokenlist.length-1].equals(">");
	}
	
	//isData?//
	public boolean isData() {
		return !(tokenlist[0].equals("<") && tokenlist[tokenlist.length-1].equals(">"));	
	}
	
	//isClosingTag?//
	public boolean isClosingTag() {
		return isTag() && tokenlist[1].equals("/");
	}
	
	//isSelfClosingTag?//
	public boolean isSelfClosingTag() {
		return isTag() && tokenlist[tokenlist.length-2].equals("/");
	}
	
	//isOpeningTag?//
	public boolean isOpeningTag() {
		return isTag() && !isClosingTag() && !isSelfClosingTag();
	}
	
	//Show Tag name//
	public String tagName() {
		if(isOpeningTag()) {
			String name =token.substring(1, tokenlist.length-1);
			return name;
		}else if(isClosingTag()) {
			String name =token.substring(2, tokenlist.length-1);
			return name;
		}
		else if(isSelfClosingTag()) {
			String name =token.substring(1, tokenlist.length-2);
			return name;
		}else return null;
	}
	
}
