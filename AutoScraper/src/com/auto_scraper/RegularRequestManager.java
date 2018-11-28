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

		List<SearchResult> allResults = new ArrayList<SearchResult>();

		// test craigslist request
		//SearchRequest testCLRequest = factory.createCraigslistSearchRequest(options);

		SearchRequest testCDRequest = factory.createCarsDirectSearchRequest(options);


		try {
			//testCLRequest.call();
			testCDRequest.call();
		} catch (Exception e) {

		}

	}

}
