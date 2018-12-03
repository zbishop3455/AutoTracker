package com.auto_scraper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import search_requests.SearchRequest;
import search_requests.SearchRequestFactory;

public class ThreadedRequestManager implements RequestManager {

	SearchRequestFactory factory;

	public ThreadedRequestManager() {
		factory = new SearchRequestFactory();
	}

	@Override
	public void submit(SearchOptions options) {

		Long startTime = System.currentTimeMillis();
		List<SearchResult> allResults = new ArrayList<SearchResult>();


		SearchRequest testCLRequest = factory.createCraigslistSearchRequest(options);
		SearchRequest testCLRequest2 = factory.createCraigslistSearchRequest(options);
		SearchRequest testCarfaxRequest = factory.createCarfaxSearchRequest(options);

		// create the thread pool
		ExecutorService executor = Executors.newFixedThreadPool(3);

		// submit tasks
		Future<List<SearchResult>> r1 = executor.submit(testCLRequest);
		Future<List<SearchResult>> r11 = executor.submit(testCLRequest2);
		Future<List<SearchResult>> r2 = executor.submit(testCarfaxRequest);


		executor.shutdown();
		while (! executor.isShutdown()) {

		}

		try {
			allResults.addAll(r1.get());
			allResults.addAll(r2.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		Long finishTime = System.currentTimeMillis();
		System.out.println("Total Multi-Threaded Time: " + Long.toString(finishTime - startTime) + " miliseconds");


		if (! allResults.isEmpty()) {
			ResultsWindow window = new ResultsWindow(allResults);
		}

	}

}
