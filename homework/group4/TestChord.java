package group4;

public class TestChord {

	public static void main(String[] args) {
		
		Chord[ ] threechord = new Chord[] {
			new Chord(0.5,261.626, 349.228, 391.995),
			new Chord(0.5,440.0, 880.0, 1760.0),
			new Chord(0.5,329.628, 391.995, 493.883)
		};
		
		for( Chord chord : threechord) {
			chord.play();
		}
	}
}
