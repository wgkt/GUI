package edu.iup.cosc210.video.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.iup.cosc210.video.io.VideoReader;
import edu.iup.cosc210.video.io.VideoWriter;

/**
 * VideoManger maintains a list of videos.  The videos can be loaded from 
 * a comma separated file using method loadFile and saved using method saveToFile.
 * 
 * @author dtsmith
 *
 */
public class VideoManager {
	private List<Video> videos = new ArrayList<Video>();

	/**
	 * Load videos from a comma separated text file.
	 * 
	 * @param fileName - the name of the file containing the videos
	 * @throws IOException
	 */
	public void loadFile(String fileName) throws IOException {
		VideoReader in = new VideoReader(fileName);

		Video video;

		while ((video = in.readVideo()) != null) {
			addVideo(video);
		}
		
		in.close();
	}

	/**
	 * Save the videos in the VideoManager to a comma separated file.
	 * 
	 * @param fileName - the name of the file in which to save videos
	 * @throws IOException
	 */
	public void saveToFile(String fileName) throws IOException {
		VideoWriter out = new VideoWriter(fileName);

		for (Video video : videos) {
			out.saveVideo(video);
		}
		
		out.close();
	}

	/**
	 * Add a video to the list of videos.
	 * 
	 * @param video - the video to be added
	 */
	public void addVideo(Video video) {
		videos.add(video);
	}

	/**
	 * Remove a video from the list of videos.
	 * 
	 * @param video - the video to be removed
	 */
	public void removeVideo(Video video) {
		videos.remove(video);
	}

	/**
	 * Remove a video given its index from the list of videos.
	 * 
	 * @param index - the index position of the video to be removed
	 */
	public void removeVideo(int i) {
		videos.remove(i);
	}

	/**
	 * Get the of videos in the VideoManager's list
	 * 
	 * @return the of videos in the VideoManager's list
	 */
	public int getNoVideos() {
		return videos.size();
	}

	/**
	 * Get a video from the VideoManager given its index position.
	 * 
	 * @param index - the index position of the video to be retrieved.
	 * @return video at the given index position.
	 */
	public Video getVideo(int i) {
		return videos.get(i);
	}

	/**
	 * Main method used to test that video are loaded from a comma separated file.
	 * 
	 * @param args - the first command line argument holds the file name of the videos
	 * to be loaded.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: java edu.iup.cosc210.bo.VideoManager [video file]");
			System.exit(-1);
		}
		VideoManager vm = new VideoManager();
		try {
			vm.loadFile(args[0]);

		} catch (FileNotFoundException e) {
			System.out.println("File " + args[0] + " not found");
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
