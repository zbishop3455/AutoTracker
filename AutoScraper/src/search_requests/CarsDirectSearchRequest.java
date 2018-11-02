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
	private String link;
	
	public CarsDirectSearchRequest(SearchOptions options)
	{
		this.options=options;
	}
	
	@Override
	protected List<SearchResult> request() 
	{
		try{
			Document doc= Jsoup.connect(link).get();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void CreateLink ()
	{
		
	}
}
