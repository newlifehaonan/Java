package group4;

import java.util.ArrayList;

public class ChordSong {
	
	private ArrayList<Chord> ChordSong;
	public ChordSong() {
		this.ChordSong = new ArrayList<Chord>();
	}
	
	public void add(Chord note) {
		ChordSong.add(note);
	}
	
	public void play() {
		for (Chord chord : ChordSong) {
			chord.play();
		}
	}
	
	public Chord chordAt(int index) {
		if (index < 0 || index >= ChordSong.size()) {
			throw new IndexOutOfBoundsException("The index is out of bounds");
		}
		return ChordSong.get(index); 
	}
	
	public String toString() {
		return ChordSong.toString();
	}
}
