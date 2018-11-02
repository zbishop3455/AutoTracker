/*
 * Author: Zachary Bishop
 * Date: 11/1/18
 * Purpose: Provide a unified format for the results of each different website
 */

package com.auto_scraper;

public class SearchResult {

	private String link;
	private String imageLink;
	private String transmisison;
	private int miles;
	private int price;

	public SearchResult() {

	}

	public SearchResult(String link, String imageLink, String transmission, int miles, int price) {
		this.link = link;
		this.imageLink = imageLink;
		this.transmisison = transmission;
		this.miles = miles;
		this.price = price;

	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getTransmisison() {
		return transmisison;
	}

	public void setTransmisison(String transmisison) {
		this.transmisison = transmisison;
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

}
