/*
 * Author: Zachary Bishop
 * Date: 11/1/18
 * Purpose: Provide a GUI for inputing search options, and submitting the search
 * 			to the request manager.
 */


package com.auto_scraper;

public class GUI {

	public GUI() {

	}

	// Bypass GUI input for dev
	public void test() {

		// Single Threaded manager
		RequestManager requestManager = new RegularRequestManager();
		RequestManager threadedRequestmanager = new ThreadedRequestManager();

		// populate options
		SearchOptions options = new SearchOptions();


		// start the requests
		requestManager.submit(options);
		threadedRequestmanager.submit(options);

	}

}
