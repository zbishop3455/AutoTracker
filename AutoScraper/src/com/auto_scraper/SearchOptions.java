/*
 * Author: Adam Harpenau
 * Date:11/1/18
 * Purpose is to make it were the each of the SearchRequest sites will have a unified
 * search criteria
 */

package com.auto_scraper;

public class SearchOptions
{
	private String keywords = null;
	private String color = null;
	private String make = null;


	// price range
	private int minPrice = -1;
	private int maxPrice = -1;

	// odometer range
	private int minMiles = -1;
	private int maxMiles = -1;

	// year range
	private int minYear = -1;
	private int maxYear = -1;

	public SearchOptions(){

	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getMinMiles() {
		return minMiles;
	}

	public void setMinMiles(int minMiles) {
		this.minMiles = minMiles;
	}

	public int getMaxMiles() {
		return maxMiles;
	}

	public void setMaxMiles(int maxMiles) {
		this.maxMiles = maxMiles;
	}

	public int getMaxYear() {
		return this.maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	public int getMinYear() {
		return this.minYear;
	}

	public void setMinYear(int year){
		this.minYear = year;
	}


}
