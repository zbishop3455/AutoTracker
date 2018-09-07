package listings;

public class CraigslistListing implements Listing {


	private int price;
	private int type;
	private int year;
	private String url;
	private String location;



	public CraigslistListing(String url)
	{
		this.url = url;
	}


	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getYear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getModel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

}
