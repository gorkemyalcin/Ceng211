package presentationLayer;

import java.io.IOException;
import java.util.ArrayList;

import businessLayer.Book;
import businessLayer.Customer;
import businessLayer.Movie;
import businessLayer.RentableItem;
import businessLayer.Storage;
import businessLayer.StoreManager;

/**
 * @author Gorkem-Onur
 *
 */
public class StoreApp {
	private static String newReleasePolicy = "NewReleasePolicy";
	private static String oldReleasePolicy = "OldReleasePolicy";
	private static String bargainPolicy = "BargainPolicy";

	
	/**
	 * Since we didn't know if homework wanted user inputs and from whose perspective we should do the simulation, we created some objects and tested it
	 * In this example in initializeStorage we initialize the storage with given items and in main we create customers and storage and storemanager objects
	 * between line 32 and 35 we first check if the given author has any books in the storage and assigns them to rentList1. then if size is greater than 0
	 * which means we found some books with the given authors name as parameter, we rent the items to the customer.
	 * 
	 * Between lines 40 and 43 we rent a movie to customer and this movie is the only one with the adventure genre.
	 * 
	 * In the 3rd rent assignment we again try to rent items to the customer but this time we can not rent, because there is no more adventure movies left in the storage
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		Storage storage = initializeStorage();
		StoreManager storeManager = new StoreManager(storage);
		Customer customer1 = new Customer("silver", 1);
		Customer customer2 = new Customer("regular", 2);
		Customer customer3 = new Customer("gold", 3);
		ArrayList<RentableItem> rentList1 = storeManager.searchWithOneAttribute("George R.R. Martin", 1);
		boolean successful1 = rentList1.size() != 0; // if items were found or not
		if (successful1) {
			storeManager.rentToCustomer(customer1, rentList1, "book");
		}
		ArrayList<RentableItem> rentList2 = storeManager.searchWithOneAttribute("Tom Hanks", 6);
		boolean successful2 = rentList2.size() != 0; // if items were found or not
		if (successful2) {
			storeManager.rentToCustomer(customer2, rentList2, "movie");
		}
		ArrayList<RentableItem> rentList3 = storeManager.searchWithOneAttribute("adventure", 4);
		boolean successful3 = rentList3.size() != 0; // if items were found or not
		if (rentList3.size() != 0) {
			storeManager.rentToCustomer(customer3, rentList3, "movie");
		}
		System.out.println("Storage contents are " + storage.getStorage().toString());
		if(successful1) {
			System.out.println("Customer has to pay " + customer1.getInvoice().getPrice() + " liras.");
			storeManager.returnFromCustomer(customer1);
		}
		if(successful2) {
			System.out.println("Customer has to pay " + customer2.getInvoice().getPrice() + " liras.");
			storeManager.returnFromCustomer(customer2);
			System.out.println(customer2.getInvoice().getPrice());
		}
		if(successful3) {
			//Doesn't enter here because there is no more adventure movie left
			System.out.println("Customer has to pay " + customer1.getInvoice().getPrice() + " liras.");
			storeManager.returnFromCustomer(customer3);
		}
		System.out.println("Storage contents are " + storage.getStorage().toString());
		System.out.println(storeManager.searchWithOneAttribute("A Clash of Kings", 2));
		System.out.println(storeManager.searchWithOneAttribute("Tom hanks", 6));
		storeManager.updateFiles();

	}
	
	/**
	 * Creates the storage with the given objects.
	 * @return the storage which is an ArrayList<RentableItem>
	 */
	private static Storage initializeStorage() {
		Storage storage = new Storage();
		RentableItem movie1 = new Movie(1, "Angels and Demons", "movie", oldReleasePolicy, "Tom Hanks", "adventure", "Ron Howard");
		RentableItem movie2 = new Movie(2, "Mission Impossible", "movie", newReleasePolicy, "Tom Cruise", "action", "Paula Wagner");
		RentableItem movie3 = new Movie(3, "Interstellar", "movie", oldReleasePolicy, "Matthew McConaughey", "mystery", "Christopher Nolan");
		RentableItem book1 = new Book(4, "A Clash of Kings", "book", bargainPolicy, "George R.R. Martin", "Bantam Books");
		RentableItem book2 = new Book(5, "The Winds of Winter", "book", newReleasePolicy, "George R.R. Martin", "Bantam Books");
		RentableItem book3 = new Book(6, "A Storm of Swords", "book", bargainPolicy, "George R.R. Martin", "Bantam Books");
		storage.addToStorage(movie1);
		storage.addToStorage(movie2);
		storage.addToStorage(movie3);
		storage.addToStorage(book1);
		storage.addToStorage(book2);
		storage.addToStorage(book3);
		return storage;
	}
}
