package group4;

import stdlib.StdAudio;
import week5examples.Note;

public class Chord implements Comparable<Chord>{
	private double duration;
	private double frequency1;
	private double frequency2;
	private double frequency3;
	
	public Chord(double duration, double frequency1, double frequency2, double frequency3) {
		this.duration = duration;
		this.frequency1 = frequency1;
		this.frequency2 = frequency2;
		this.frequency3 = frequency3;
	}
	
	public Note[ ] notes() {
		Note [] notelist = new Note[] {
		new Note(duration,frequency1),
		new Note(duration,frequency2),
		new Note(duration,frequency3)
		};
		return notelist;
	}
	
	public void play() {
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * this.duration);
		final double[] slices = new double[sliceCount+1];
		for (int i = 0; i <= sliceCount; i++) {
			Note [ ] chord = notes(); 
			for(Note items : chord) {
				slices[i] += Math.sin(2 * Math.PI * i * items.frequency() / StdAudio.SAMPLE_RATE);
			}
			slices[i] /= chord.length;
		}
		StdAudio.play(slices);
	}
	
	public String toString() {
		return String.format("Chord of %.2f Hz, %.2f Hz, %.2f Hz for %.3f S", this.frequency1,this.frequency2,this.frequency3,this.duration);};
	
	public boolean equals(Object that) {
		if (this == that) return true;
		if (!(that instanceof Chord)) return false;
		Chord thatchord =(Chord) that ;
		return this.frequency1 == thatchord.frequency1 && 
			   this.frequency2 == thatchord.frequency2 &&
			   this.frequency3 == thatchord.frequency3 &&
			   this.duration == thatchord.duration;
		};
	
	public int hashCode() {
		return Double.hashCode(this.duration) + 31*Double.hashCode(this.frequency1) + 31*31*Double.hashCode(frequency2) + 31*31*31*Double.hashCode(frequency3);};
	
	public int compareTo(Chord that) {
		if (this.frequency1 < that.frequency1) return -1;
		if (this.frequency1 > that.frequency1) return +1;
		if (this.frequency2 < that.frequency2) return -1;
		if (this.frequency2 > that.frequency2) return +1;
		if (this.frequency3 < that.frequency3) return -1;
		if (this.frequency3 > that.frequency3) return +1;
		if (this.duration < that.duration) return -1;
		if (this.duration > that.duration) return +1;
		return 0;
		}
	

}
