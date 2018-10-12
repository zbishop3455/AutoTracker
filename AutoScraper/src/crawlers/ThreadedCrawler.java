package crawlers;

public abstract class ThreadedCrawler implements Crawler, Runnable {

	public void run() {
		this.start();
	}


}
