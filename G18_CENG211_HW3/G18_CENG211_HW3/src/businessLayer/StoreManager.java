package businessLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import dataAccessLayer.StorageFileCreator;

public class StoreManager {

	private ArrayList<Invoice> invoiceList;
	private Storage storage;
	
	/**
	 * Creates manager with a storage unit bound to it
	 * @param storage
	 */
	public StoreManager(Storage storage) {
		this.setStorage(storage);
		ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
		this.setInvoiceList(invoiceList);
	}
	
	/**
	 * changes an items policy with the parameter.
	 * @param item, item thats policy will be changed
	 * @param policy, items policy will be changed into
	 */
	public void changeRentPolicy(RentableItem item, String policy) {
		item.setRentPolicy(policy);
	}
	
	/**
	 * adds an item to storage
	 * @param item
	 * @throws IOException
	 */
	public void addItem(RentableItem item)throws IOException {
		storage.addToStorage(item);
		updateFiles();
	}
	
	/**
	 * Updates the json and xml files. We thought about making a decision parameter to change the method into a selectable writer methods,
	 * but since we are just writing the storage unit into the files we thought there will be no point in updating different files in different times,
	 * since there will be 2 different storage files with different contexts but when we update them they'll both become the same.
	 *  So we are updating both XML and JSON files here with the current storage,
	 *  and calling this method in every mutator that changes the storage to update the files
	 * @throws IOException
	 */
	public void updateFiles() throws IOException {//json and xml
		Storage storage = getStorage();
		StorageFileCreator.StorageFileWriterJSON(storage);
		StorageFileCreator.StorageFileWriterXML(storage);
	}
	
	/**
	 * Finds the invoice thats bound to the customer, using 
	 * @param customer
	 * @return invoice object if found, null otherwise
	 */
	private Invoice findInvoice(Customer customer) {
		for (Invoice invoice:invoiceList) {
			if (invoice.getCustomer().getCustomerNumber() == customer.getCustomerNumber()) {
				return invoice;
			}
		}
		System.out.println("No such invoice for the given id");
		return null;
	}
	
	/**
	 * @param daysPassedWithoutReturning
	 * @param returnedInvoice, it will update the price on the given invoice with the overdue charge.
	 * @return
	 */
	private double calculateOverdueCharge(int daysPassedWithoutReturning, Invoice returnedInvoice) {
		double result = 0.0;
		for (RentableItem item:returnedInvoice.getItemList()) {
			result = result + item.getOverdueCharge();
		}
		result = daysPassedWithoutReturning * result;
		return result;
	}
	
	/**
	 * Method takes the customer as a parameter, then gets the invoice that is bound to the customer, then if the return date is passed, calculates overdue charge
	 * and sets the new price that customer has to pay. Then returns the items into the storage and updates the xml and json files.
	 * @param customer Customer object that will return the items
	 * @throws IOException
	 */
	public void returnFromCustomer(Customer customer) throws IOException {
		Invoice returnedInvoice = findInvoice(customer);// finds the invoice info, so that we do not need to take parameter from customer about 
														  //item id  or date entered, those informations are stored in invoice.						  
		Calendar currentDate = Calendar.getInstance();
		if(returnedInvoice.getReturnDate().after(currentDate)) {
			int daysPassedWithoutReturning = currentDate.get(Calendar.DAY_OF_YEAR) - returnedInvoice.getReturnDate().get(Calendar.DAY_OF_YEAR);
			double overdueCharge = calculateOverdueCharge(daysPassedWithoutReturning, returnedInvoice);
			returnedInvoice.setPrice(returnedInvoice.getPrice() + overdueCharge);
			}
		ArrayList<RentableItem> returnedItems = returnedInvoice.getItemList();
		for (RentableItem item:returnedItems) {
			storage.addToStorage(item);
		}
		updateFiles();
		returnedInvoice.setPrice(0);
	}
	
	/**
	 * To search if the items that customer wants to rent are in the storage
	 * @param rentList
	 * @return true if all the items are in the storage. false if itemlist is 0 or storage doesn't contain an item that is in the itemlist.
	 */
	private boolean searchTheWantedItems(ArrayList<RentableItem> rentList) {
		if (rentList.size() > 0) {
			for (RentableItem item:rentList) {
				if (!storage.getStorage().contains(item)) {
					return false;
				}
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * First searches the items in the storage and returns a boolean value, if true creates an invoice with customer rentlist and type
	 * Sets the invoice to customers invoice attribute. Adds the invoice to invoiceList(Manager controls the invoice list) then removes items from the storage.
	 * Finally updates xml and json files.
	 * @param customer Customer that wants to rent the items
	 * @param rentList Items that customer wants to rent
	 * @param type type of items that will be rented
	 * @throws IOException
	 */
	public void rentToCustomer(Customer customer, ArrayList<RentableItem> rentList, String type) throws IOException {
		boolean search = searchTheWantedItems(rentList);		
		if (search == true) {
			if (rentList.size() > 0) {
				Invoice rentInvoice = new Invoice(customer, rentList, type);
				customer.setInvoice(rentInvoice);
				invoiceList.add(rentInvoice);
				for (RentableItem item:rentList) {
					storage.removeFromStorage(item);
				}
			}
			updateFiles();
		}
		else {
			System.out.println("Wanted item does not exist in the storage!");
		}
	}
	
	/**
	 * Searches the storage with the given decision and returns the found foundList (since there could be a author with 2 books, shouldn't just return the first appearance)
	 * @param attribute
	 * @param decision 1 for author, 2 for name, 3 for publisher, 4 for genre, 5 for producer, 6 for actor.
	 * @return foundList, items that are found
	 */
	public ArrayList<RentableItem> searchWithOneAttribute(String attribute, int decision) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		if (decision == 1) {
			foundList = searchBookByAuthor(attribute);
		}
		else if (decision == 2) {
			foundList = searchBookByName(attribute);
		}
		else if (decision == 3) {
			foundList = searchBookByPublisher(attribute);
		}
		else if (decision == 4) {
			foundList = searchMovieByGenre(attribute);
		}
		else if (decision == 5) {
			foundList = searchMovieByProducer(attribute);
		}
		else if (decision == 6) {
			foundList = searchMovieByActor(attribute);
		}
		return foundList;
	}
	
	/**
	 *  Searches the storage with the given decision and returns the found foundList (since there could be a author with 2 books, shouldn't just return the first appearance)
	 * @param attribute1
	 * @param attribute2
	 * @param decision  1 for author and name, 2 for author and publisher, 3 for publisher and name, 4 for genre and actor, 5 for producer and actor, 6 for genre and producer.
	 * @return foundList, items that are found
	 */
	public ArrayList<RentableItem> searchWithTwoAttribute(String attribute1, String attribute2, int decision) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		if (decision == 1) {
			foundList = searchBookByAuthorAndName(attribute1, attribute2); 
		}
		else if (decision == 2) {
			foundList = searchBookByAuthorAndPublisher(attribute1, attribute2); 
		}
		else if (decision == 3) {
			foundList = searchBookByNameAndPublisher(attribute1, attribute2); 
		}
		else if (decision == 4) {
			foundList = searchMovieByActorAndGenre(attribute1, attribute2); 
		}
		else if (decision == 5) {
			foundList = searchMovieByActorAndProducer(attribute1, attribute2); 
		}
		else if (decision == 6) {
			foundList = searchMovieByGenreAndProducer(attribute1, attribute2); 
		}
		return foundList;
	}

	private ArrayList<RentableItem> searchBookByAuthorAndName(String author, String name) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("book")) {
				if (((Book) item).getAuthor().toLowerCase().equals(author.toLowerCase())){
					if(item.getName().toLowerCase().equals(name.toLowerCase())) {
						foundList.add(item);
					}
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchBookByAuthorAndPublisher(String author, String publisher) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("book")) {
				if (((Book) item).getAuthor().toLowerCase().equals(author.toLowerCase())) {
					if(((Book) item).getPublisher().toLowerCase().equals(publisher.toLowerCase())) {
						foundList.add(item);
					}
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchBookByNameAndPublisher(String name, String publisher) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("book")) {
				if (((Book) item).getName().toLowerCase().equals(name.toLowerCase())) {
					if(((Book) item).getPublisher().toLowerCase().equals(publisher.toLowerCase())) {
						foundList.add(item);
					}
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchMovieByActorAndGenre(String actor, String genre) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("movie")) {
				if (((Movie) item).getActor().toLowerCase().equals(actor.toLowerCase())) {
					if(((Movie) item).getGenre().toLowerCase().equals(genre.toLowerCase())) {
						foundList.add(item);
					}
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchMovieByActorAndProducer(String actor, String producer) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("movie")) {
				if (((Movie) item).getActor().toLowerCase().equals(actor.toLowerCase())) {
					if(((Movie) item).getProducer().toLowerCase().equals(producer.toLowerCase())) {
						foundList.add(item);
					}
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchMovieByGenreAndProducer(String genre, String producer) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("movie")) {
				if (((Movie) item).getGenre().toLowerCase().equals(genre.toLowerCase())) {
					if(((Movie) item).getProducer().toLowerCase().equals(producer.toLowerCase())) {
						foundList.add(item);
					}
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchBookByAuthor(String author) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("book")) {
				if (((Book) item).getAuthor().toLowerCase().equals(author.toLowerCase())){
					foundList.add(item);
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchBookByName(String name) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("book")) {
				if (((Book) item).getName().toLowerCase().equals(name.toLowerCase())){
					foundList.add(item);
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchBookByPublisher(String publisher) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("book")) {
				if (((Book) item).getPublisher().toLowerCase().equals(publisher.toLowerCase())){
					foundList.add(item);
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchMovieByGenre(String genre) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("movie")) {
				if (((Movie) item).getGenre().toLowerCase().equals(genre.toLowerCase())){
					foundList.add(item);
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchMovieByProducer(String producer) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("movie")) {
				if (((Movie) item).getProducer().toLowerCase().equals(producer.toLowerCase())){
					foundList.add(item);
				}
			}
		}
		return foundList;
	}
	
	private ArrayList<RentableItem> searchMovieByActor(String actor) {
		ArrayList<RentableItem> foundList = new ArrayList<RentableItem>();
		for (RentableItem item:storage.getStorage()) {
			if (item.getType().toLowerCase().equals("movie")) {
				if (((Movie) item).getActor().toLowerCase().equals(actor.toLowerCase())){
					foundList.add(item);
				}
			}
		}
		return foundList;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	
}
