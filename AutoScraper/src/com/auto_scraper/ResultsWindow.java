package com.auto_scraper;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//creates a window that displays the data foud in a grid



@SuppressWarnings("serial")
public class ResultsWindow extends JPanel {

	//data to be displayed
	private Object[][] displayData;
	private List<SearchResult> results;
	private static final int ROW_SIZE = 8;

	JScrollPane scrollPane;


	public ResultsWindow(List<SearchResult> allResults){

		this.results = allResults;
		String[] header = {"Listing Name", "Price", "Miles", "Location", "Year", "Platform", "Listing Date", "Link"};

		// populate display data
		displayData = new Object[allResults.size()][ROW_SIZE];
		for (int i=0; i<allResults.size(); i++) {
			SearchResult curResult = allResults.get(i);

			// get values
			String title = curResult.getName();
			int price = curResult.getPrice();
			int miles = curResult.getMiles();
			String location = curResult.getLocation();
			int year = curResult.getYear();
			String platform = curResult.getPlatform();
			String listingDate = curResult.getListingDate();
			String link = curResult.getLink();


			if (title != null)
			displayData[i][0] = title;

			if (price > 0)
				displayData[i][1] = price;

			if (miles > 0)
				displayData[i][2] = miles;

			if (location != null)
				displayData[i][3] = location;

			if (year > 0)
				displayData[i][4] = year;

			if (platform != null)
				displayData[i][5] = platform;

			if (listingDate != null)
				displayData[i][6] = listingDate;

			if (link != null);
				displayData[i][7] = link;
		}


		// make table
		DefaultTableModel tableModel = new DefaultTableModel(displayData, header);
		JTable table = new JTable(tableModel);
		table.setFont(new Font("TimesRoman", Font.BOLD, 20));
		table.setRowHeight(30);
		table.setRowMargin(2);

		table.setEnabled(false);

		// create scoll pane
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(2);
		table.setAutoCreateRowSorter(true);
		createWindow();

	}


	public void createWindow(){
		JFrame window = new JFrame("Search Results");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.add(scrollPane);
		window.setSize(1980,1020);
		window.setBounds(800, 600, 800, 600);
		window.setAlwaysOnTop(true);
		window.setVisible(true);

	}


	}

