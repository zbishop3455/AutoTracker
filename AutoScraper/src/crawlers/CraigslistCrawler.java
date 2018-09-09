// Author: Zachary Bishop
// Created: 9/8/18
// Purpose: Provide class that will do a restricted or unrestricted crawling of craigslist


package crawlers;

import java.util.LinkedList;

public class CraigslistCrawler extends ThreadedCrawler {

	// all crawled links must contain this string
	private String urlRestriction;

	// sets the location of restriction
	private String locationRestriction;

	// starting url
	private String startingUrl;


	public CraigslistCrawler(){
		this.urlRestriction = "craigslist";
		this.locationRestriction = "indianapolis";
		this.startingUrl = "https://indianapolis.craigslist.org/";
	}


	public CraigslistCrawler(String startingUrl){

	}


	public CraigslistCrawler(String startingUrl, String urlRestriction){

	}


	public CraigslistCrawler(String startingUrl, String urlRestriction, String locationRestriction){

	}



	@Override
	public void crawl() {
		// TODO Auto-generated method stub

	}

	@Override
	public LinkedList<String> getUrls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}








}
