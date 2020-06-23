package businessLayer;

import java.io.IOException;
import java.util.ArrayList;

import dataAccessLayer.FileIOApartment;

/**
 * @author Gorkem-Onur
 *
 */
public class Apartment {

	private Flat[][] flatObjects;
	private int maxFloorNumber;
	
	public Apartment(String fileName1,String fileName2) throws IOException {
		create2DApartment(fileName1, fileName2);
	}// end default constructor.
	
	
	/** Initializes the apartment array in the constructor.
	 * @param fileName1 The file name of the apartment information file.
	 * @param fileName2 The file name of the billing information file.
	 */
	
	private void create2DApartment(String fileName1,String fileName2) throws IOException {
		ArrayList<Flat> flatList = FileIOApartment.apartmentInfoReader(fileName1, fileName2);
		maxFloorNumber = maxFloorFinder(flatList);
		flatObjects = new Flat[maxFloorNumber][2];
		int floorNumber;
		int flatNumber;
		for(Flat flat:flatList) {
			floorNumber = flat.getFloorNumber();
			flatNumber = flat.getFlatNumber();
			flatObjects[floorNumber-1][flatNumber-1] = flat;
			}
	}// end create2DApartment.
	
	/** Finds and returns the maximum value of the floor in the apartment array.
	 * @param flatList the ArrayList which includes all flat information.
	 *
	 **/
	
	
	private int maxFloorFinder(ArrayList<Flat> flatList){
		int maxId = 0;
		for (Flat flat:flatList) {
			if(flat.getFloorNumber() > maxId) {
				maxId = flat.getFloorNumber();
			}
		}
		return maxId;
	}// end maxFloorFinder.
	
	public Flat[][] getFlatObjects(){
		return flatObjects;
	}// end getFlatObjects.
	
	public String toString(){
		String apartment = "";
		for (int i=0; i < maxFloorNumber; i++) {
			for (int j=0; j<2; j++) {
				apartment += flatObjects[i][j];
				if(j == 0) {
					apartment += "|";
				}
			}
			apartment += "\n";
		}
		return apartment;
	}// end toString.
	
}// end Apartment.
