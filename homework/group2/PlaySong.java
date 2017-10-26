package group2;

import stdlib.StdAudio;
import stdlib.StdIn;

public class PlaySong {
	
	public static void playTone(double frequency) {
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * 0.25);
		final double[] slices = new double[sliceCount+1];
		for (int i = 0; i <= sliceCount; i++) {
			slices[i] = Math.sin(2 * Math.PI * i * frequency / StdAudio.SAMPLE_RATE);
		}
		StdAudio.play(slices);
	}
	
    public static void playmusic(double[] values) {
		for(double value:values) {
			playTone(value);
		}	
    }
    
	public static void main(String[] args) {
		StdIn.fromFile("data/group2song.txt");
		double[] values = StdIn.readAllDoubles();
		playmusic(values);
	    StdAudio.close();
        System.exit(0);
}
}
