// Author: Zachary Bishop
// Created: 8/30/18
// Purpose: Interface ensuring all scrapers can scrape and return their results in a common listing type

package scrapers;

public interface Scraper {

	public void scrape();

	// returns a uniform data object
	public void getData();

}
