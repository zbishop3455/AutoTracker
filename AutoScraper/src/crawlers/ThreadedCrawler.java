package crawlers;

import java.util.HashMap;

public abstract class ThreadedCrawler implements Crawler, Runnable {

	public void run() {
		this.start();
	}


}
