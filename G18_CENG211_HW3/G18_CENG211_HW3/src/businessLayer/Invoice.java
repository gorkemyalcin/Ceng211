package businessLayer;

import java.util.ArrayList;
import java.util.Calendar;

public class Invoice {

	private Calendar rentDate;
	private Calendar returnDate;
	private ArrayList<RentableItem> itemList;
	private double price;
	private Customer customer;

	/**
	 * @param customer Every invoice will have a customer bound to it. Invoice has-a customer
	 * @param itemList rented items
	 * @param type book or movie
	 */
	public Invoice(Customer customer, ArrayList<RentableItem> itemList, String type) {
		Calendar rentDate = Calendar.getInstance();
		Calendar returnDate = Calendar.getInstance();
		if (type.toLowerCase().equals("movie")) {
			returnDate.add(Calendar.DATE, 2);//if movie return date is current date + 2, else its current date + 7
		}
		else {
			returnDate.add(Calendar.DATE, 7);
		}
		double price = 0.0;
		for (RentableItem item:itemList) {
			price = price + item.getPrice();
		}
		price = price -  (price * customer.getDiscount() / 100);
		this.setPrice(price);
		this.setRentDate(rentDate);
		this.setReturnDate(returnDate);
		this.setItemList(itemList);
		this.setCustomer(customer);
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}

	public Calendar getRentDate() {
		return rentDate;
	}

	public void setRentDate(Calendar rentDate) {
		this.rentDate = rentDate;
	}

	public Calendar getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Calendar returnDate) {
		this.returnDate = returnDate;
	}

	public ArrayList<RentableItem> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<RentableItem> itemList) {
		if (itemList.size() < 1) {
			System.out.println("Invoice can not be created for no items!");
		}
		else {
			this.itemList = itemList;
		}
	}
}
