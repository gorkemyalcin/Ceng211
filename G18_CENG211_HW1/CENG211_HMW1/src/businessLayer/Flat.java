package businessLayer;
import java.util.ArrayList;

/**
 * @author Gorkem-Onur
 *
 */
public class Flat {
		
		private int Id;
		private int floorNumber;
		private int flatNumber; 
		private int numberOfRooms;
		private int squareMeter;
		private ArrayList<Bill> listOfBills;

		
		public Flat(int Id, int floorNumber, int flatNumber, int numberOfRooms, int squareMeter, ArrayList<Bill> listOfBills) {
			this.Id = Id;
			this.floorNumber = floorNumber;
			this.flatNumber = flatNumber;
			this.numberOfRooms = numberOfRooms;
			this.squareMeter = squareMeter;
			this.listOfBills = listOfBills;
		}// end constructor.
		
		public String toString() {
			return (Id + ", " + floorNumber + ", " + flatNumber + ", " + numberOfRooms + ", " + squareMeter + ", " + listOfBills.toString());
		}// end toString.
		
		public int getId(){
			return Id;	
		}
		
		@SuppressWarnings("unused")
		private void setId(int Id) {
			if (Id > 0) {
				this.Id = Id;
			}
			else {
				System.out.println("Id has to be positive.");
			}
			return;
		}
		
		public int getFloorNumber() {
			return floorNumber;
		}
		
		@SuppressWarnings("unused")
		private void setFloorNumber(int floorNumber) {
			if (floorNumber > 0) {
				this.floorNumber = floorNumber;
			}
			else {
				System.out.println("Id has to be positive.");
			}
			return;
		}
		
		public int getFlatNumber() {
			return flatNumber;
		}
		
		@SuppressWarnings({"unused" })
		private void setFlatNumber(int flatNumber) {
			if (flatNumber > 0) {
				this.flatNumber = flatNumber;
			}
			else {
				System.out.println("Flat number value can not be negative. No assignment has been done.");
			}
			return;
			
		}
		
		public int getNumberOfRooms() {
			return numberOfRooms;
		}
		
		@SuppressWarnings("unused")
		private void setNumberOfRooms(int numberOfRooms) {
			if (numberOfRooms > 0) {
				this.numberOfRooms = numberOfRooms;
			}
			else {
				System.out.println("Room number value can not be negative. No assignment has been done.");
			}
			return;
		}
		
		public int getSquareMeter() {
			return squareMeter;
		}
		
		@SuppressWarnings("unused")
		private void setSquareMeter(int squareMeter) {
			if (squareMeter > 0) {
				this.squareMeter = squareMeter;
			}
			else {
				System.out.println("Square meter value can not be negative. No assignment has been done.");
			}
		}
		
		public ArrayList<Bill> getListOfBills(){
			return listOfBills;
		}
		
		@SuppressWarnings("unused")
		private void setListOfBills(ArrayList<Bill> listOfBills){
			this.listOfBills = listOfBills;
		}
}
