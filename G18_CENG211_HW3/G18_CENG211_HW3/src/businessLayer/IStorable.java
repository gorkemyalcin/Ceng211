package businessLayer;

public interface IStorable {
	
	public void storeJSON(RentableItem item);
	
	public void storeXML(RentableItem item);
}