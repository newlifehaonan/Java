package HW5;

public class Flight {
	

	private final String name;
	private final int number;
	private final String origin;
	private final String Destination;
	
	
	public Flight(String name, int number, String origin, String Destination) {
		this.name= name;                                                                                              
		this.number= number;
		this.origin= origin;
		this.Destination= Destination;

	}

	public String toString() {
		return "[" +this.name+", " + this.number+", " + this.origin+", " + this.Destination + "]";
	}
	
	public int hashCode()
    {
        int hash = 17;
        hash = 31 * hash + name.hashCode();
        hash = 31 * hash + origin.hashCode();
        hash = 31 * hash + Destination.hashCode();
        hash = 31 * hash + number;
        return hash;
    } 
		
	public boolean equals(Flight x) {
		if (x == this) return true;
		if (x == null) return false;
		if (x.getClass() != this.getClass()) return false;
		Flight that = (Flight) x;
		return (
				this.name == that.name) && 
				(this.number == that.number) && 
				(this.origin == that.origin)&&
				(this.Destination == that.Destination);
	}
	
}