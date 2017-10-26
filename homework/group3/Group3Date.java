package group3;

import java.util.Arrays;
import java.util.Comparator;
import stdlib.StdOut;

public class Group3Date implements Comparable<Group3Date> {
	private static final int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private final int month;   // month (between 1 and 12)
	private final int day;     // day   (between 1 and DAYS[month]
	private final int year;    // year

	// do bounds-checking to ensure object represents a valid date
	public Group3Date(int month, int day, int year) {
		if (!isValid(month, day, year)) throw new Error("Invalid date");
		this.month = month;
		this.day   = day;
		this.year  = year;
	}

	// create new data by parsing from string of the form mm/dd/yy
	public Group3Date(String date) {
		String[] fields = date.split("/");
		if (fields.length != 3) {
			throw new Error("Date parse error");
		}
		month = Integer.parseInt(fields[0]);
		day   = Integer.parseInt(fields[1]);
		year  = Integer.parseInt(fields[2]);
		if (!isValid(month, day, year)) throw new Error("Invalid date");
	}

	public int month() { return month; }
	public int day()   { return day;   }
	public int year()  { return year;  }


	// is the given date valid?
	private static boolean isValid(int m, int d, int y) {
		if (m < 1 || m > 12)      return false;
		if (d < 1 || d > DAYS[m]) return false;
		if (m == 2 && d == 29 && !isLeapYear(y)) return false;
		return true;
	}

	// is y a leap year?
	private static boolean isLeapYear(int y) {
		if (y % 400 == 0) return true;
		if (y % 100 == 0) return false;
		return y % 4 == 0;
	}

	// return the next Date
	public Group3Date next() {
		if (isValid(month, day + 1, year))    return new Group3Date(month, day + 1, year);
		else if (isValid(month + 1, 1, year)) return new Group3Date(month + 1, 1, year);
		else                                  return new Group3Date(1, 1, year + 1);
	}


	// is this Date after b?
	public boolean isAfter(Group3Date b) {
		return compareTo(b) > 0;
	}

	// is this Date a before b?
	public boolean isBefore(Group3Date b) {
		return compareTo(b) < 0;
	}

	// compare this Date to that one
	public int compareTo(Group3Date that) {
		if (this.year  < that.year)  return -1;
		if (this.year  > that.year)  return +1;
		if (this.month < that.month) return -1;
		if (this.month > that.month) return +1;
		if (this.day   < that.day)   return -1;
		if (this.day   > that.day)   return +1;
		return 0;
	}
	
	public int countDaysUntil(Group3Date a) {
		int day = 0;
		boolean result1 = isBefore(a);
		boolean result2 = isAfter(a);
		Group3Date nextday = next();
		while(result1) {
			day++;
			result1 = nextday.isBefore(a);
			nextday = nextday.next();
		};
		
		while(result2) {
			day--;
			a= a.next();
			result2 = isAfter(a);
		}
		return day;
	}

	// return a string representation of this date
	public String toString() {
		return month + "/" + day + "/" + year;
	}

	// is this Date equal to x?
	public boolean equals(Object x) {
		if (x == this) return true;
		if (x == null) return false;
		if (x.getClass() != this.getClass()) return false;
		Group3Date that = (Group3Date) x;
		return (this.month == that.month) && (this.day == that.day) && (this.year == that.year);
	}

	public int hashCode() {
		int hash = 17;
		hash = 31*hash + month;
		hash = 31*hash + day;
		hash = 31*hash + year;
		return hash;
	}



	// A stupid class!
	private static class MyComp implements Comparator<Group3Date>{
		public int compare (Group3Date o1, Group3Date o2) {
			return o2.compareTo (o1);
		}		
	}
	
	// sample client for testing
	public static void main(String[] args) {
		// using the stupid class:
		Comparator<Group3Date> c1 = new MyComp ();

		// using an inner class:
		Comparator<Group3Date> c2 = new Comparator<Group3Date>() {
			public int compare (Group3Date o1, Group3Date o2) {
				return o2.compareTo (o1);
			}
		};
		
		// using a lambda expression:
		Comparator<Group3Date> c3 = (o1, o2) -> o2.compareTo (o1);	
		
		Group3Date[] dates = new Group3Date[] { new Group3Date(2, 25, 2004), new Group3Date(2, 25, 2003), } ;
		StdOut.println (Arrays.binarySearch (dates, new Group3Date(2, 25, 2003), c1));
		
//		Date today = new Date(2, 25, 2004);
//		StdOut.println(today);
//		for (int i = 0; i < 10; i++) {
//			today = today.next();
//			StdOut.println(today);
//		}
//
//		StdOut.println(today.isAfter(today.next()));
//		StdOut.println(today.isAfter(today));
//		StdOut.println(today.next().isAfter(today));
//
//
//		Date birthday = new Date(10, 16, 1971);
//		StdOut.println(birthday);
//		for (int i = 0; i < 10; i++) {
//			birthday = birthday.next();
//			StdOut.println(birthday);
//		}
	}

}

