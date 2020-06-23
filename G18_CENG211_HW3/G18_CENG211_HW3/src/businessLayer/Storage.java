package businessLayer;

import java.util.ArrayList;

public class Storage {
	
private ArrayList<RentableItem> storage = new ArrayList<RentableItem>();
	
	/**
	 * creates storage unit for the store
	 * @param storage
	 */
	public Storage(ArrayList<RentableItem> storage) {
		this.setStorage(storage);
	}

	public Storage() {
		ArrayList<RentableItem> storage = new ArrayList<RentableItem>();
		this.setStorage(storage);
	}
	
	public String toString() {
		return storage.toArray().toString();
	}

	public ArrayList<RentableItem> getStorage() {
		return storage;
	}

	public void setStorage(ArrayList<RentableItem> storage) {
		this.storage = storage;
	}
	
	public void addToStorage(RentableItem item) {
		storage.add(item);
	}
	
	public void removeFromStorage(RentableItem item) {
		storage.remove(item);
	}
}


