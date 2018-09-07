// Author: Zachary Bishop
// Created: 8/31/18
// Purpose: Base type for scraped data


package listings;

public interface Listing {


	public int getPrice();

	public int getType();

	public int getYear();

	public int getModel();

	public String getUrl();

	public String getLocation();




}
