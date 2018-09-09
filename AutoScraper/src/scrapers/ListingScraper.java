package scrapers;

import listings.Listing;

public abstract class ListingScraper implements Runnable, Scraper {


	abstract protected void addListing();

	public Listing getData()
	{
		Listing test = null;

		return test;
	}

	public void run()
	{

	}

}
