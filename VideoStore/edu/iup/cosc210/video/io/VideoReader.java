package edu.iup.cosc210.video.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import edu.iup.cosc210.video.bo.Video;

/**
 * Helper class to read videos from a comma separated file.
 * 
 * @author dtsmith
 *
 */
public class VideoReader {
	BufferedReader input;
	
	/**
	 * Constructor
	 * 
	 * @param fileName - the name of the file from which to read videos
	 * @throws FileNotFoundException
	 */
	public VideoReader(String fileName) throws FileNotFoundException {
		input = new BufferedReader(new FileReader(fileName));
	}
	
	/**
	 * Reads one video from the file.  Each video is stored on a separate line.
	 * 
	 * @return the next video from the file, null if there are not more videos.
	 * @throws IOException
	 */
	public Video readVideo() throws IOException {
		String line = input.readLine();
		if (line == null) {
			return null;
		}

		String[] parts = line.split(",");
		String vid = parts[0];
		String title = parts[1];
		double rate = Double.parseDouble(parts[2]);
		int year = Integer.parseInt(parts[3]);
		String rating = parts[4];
		boolean newRelease = parts[5].equals("T");
		Video video = new Video(vid, title, rate, year, rating, newRelease);

		return video;
	}
	
	/**
	 * Close the input file.  No more videos can be read.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		input.close();
	}
}
