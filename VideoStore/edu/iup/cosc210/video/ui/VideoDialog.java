package edu.iup.cosc210.video.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.javera.ui.layout.JvGridLayout;
import com.javera.ui.layout.JvGridLocation;

import edu.iup.cosc210.video.bo.Video;

public class VideoDialog extends JDialog {
	private Video video;
	
	private JTextField vidField;
	private JTextField titleField;
	private JTextField yearField;
	private JComboBox ratingField;
	private JCheckBox newField;
	private JTextField priceField;
	
	private boolean okPressed = false;

	public VideoDialog(VideoFrame videoFrame, Video video, String title) {
		super(videoFrame, title, true);
		
		this.video = video;
		
		JPanel dataPanel = new JPanel(new JvGridLayout(3, 4));
		
		dataPanel.setBorder(BorderFactory.createTitledBorder("Video"));
		
		dataPanel.add(new JLabel("VID"), new JvGridLocation(0,0));
		
		vidField = new JTextField(3);
		vidField.setEditable(false);
		vidField.setBackground(Color.LIGHT_GRAY);
		
		JPanel vidPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		vidPanel.add(vidField);
		
		dataPanel.add(vidPanel, new JvGridLocation(0,1));
		
		dataPanel.add(new JLabel("Title"), new JvGridLocation(0,2));
		titleField = new JTextField(15);
		dataPanel.add(titleField, new JvGridLocation(0,3));
		
		dataPanel.add(new JLabel("Year"), new JvGridLocation(1,0));
		yearField = new JTextField(4);
		dataPanel.add(yearField, new JvGridLocation(1,1));
		
		dataPanel.add(new JLabel("Rating"), new JvGridLocation(1,2));
		ratingField = new JComboBox();
		ratingField.addItem("G");
		ratingField.addItem("PG");
		ratingField.addItem("PG13");
		ratingField.addItem("R");
		
		JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		ratingPanel.add(ratingField);

		dataPanel.add(ratingPanel, new JvGridLocation(1,3));
		
		newField = new JCheckBox("New");
		dataPanel.add(newField, new JvGridLocation(2,0,1,2));
		
		dataPanel.add(new JLabel("Price"), new JvGridLocation(2,2));
		priceField = new JTextField(6);
		
		JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		pricePanel.add(priceField);

		dataPanel.add(pricePanel, new JvGridLocation(2,3));
		
				
		getContentPane().add(dataPanel);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2,10,10));
		
		JButton okButton = new JButton("OK");

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getFields();
				okPressed = true;
				setVisible(false);
				
			}});

		buttonPanel.add(okButton);

		JButton cancelButton = new JButton("Cancel");
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}});
		
		buttonPanel.add(cancelButton);
		
		JPanel centerButtonPanel = new JPanel();
		
		centerButtonPanel.add(buttonPanel);
		
		getContentPane().add(centerButtonPanel, BorderLayout.SOUTH);
		
		pack();
		
		setLocation(videoFrame.getX() + (videoFrame.getWidth() - getWidth()) / 2,
			videoFrame.getY() + (videoFrame.getHeight() - getHeight()) / 2);

		setFields();
	}

	
	public boolean isOkPressed() {
		return okPressed;
	}


	private void setFields() {
		vidField.setText(video.getVid());
		titleField.setText(video.getTitle());
		yearField.setText(String.format("%d", video.getYear()));
		ratingField.setSelectedItem(video.getRating());
		newField.setSelected(video.isNewRelease());
		priceField.setText(String.format("%6.2f", video.getRentalRate()));
	}
	
	private void getFields() {
		video.setTitle(titleField.getText());
		video.setYear(Integer.parseInt(yearField.getText()));
		video.setRating((String) ratingField.getSelectedItem());
		video.setNewRelease(newField.isSelected());
		video.setRentalRate(Double.parseDouble(priceField.getText().trim()));
	}

}
