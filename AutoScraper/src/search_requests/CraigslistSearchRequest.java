package search_requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

		try {
			Document doc = Jsoup.connect(url).get();
			System.out.println(doc.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}



		return null;
	}

	// create request URL for
	private String createUrl() {

		String base = "https://indianapolis.craigslist.org/search/cta?";

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("max_auto_year", Integer.toString(this.options.getMaxYear()));
		params.put("min_auto_year", Integer.toString(this.options.getMinYear()));
		params.put("min_auto_miles", Integer.toString(this.options.getMinMiles()));
		params.put("max_auto_miles", Integer.toString(this.options.getMaxMiles()));
		params.put("min_price", Integer.toString(this.options.getMinPrice()));
		params.put("max_price", Integer.toString(this.options.getMaxPrice()));

		// add make if it exists
		if (this.options.getMake() != null) params.put("auto_make_model", this.options.getMake());

		// add keywords if it exists
		if (this.options.getKeywords() != null) params.put("query", this.options.getKeywords());

		// find color


		// create output string
		for (String key : params.keySet()) {
			String curParam = params.get(key);
			base += "&";
			base += key;
			base += "=";
			base += curParam;
		}


		return base;
	}



}
