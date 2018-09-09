// Author: Zachary Bishop
// Created: 9/8/18
// Purpose: Provide class that will do a restricted or unrestricted crawling of craigslist


/*
 *
 */

package crawlers;

import java.util.LinkedList;

public class CraigslistCrawler extends ThreadedCrawler {

	// all crawled links must contain this string
	private String urlRestriction = "craigslist";

	// sets the location of restriction
	private String locationRestriction = "indianapolis";

	// starting url
	private String startingUrl;

	// if a type is set, it will restrice the types of urls that are crawled
	private CraigslistCrawlerType type = null;


	public CraigslistCrawler(){
		this.init();
	}


	public CraigslistCrawler(String location){
		this.locationRestriction = location;
		this.init();
	}


	public CraigslistCrawler(CraigslistCrawlerType t){
		this.urlRestriction = "craigslist";
		this.init();
	}



	private void init(){

		this.foundUrls = new LinkedList<String>();

		this.startingUrl ="https://" + this.locationRestriction + ".craigslist.org/";
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


	public void setStartingUrl(String url){
		this.startingUrl = url;

	}


	public void setMaxUrls(int number) throws Exception{
		if ( 0 < number) this.maxUrls = number;
		else throw new Exception("Invalid Number");
	}



}
