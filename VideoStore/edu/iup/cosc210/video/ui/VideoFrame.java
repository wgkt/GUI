package edu.iup.cosc210.video.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import edu.iup.cosc210.video.bo.Video;
import edu.iup.cosc210.video.bo.VideoManager;

public class VideoFrame extends JFrame {
	private VideoManager vm;
	private JTable videoTable;
	private VideoTableModelAdapter videoModel;

	public VideoFrame(final VideoManager vm) {
		super("Video Store");
		this.vm = vm;

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(600, 400);

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation((size.width - getWidth()) / 2,
				(size.height - getHeight()) / 2);

		videoModel = new VideoTableModelAdapter(vm);
		videoTable = new JTable(videoModel);

		getContentPane().add(new JScrollPane(videoTable));

		Action newAction = new AbstractAction("New...", new ImageIcon(
				"images/New.gif")) {
			public void actionPerformed(ActionEvent e) {
				Video video = new Video();

				VideoDialog videoDialog = new VideoDialog(VideoFrame.this,
						video, "Edit Video");

				videoDialog.setVisible(true);

				if (videoDialog.isOkPressed()) {
					vm.addVideo(video);

					int i = vm.getNoVideos() - 1;
					videoModel.fireTableRowsInserted(i, i);
				}

			}
		};

		Action openAction = new AbstractAction("Open...", new ImageIcon(
				"images/Open.gif")) {
			public void actionPerformed(ActionEvent e) {
				int i = videoTable.getSelectedRow();

				if (i >= 0) {
					Video video = VideoFrame.this.vm.getVideo(i);

					VideoDialog videoDialog = new VideoDialog(VideoFrame.this,
							video, "Edit Video");

					videoDialog.setVisible(true);

					videoModel.fireTableRowsUpdated(i, i);
				}

			}
		};

		Action deleteAction = new AbstractAction("Delete...", new ImageIcon(
				"images/Delete.gif")) {
			public void actionPerformed(ActionEvent e) {
				int i = videoTable.getSelectedRow();

				if (i >= 0) {
					Video video = VideoFrame.this.vm.getVideo(i);

					if (JOptionPane.showConfirmDialog(
							VideoFrame.this,
							"Are you sure your want to delete\n"
									+ video.getTitle() + "?", "Delete Video",
							JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						vm.removeVideo(i);
 						videoModel.fireTableRowsDeleted(i, i);
					}
				}

			}
		};
		
		Action aboutAction = new AbstractAction("About...") {
			public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(
							VideoFrame.this,
							"Video Store\nVersion 1.0\nCopyright 2015", "About",
							JOptionPane.INFORMATION_MESSAGE) ;
			}
		};

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.addSeparator();
		fileMenu.add(deleteAction);

		menuBar.add(fileMenu);
		
		JMenu helpMenu = new JMenu("help");
		helpMenu.add(aboutAction);
		
		menuBar.add(helpMenu);

		JToolBar toolBar = new JToolBar();

		toolBar.add(newAction);
		toolBar.add(openAction);
		toolBar.addSeparator();
		toolBar.add(deleteAction);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(menuBar, BorderLayout.NORTH);
		topPanel.add(toolBar, BorderLayout.SOUTH);

		getContentPane().add(topPanel, BorderLayout.NORTH);

		JButton openButton = new JButton(openAction);

		JPanel bottomPanel = new JPanel();
		bottomPanel.add(openButton);

		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out
					.println("Usage: java edu.iup.cosc210.bo.VideoManager [video file]");
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

		new VideoFrame(vm).setVisible(true);

	}

}
