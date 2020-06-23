package businessLayer;

/**
 * @author Gorkem-Onur
 *
 */
public abstract class RentableItem implements IStorable, IRentable, ISearchable{

	private double price;
	private int id;
	private String name;
	private String type;
	private String rentPolicy;
	private double overdueCharge;
	
	public RentableItem(int id, String name, String type, String rentPolicy) {
		this.setId(id);
		this.setName(name);
		this.setType(type);
		this.setRentPolicy(rentPolicy);
		initializePrices(rentPolicy);
	}
	
	public RentableItem() {
		this.price = -1.0;
		this.id = -1;
		this.name = null;
		this.type = null;
		this.rentPolicy = null;
		this.overdueCharge = -1.0;
	}
	
	/**
	 * Depending on the rent policy initializes price of the item.
	 * @param rentPolicy
	 */
	private void initializePrices(String rentPolicy) {
		if (rentPolicy.toLowerCase().equals("newreleasepolicy")) {
			this.price = 30.0;
			this.overdueCharge = 5.0;
		}
		else if (rentPolicy.toLowerCase().equals("oldreleasepolicy")) {
			this.price = 20.0;
			this.overdueCharge = 3.0;
		}
		else {
			this.price = 10.0;
			this.overdueCharge = 2.0;
		}
	}

	public String getRentPolicy() {
		return rentPolicy;
	}

	public void setRentPolicy(String rentPolicy) {
		this.rentPolicy = rentPolicy;
		initializePrices(rentPolicy);
	}
	
	public double getOverdueCharge() {
		return overdueCharge;
	}

	@SuppressWarnings("unused")
	private void setOverdueCharge(double overdueCharge) {
		this.overdueCharge = overdueCharge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	@SuppressWarnings("unused")
	private void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/* 
	 * We couldn't think how to use these methods, 
	 * For rent, we used storeManager object in our program to accomplish the task. We didn't understand how to give an item a method called rent and make it so
	 * it can rent itself, same with return method.
	 * For search method we needed a storage unit in order to search and again we did the searching in the storeManager class
	 * For storing, we needed to store the whole storage unit into the xml and json files. And again we did that in storeManager class. 
	 * Later on we thought when we were writing the items into xml and json files in a loop, we could've wrote item.storeJSON() but then we had some problems
	 * with PrintWriter so we thought we do the methods the way they are since it is working.
	 */
	@Override
	public void rent() {
	}

	@Override
	public void returnItem() {
	}

	@Override
	public boolean search() {
		return false;
	}

	@Override
	public void storeJSON(RentableItem item) {			

	}

	public void storeXML(RentableItem item) {
	}
}
