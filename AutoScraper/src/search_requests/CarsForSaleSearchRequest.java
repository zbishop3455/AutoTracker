package search_requests;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.auto_scraper.SearchOptions;
import com.auto_scraper.SearchResult;

public class CarsForSaleSearchRequest extends SearchRequest {

	private SearchOptions options;

	public CarsForSaleSearchRequest(SearchOptions options) {
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
			System.out.println("Error Connecting to CarsForSale.com! ");
		}

		// scrape the page
		ArrayList<SearchResult> results = new ArrayList<SearchResult>();

		if (doc != null){
			results.addAll(this.scrapeListings(doc));
		}

		return results;
	}

	ArrayList<SearchResult> scrapeListings(Document doc){

		ArrayList<SearchResult> finished = new ArrayList<SearchResult>();

		Elements listingDivs = doc.select("article.srp-list-item");
		for (Element curListing : listingDivs) {

			SearchResult result = new SearchResult();

			// title
			String title = curListing.selectFirst("span.srp-list-item-basic-info-model").text();
			result.setName(title);

			// price
			Element price = curListing.selectFirst("span.srp-list-item-price");
			int extractedPrice = Integer.parseInt(price.text().replaceAll("[^0-9]", ""));
			result.setPrice(extractedPrice);

			// miles
			Element miles = curListing.selectFirst("span.srp-list-item-basic-info-value");
			int extractedMiles = Integer.parseInt(miles.text().replaceAll("[^0-9]", ""));
			result.setMiles(extractedMiles);

			Element location = curListing.selectFirst("div.srp-list-item-dealership-location");
			result.setLocation(location.text().substring(location.text().indexOf(' '), location.text().indexOf(',')).replaceAll(" ", ""));

			String date = new SimpleDateFormat("MM-dd-yyyy").toString();
			result.setListingDate(date);

			finished.add(result);

		}


		return finished;
	}


	private String createUrl() {

		String base = "https://www.carfax.com/Used-Cars-in-Indianapolis-IN_c19840";

		HashMap<String, String> params = new HashMap<String, String>();

		// add parameters if they exists
		if (this.options.getMinYear() != -1) params.put("MaxModelYear", Integer.toString(this.options.getMaxYear()));
		if (this.options.getMaxYear() != -1) params.put("MinModelYear", Integer.toString(this.options.getMinYear()));
		if (this.options.getMinMiles() != -1) params.put("MinMileage", Integer.toString(this.options.getMinMiles()));
		if (this.options.getMaxMiles() != -1) params.put("MaxMileage", Integer.toString(this.options.getMaxMiles()));
		if (this.options.getMinPrice() != -1) params.put("MinPrice", Integer.toString(this.options.getMinPrice()));
		if (this.options.getMaxPrice() != -1) params.put("MaxPrice", Integer.toString(this.options.getMaxPrice()));
		if (this.options.getMake() != null) params.put("Model", this.options.getMake());

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
