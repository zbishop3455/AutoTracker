/*
 * Author: Adam Harpeanu
 * 11/1/18
 * Purpose is to go through the Cars direct websites with specific search requests that with give back a 
 * list of complete search results 
 */

package search_requests;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.auto_scraper.SearchOptions;
import com.auto_scraper.SearchResult;

public class CarsDirectSearchRequest extends SearchRequest
{
	private SearchOptions options;
	//private String link = "https://www.carsdirect.com/used_cars/search";	
	private String link = "https://www.carsdirect.com/used_cars/listings/";
	public CarsDirectSearchRequest(SearchOptions options)
	{
		this.options=options;
	}
	
	@Override
	protected List<SearchResult> request() 
	{
		try{
			//System.out.println("Hello World");
			Document doc= Jsoup.connect(link).get();
			String title = doc.title();
			String body= doc.body().text();
			System.out.println(title);
			System.out.println(body);
			CreateLink();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void CreateLink ()
	{
		//this is only if "KeyWords" is equivalent to model
		try
		{
			if (options.getMake() != null && options.getKeywords() != null)
			{
				link = link + options.getMake() + "/" + options.getKeywords() + "?" + "zipcode=46240" + "&dealerId=" + "&distance=" + options.getMaxMiles();
				link = link + "&yearFrom=&yearTo=" + "&priceFrom=" + options.getMinPrice() + "&priceTo=" + options.getMaxPrice();
			}
			else 
			{
				throw new NoSuchFieldException("The make or model was not specified"); 
			}
			// hi
		}
		catch(NoSuchFieldException e)
		{
			System.out.println("No specified make or model");
		}
		System.out.println(link);
	}
}
