// Author: Zachary Bishop
// Created: 9/8/18
// Purpose: Provide class that will do a restricted or unrestricted crawling of craigslist


/*
 *
 */

package crawlers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class CraigslistCrawler extends ThreadedCrawler {

	// all crawled links must contain this string
	private String urlRestriction = "craigslist";

	// sets the location of restriction
	private String locationRestriction = "indianapolis";

	// starting url
	private String startingUrl;

	// page to start on
	int startingPage;

	// max pages
	int maxPages;

	// if a type is set, it will restrice the types of urls that are crawled
	private CraigslistCrawlerType type = null;


	public CraigslistCrawler(){
		this.startingPage = 0;
		this.maxPages = 5;
	}


	public CraigslistCrawler(int startingPage, int maxPages){
		this.startingPage = startingPage;
		this.maxPages = maxPages;
	}


	public CraigslistCrawler(String location){
		this.locationRestriction = location;
		this.startingUrl ="https://" + this.locationRestriction + ".craigslist.org/d/cars-trucks/search/cta";
	}


	public CraigslistCrawler(CraigslistCrawlerType t){
		this.urlRestriction = "craigslist";
	}



	// returns page object for urls to be grabbed
	private Document getWebPage(String url) {
		// TODO Auto-generated method stub

		Document page = null;

		try {
			page =  Jsoup.connect(url).get();

		} catch (IOException e) {
			System.out.println("Could not connect to CL page!");
			e.printStackTrace();
		}

		return page;
	}


	// recursivly scrapes all listsings down off a CL page
	// finds link to the next page
	private List<String> crawl() {

		String previousUrl = "";
		String nextUrl = this.startingUrl;
		LinkedList<String> urls = new LinkedList<String>();

		boolean keepCrawling = true;

		long startTime = System.currentTimeMillis();

		while (keepCrawling) {

			// scrape the page
			Document page = this.getWebPage(nextUrl);

			// find all listings
			Elements listings = page.getElementsByClass("result-row");

			// extract links from listings
			for (Element curListing : listings) {
				String curListingUrl = curListing.select("a").first().attr("href");
				urls.add(curListingUrl);
			}

			System.out.println(urls.size());

			previousUrl = nextUrl;

			// Get next page url
			Element nextPage = page.getElementsByClass("button next").first();
			nextUrl = nextPage.absUrl("href");
			System.out.println(nextUrl);


			if (nextUrl.equals(previousUrl)) keepCrawling = false;


		}

		long stopTime = System.currentTimeMillis();

		System.out.println("Finished CL in: " + urls.size() + " in "+ (stopTime - startTime) + "ms");

		return urls;
	}



	@Override
	public void run() {
		this.start();
	}

	@Override
	public void start() {
		System.out.println("Starting");
		List<String> scrapedLinks = this.crawl();
		System.out.println("Finished");
	}


}
