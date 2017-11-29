package edu.iup.cosc210.video.bo;

import java.util.Date;

/**
 * A video.
 * 
 * @author dtsmith
 */
public class Video {
	private String vid;
	private String title;
	private double rentalRate;
	private int year;
	private String rating;
	private boolean newRelease;
	private static int nextNum = 100;

	/**
	 * Initialize the next available video id number
	 * @param no - the number to be used as the next available video id
	 */
	private static void intializeNextNum(int no) {
		nextNum = no;
	}
	
	/**
	 * Create a new Video
	 * 
	 * @param vid - video id
	 * @param title - title of the video
	 * @param rentalRate - current rental rate
	 * @param year - year the video was first released
	 * @param rating - the current movie industry rating
	 * @param newRelease - an indicator for new releases
	 */
	public Video(String vid, String title, double rentalRate, int year,
			String rating, boolean newRelease) {
		this.vid = vid;
		this.title = title;
		this.rentalRate = rentalRate;
		this.year = year;
		this.rating = rating;
		this.newRelease = newRelease;
	}

	/**
	 * Default constructor used to provide default values for a new
	 * video.
	 */
	public Video() {
		vid = "V" + nextNum++;
		title = "";
		rentalRate = 1.99;
		year = new Date().getYear() + 1900;
		rating = "R";
		newRelease = true;
	}

	/**
	 * Get the video id
	 * 
	 * @return the video id
	 */
	public String getVid() {
		return vid;
	}

	/**
	 * Get the daily rental rate for this video.
	 * 
	 * @return the daily rental rate
	 */
	public double getRentalRate() {
		return rentalRate;
	}

	/**
	 * Set the daily rental rate to a new value.
	 * 
	 * @param rentalRate - the new daily rental rate
	 */
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	/**
	 * Get the title of the video.
	 * 
	 * @return the title of the video
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the video to an updated value
	 * @param title - update title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the year the video was released.
	 * 
	 * @return the year the video was released
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set the year the video was released to an updated value.
	 * 
	 * @param year the updated year the video was released
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Get the new release indicator.
	 * 
	 * @return true if the video is new release, otherwise false.
	 */
	public boolean isNewRelease() {
		return newRelease;
	}

	/**
	 * Set the new release indicator to an updated value
	 * 
	 * @param newRelease the updated new release indicator
	 */
	public void setNewRelease(boolean newRelease) {
		this.newRelease = newRelease;
	}

	/**
	 * Get the current movie industry rating.
	 * 
	 * @return the current movie industry rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * Set the movie industry rating to a new value.
	 * 
	 * @param rating the updated movie industry rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
}
