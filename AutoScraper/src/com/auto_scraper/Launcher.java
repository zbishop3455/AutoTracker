/*	Author: Zachary Bishop
 * 	Created: 8/30/18
 * 	Purpose: To launch the App
 */

package com.auto_scraper;

import crawlers.CraigslistCrawler;

//import org.jsoup.*;


public class Launcher {

	public static void main(String[] args) {

		CraigslistCrawler jeff = new CraigslistCrawler("indianapolis");
		jeff.start();

	}

}


// test push