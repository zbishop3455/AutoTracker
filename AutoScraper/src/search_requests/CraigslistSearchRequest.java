package search_requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.auto_scraper.SearchOptions;
import com.auto_scraper.SearchResult;

public class CraigslistSearchRequest extends SearchRequest{

	List<SearchResult> results;
	SearchOptions options;

	public CraigslistSearchRequest(SearchOptions options){
		this.options = options;
		new ArrayList<SearchResult>();

	}

	@Override
	protected List<SearchResult> request() {

		// craft url from search options
		String url = this.createUrl();

//		try {
//			Document doc = Jsoup.connect(url).get();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		System.out.println("HELLOW WORLD");

		return null;
	}

	// create request URL for
	private String createUrl() {

		return "";
	}



}
