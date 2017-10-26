package group4;

public class TestChordSong {

	public static void main(String[] args) {
		ChordSong chordmusic = new ChordSong();
		chordmusic.add(new Chord(0.25,27.500, 29.135, 30.868));
		chordmusic.add(new Chord(0.25,55.000, 58.270, 61.735));
		chordmusic.add(new Chord(0.25,110.000, 116.541, 123.471));
		chordmusic.add(new Chord(0.25,220.000, 233.082, 246.942));
		chordmusic.add(new Chord(0.25,440.000, 466.164, 493.883));
		chordmusic.add(new Chord(0.25,880.000, 932.328, 987.767));
		chordmusic.add(new Chord(0.25,1760.000, 1864.655, 1975.533));
		chordmusic.add(new Chord(0.25,3520.000, 3729.310, 3951.0667));	
		chordmusic.play();
	}
}
