package search_requests;

import java.util.List;
import java.util.concurrent.Callable;

import com.auto_scraper.SearchResult;

public abstract class SearchRequest implements Callable<List<SearchResult>> {

	@Override
	public List<SearchResult> call() throws Exception {
		return this.request();
	}

	// this method must set the value results
	protected abstract List<SearchResult> request();



}
