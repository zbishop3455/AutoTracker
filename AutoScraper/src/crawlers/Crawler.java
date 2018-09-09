// Author: Zachary Bishop
// Craeted: 9/8/18
// Purpose: Interface for all crawlers


package crawlers;

import java.util.LinkedList;;


public interface Crawler {

	// starts crawling process
	public void crawl();


	public LinkedList<String> getUrls();

}
