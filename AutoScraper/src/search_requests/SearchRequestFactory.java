/*
 * Author: Adam Harpenau
 * 11/1/18
 * The purpose of this is to complete the factory method to obtain the specific search request
 * sites
 */

package search_requests;

import com.auto_scraper.SearchOptions;

public class SearchRequestFactory
{

	public SearchRequestFactory(){

	}

	public SearchRequest createCraigslistSearchRequest(SearchOptions options){
		return new CraigslistSearchRequest(options);
	}

	public SearchRequest createCarsDirectSearchRequest(SearchOptions options){
		return new CarsDirectSearchRequest(options);

	}

}
