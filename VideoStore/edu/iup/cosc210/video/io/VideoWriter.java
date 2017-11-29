package edu.iup.cosc210.video.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import edu.iup.cosc210.video.bo.Video;

/**
 * Helper class to save videos into a comma separated file.
 * 
 * @author dtsmith
 *
 */
public class VideoWriter {
	private PrintStream output;
	
	/**
	 * Constructor
	 * 
	 * @param fileName - the name of the file into which videos will be saved
	 * @throws FileNotFoundException
	 */
	public VideoWriter(String fileName) throws FileNotFoundException {
		output = new PrintStream(fileName);		
	}
	
	/**
	 * Save a video into the file.
	 * 
	 * @param video - the video to be saved.
	 */
	public void saveVideo(Video video) {
		output.print(video.getVid());
		output.print(',');
		output.print(video.getTitle());
		output.print(',');
		output.printf("%.2f",video.getRentalRate());
		output.print(',');
		output.print(video.getYear());
		output.print(',');
		output.print(video.getRating());
		output.print(',');
		output.print(video.isNewRelease() ? "T" : "F");
		output.println();
	}
	
	/**
	 * Close the output file.  No more videos can be saved.
	 * 
	 * @throws IOException
	 */
	public void close() {
		output.close();
	}
}
