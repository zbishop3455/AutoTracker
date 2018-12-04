/*
 * Author: Adam Harpeanu
 * 11/1/18
 * Purpose is to go through the Cars direct Website with specific search requests that with give back a
 * list of complete search results
 */

package search_requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.auto_scraper.SearchOptions;
import com.auto_scraper.SearchResult;

public class CarsDirectSearchRequest extends SearchRequest
{
	private SearchResult results;
	
	private SearchOptions options;

	private String link ="https://www.carsdirect.com/used_cars/listings/";


	//private String link = "https://www.carsdirect.com/used_cars/search";	
	//link = "https://www.carsdirect.com/used_cars/listings/";

	public CarsDirectSearchRequest(SearchOptions options)
	{
		this.options=options;
	}

	@Override
	protected List<SearchResult> request()
	{
		try{
			//Connecting the the link after it is made
			Document doc= Jsoup.connect(CreateLink()).get();
			return parseWebsite(doc);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}

	public String CreateLink ()
	{
		//This is just creating the website link just by how the setup of the link works
		if (options.getMake() != null) link += options.getMake() + "/" ;
		else link += "/";
		
		if (options.getKeywords() == null) link += "?";
		else link += options.getKeywords() + "?";
		
		link += "zipcode=46240" + "&dealerId=" + "&distance=";
		//making sure max miles can't equal -1 or the link will crash
		if (options.getMaxMiles() != -1) link += options.getMaxMiles();
		link += "&yearFrom="; 
		if (options.getMinYear() != -1) link += options.getMinYear();
		link += "&yearTo=";
		if (options.getMaxYear() != -1) link += options.getMaxYear();
		link += "&priceFrom="; 
		if(options.getMinPrice() != -1) link += options.getMinPrice();
		link += "&priceTo=";
		if(options.getMaxPrice() != -1) options.getMaxPrice();
		return link; 

		//this is only if "KeyWords" is equivalent to model
		

	}
	
	private ArrayList<SearchResult> parseWebsite(Document doc)
	{
		ArrayList<SearchResult> results = new ArrayList<SearchResult>();
		Elements listingDivs = doc.select("div.list-row");
		System.out.println("Listing div count " + listingDivs.size());
		
		for (Element e: listingDivs )		{
			//go through and parse the list-row to get he right information back
			
			try
			{
				SearchResult r = new SearchResult();
				r.setPlatform("CarsDirect");
				// link
				String link = e.attr("data-listinglink");
				r.setLink(link);
				
				// miles
				Element details = e.selectFirst("div.detail-left");
				
				//getting the price of each car
				Element price = e.selectFirst("span.detail-price-set").selectFirst("span");
				r.setPrice(Integer.parseInt(price.text().replaceAll("[^0-9]", "")));
				
				//Parsing the details to get the miles
				String[] MilesGetter = details.text().split(" ", 3);
				int ConvertMiles = Integer.parseInt(MilesGetter[1].replaceAll("[^0-9]", ""));
				r.setMiles(ConvertMiles);
				
				//Getting the information from the listing and then getting the 
				//year out of the name of the listing
				Elements info = e.select("div.list-details");
				Element name = info.select("div.listing-header").first();
				if(name != null)
				{
					r.setName(name.text());
					try 
					{
						Pattern fourDigitPattern = Pattern.compile("\\d\\d\\d\\d");
						Matcher matcher = fourDigitPattern.matcher(name.text());
						if(matcher.find())
						{
							r.setYear(Integer.parseInt(matcher.group(0)));
						}
					}
					catch(Exception ex)
					{
						
					}
				}
				results.add(r);
				
			}
			catch(Exception ex)
			{
			
				ex.printStackTrace();
			}
		}
		System.out.println(results.size());
		return results;
		
	}
}
