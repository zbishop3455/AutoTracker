// Author: Zachary Bishop
// Created: 9/8/18
// Purpose: Provide class that will do a restricted or unrestricted crawling of craigslist


/*
 *
 */

package crawlers;

import java.io.IOException;
import java.util.LinkedList;

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
		this.maxPages = 1000;
	}


	public CraigslistCrawler(int startingPage, int maxPages){
		this.startingPage = startingPage;
		this.maxPages = maxPages;
	}


	public CraigslistCrawler(String location){
		this.startingPage = 0;
		this.maxPages = 1000;
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
	private void crawl(int curDepth, int maxDepth, String pageUrl) {

		// scrape the page
		Document page = this.getWebPage(pageUrl);

		// find all listings
		Elements listings = page.getElementsByClass("result-row");

		// extract links from listings
		LinkedList<String> urls = new LinkedList<String>();
		for (Element curListing : listings) {
			String curUrl = curListing.select("a").first().attr("href");
			System.out.println(curUrl);
			urls.add(curUrl);
		}

		System.out.println(urls.size());

	}



	@Override
	public void run() {
		this.start();
	}

	@Override
	public void start() {
		System.out.println("Starting");
		this.crawl(0, this.maxPages, this.startingUrl);
		System.out.println("Finished");
	}


}
