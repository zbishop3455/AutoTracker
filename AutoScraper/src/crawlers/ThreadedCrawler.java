package crawlers;

import java.util.LinkedList;

public abstract class ThreadedCrawler implements Crawler, Runnable {

	protected LinkedList<String> foundUrls;

	// number of links before the crawler finishes
	protected int maxUrls = 1000;

	public abstract void run();


}
