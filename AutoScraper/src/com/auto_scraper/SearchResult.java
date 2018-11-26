/*
 * Author: Zachary Bishop
 * Date: 11/1/18
 * Purpose: Provide a unified format for the results of each different website
 */

package com.auto_scraper;

public class SearchResult {

	private String name;
	private String link;
	private String listingDate;
	private String location;
	// platform is where the listing came from ex: Craigslist
	private String platform;
	private int miles;
	private int price;
	private int year;

	public SearchResult() {

	}

	public void print() {
		String p = "";
		p += this.name;
		p += " | ";
		p += Integer.toString(this.year);
		System.out.println(p);
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getListingDate() {
		return this.listingDate;
	}

	public void setListingDate(String listingDate) {
		this.listingDate = listingDate;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
