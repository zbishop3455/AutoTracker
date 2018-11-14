package search_requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.auto_scraper.SearchOptions;
import com.auto_scraper.SearchResult;

public class CraigslistSearchRequest extends SearchRequest{

	List<SearchResult> results;
	SearchOptions options;

	public CraigslistSearchRequest(SearchOptions options){
		this.options = options;

	}

	@Override
	protected List<SearchResult> request() {

		// craft url from search options
		String url = this.createUrl();
		Document doc = null;

		// try to download the page
		try {
			doc = Jsoup.connect(url).get();
			System.out.println(url);

		} catch (IOException e) {
			System.out.println("Error Connecting to CL! ");
		}

		// scrape the page
		ArrayList<SearchResult> results = new ArrayList<SearchResult>();

		if (doc != null){
			results.addAll(this.scrapeListings(doc));
		}


		return results;
	}

	// returns request URL based on search options
	private String createUrl() {

		String base = "https://indianapolis.craigslist.org/search/cta?";

		HashMap<String, String> params = new HashMap<String, String>();

		// add parameters if they exists
		if (this.options.getMinYear() != -1) params.put("max_auto_year", Integer.toString(this.options.getMaxYear()));
		if (this.options.getMaxYear() != -1) params.put("min_auto_year", Integer.toString(this.options.getMinYear()));
		if (this.options.getMinMiles() != -1) params.put("min_auto_miles", Integer.toString(this.options.getMinMiles()));
		if (this.options.getMaxMiles() != -1) params.put("max_auto_miles", Integer.toString(this.options.getMaxMiles()));
		if (this.options.getMinPrice() != -1) params.put("min_price", Integer.toString(this.options.getMinPrice()));
		if (this.options.getMaxPrice() != -1) params.put("max_price", Integer.toString(this.options.getMaxPrice()));
		if (this.options.getMake() != null) params.put("auto_make_model", this.options.getMake());
		if (this.options.getKeywords() != null) params.put("query", this.options.getKeywords());


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

	// scrapes listings from cl document
	private ArrayList<SearchResult> scrapeListings(Document doc) {

		ArrayList<SearchResult> finished = new ArrayList<SearchResult>();

		// select all listings with class "result-row"
		Elements listingDivs = doc.select("li.result-row");

		// loop through each result and parse out info
		for (Element e : listingDivs) {

			try {

				SearchResult r = new SearchResult();

				// parse link
				Element link = e.select("a[href]").first();
				r.setLink(link.attr("href"));

				Elements info = e.select("p.result-info");

				// parse name
				Element name = info.select("a.result-title.hdrlnk").first();
				if (name != null) {
					r.setName(name.text());
					// see if there are 4 digits in a row (probably the year if there are)
					Pattern fourDigitPattern = Pattern.compile("\\d\\d\\d\\d");
					Matcher matcher = fourDigitPattern.matcher(name.text());
					if (matcher.find()) {
						r.setYear(Integer.parseInt(matcher.group(0)));
					}
				}

				// price
				Element price = info.select("span.result-price").first();
				if (price != null) {
					String rawPrice = price.text();
					rawPrice = rawPrice.replace("$", "");
					r.setPrice(Integer.parseInt(rawPrice));
				}

				// location
				Element location = info.select("span.result-hood").first();
				if (location != null) {
					r.setLocation(location.text());
				}

				// time
				Element date = info.select("time.result-date").first();
				if (date != null) {
					r.setListingDate(date.attr("datetime"));
				}

				finished.add(r);
				r.print();

			} catch(Exception ex) {

			}
		}

		System.out.println(finished.size());

		return finished;
	}

}
