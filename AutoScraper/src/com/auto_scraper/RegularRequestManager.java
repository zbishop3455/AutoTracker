package com.auto_scraper;

import java.util.ArrayList;
import java.util.List;

import search_requests.SearchRequest;
import search_requests.SearchRequestFactory;

public class RegularRequestManager implements RequestManager {

	private SearchRequestFactory factory;

	public RegularRequestManager() {
		factory = new SearchRequestFactory();
	}


	@Override
	public void submit(SearchOptions options) {

		Long startTime = System.currentTimeMillis();

		List<SearchResult> allResults = new ArrayList<SearchResult>();

		// test craigslist request
		SearchRequest CLRequest = factory.createCraigslistSearchRequest(options);
		SearchRequest CLRequest2 = factory.createCraigslistSearchRequest(options);
		SearchRequest CFRequest = factory.createCarfaxSearchRequest(options);
		SearchRequest CDRequest = factory.createCarsDirectSearchRequest(options);



		try {
			allResults.addAll(CLRequest.call());
			allResults.addAll(CLRequest2.call());
			allResults.addAll(CFRequest.call());
			allResults.addAll(CDRequest.call());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// end timer
		Long finishTime = System.currentTimeMillis();
		System.out.println("Total Single Thread Time: " + Long.toString(finishTime - startTime) + " miliseconds");

		if (! allResults.isEmpty()) {
			ResultsWindow window = new ResultsWindow(allResults);
		}

	}

}
