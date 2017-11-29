package edu.iup.cosc210.video.ui;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import edu.iup.cosc210.video.bo.Video;
import edu.iup.cosc210.video.bo.VideoManager;

public class VideoTableModelAdapter extends AbstractTableModel implements TableModel {
	private VideoManager vm;
	

	public VideoTableModelAdapter(VideoManager vm) {
		super();
		this.vm = vm;
	}

	@Override
	public int getRowCount() {
		return vm.getNoVideos();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0: return "ID";
		case 1: return "Title";
		case 2: return "Year";
		case 3: return "Rating";
		case 4: return "Rate";
		case 5: return "New";
		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0: return String.class;
		case 1: return String.class;
		case 2: return Integer.class;
		case 3: return String.class;
		case 4: return Double.class;
		case 5: return Boolean.class;
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Video video = vm.getVideo(rowIndex);

		switch (columnIndex) {
		case 0: return video.getVid();
		case 1: return video.getTitle();
		case 2: return video.getYear();
		case 3: return video.getRating();
		case 4: return video.getRentalRate();
		case 5: return video.isNewRelease();
		}

		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

}
