package businessLayer;

import java.util.GregorianCalendar;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * @author Gorkem-Onur
 *
 */
public class BillQuery {
			
		/**
		 * @param flatObjects Array that holds all the flat objects
		 * @return floorNumber Maximum floor number in the apartment
		 */
		private static int findFloorNumber(Flat[][] flatObjects) {
			int floorNumber = 0;
			floorNumber = flatObjects.length;
			return floorNumber;
		}
			
		/**
		 * @param flatObjects Array that holds all the flat objects
		 * @param billId Requested bill's id
		 * @return result An array that contains the following information. integer floorNumber, integer flatNumber and an integer that confirms if bill is found or not.
		 */
		private static int[] findFloorFlatBillNumbersWithBillId(Flat[][] flatObjects, int billId) {
				int[] result = new int[3];
				int floorNumber = findFloorNumber(flatObjects);
				for (int i = 0; i < floorNumber; i++) {
					int flatNumber = flatObjects[i].length;
						for (int j = 0; j < flatNumber; j++) {
							int counter = -1;
							for (Bill bill:flatObjects[i][j].getListOfBills()) {
								counter++;
								if(bill.getBillId() == billId) {
									result[0] = i;
									result[1] = j;
									result[2] = counter;
								}
							}
						}
					}
				return result;
			}
		
		/**
		 * @param flatObjects Array that holds all the flat objects
		 * @param billId Requested bill's id
		 * @param paymentInfo Payment information that will change the current value
		 * @return result If method works clear, returns true else false.
		 * @throws IOException
		 */
		public static boolean changePaymentInfo(Flat[][] flatObjects, int billId, String paymentInfo) throws IOException {
			boolean result = false;
			Calendar currentDate = Calendar.getInstance();
			int floorNumber = findFloorFlatBillNumbersWithBillId(flatObjects, billId)[0];
			int flatNumber = findFloorFlatBillNumbersWithBillId(flatObjects, billId)[1];
			int billIndex = findFloorFlatBillNumbersWithBillId(flatObjects, billId)[2];
			if (billIndex != -1) {
				flatObjects[floorNumber][flatNumber].getListOfBills().get(billIndex).setPaymentInfo(paymentInfo);
				flatObjects[floorNumber][flatNumber].getListOfBills().get(billIndex).setLastUpdateDate(currentDate);
				result = true;
			}
			recreateNewFile(flatObjects);
			return result;
		}
		
		/**recreates the bill file after changing the payment information
		 * @param flatObjects Array that holds all the flat objects
		 * @throws IOException
		 */
		@SuppressWarnings("deprecation")
		private static void recreateNewFile(Flat[][] flatObjects) throws IOException {
			Calendar currentDate = Calendar.getInstance();
			File newFile = new File("HW1-BillingInfo-2018" + "-" + (currentDate.getTime().getMonth()+1) + "-" + currentDate.getTime().getDate()+ ".csv");
			newFile.createNewFile();
			PrintWriter fileWriter = new PrintWriter(newFile);
			ArrayList<Bill> billList = getAllBills(flatObjects);
			for (Bill bill:billList) {
				fileWriter.println(bill.toString()+"\n");
			}
			fileWriter.close();
			File deleteFile = new File(findDeleteFile());
			if (!deleteFile.toString().equals(newFile.getAbsolutePath())) {
				deleteFile.delete();
			}
			return;
		}
		
		/** finds the file that is used already and will be deleted after method is conclduded.
		 * @return deleteFile a string which will be deleted in another method.
		 */
		private static String findDeleteFile() {
			String deleteFile = null;
			File directory = new File(System.getProperty("user.dir"));
			File[] allFilesInDir = directory.listFiles();
			int count = 0;
			for(File file:allFilesInDir) {
				if (file.toString().endsWith(".csv")) {
						count++;
						if (count == 2) {
							deleteFile = file.toString();
							break;
						}			
				}
			}
			return deleteFile;
		}
		
		/**A method for listing all the flats
		 * @param flatObjects Array that holds all the flat objects
		 */
		public static void listAllFlats(Flat[][] flatObjects) {
			int floorNumber = findFloorNumber(flatObjects);
			for (int i = 0; i<floorNumber; i++) {
				for(int j = 0; j<flatObjects[i].length; j++) {
					System.out.println(flatObjects[i][j].toString());
				}
			}
		}
		
		/**
		 * @param flatObjects An array that holds all the flat objects
		 * @param flatId An integer which corresponds the the flat's id
		 * @return result An array which stores the corresponding data. Floor number, Flat number and an integer which returns 1 if method worked clearly.
		 */
		private static int[] findFloorFlatNumbersWithFlatId(Flat[][] flatObjects, int flatId) {
			int[] result = new int[3];
			result[2] = -1;
			int floorNumber = findFloorNumber(flatObjects);
			for (int i = 0; i < floorNumber; i++) {
				int flatNumber = flatObjects[i].length;
					for (int j = 0; j < flatNumber; j++) {
						if(flatObjects[i][j].getId() == flatId) {
								result[0] = i;
								result[1] = j;
								result[2] = 1;
								break;
							}
						}
					}
			return result;
		}
		
		/**Lists all the bills of a specific flat.
		 * @param flatObjects Array that holds all the flat objects
		 * @param flatId An integer which corresponds the the flat's id
		 */
		public static void listAllBillsOfAFlat(Flat[][] flatObjects, int flatId) {
			int floorNumber = findFloorFlatNumbersWithFlatId(flatObjects, flatId)[0];
			int flatNumber = findFloorFlatNumbersWithFlatId(flatObjects, flatId)[1];
			int found = findFloorFlatNumbersWithFlatId(flatObjects, flatId)[2];
				if(found != -1) {
					for(Bill bill:flatObjects[floorNumber][flatNumber].getListOfBills()) {
						System.out.println(bill.toString());
					}
				}
				else {
					System.out.println("Asked flat doesn't exist in the database.");
				}
		}
		
		/** Returns an ArrayList which consists of all bills.
		 * @param flatObjects Array that holds all the flat objects
		 * @return an ArrayList which consists of all bills.
		 */
		public static ArrayList<Bill> getAllBills(Flat[][] flatObjects){
			ArrayList<Bill> billList = new ArrayList<Bill>();
			int floorNumber = findFloorNumber(flatObjects);
			for (int i = 0; i < floorNumber; i++) {
				for(int j = 0; j<flatObjects[i].length; j++) {
					for(Bill bill:flatObjects[i][j].getListOfBills()) {
						billList.add(bill);
					}
				}
			}
			return billList;
		}
		
		/** Prints all the bills
		 * @param flatObjects Array that holds all the flat objects
		 */
		public static void printAllBills(Flat[][] flatObjects) {
			ArrayList<Bill> billList = getAllBills(flatObjects);
			for (Bill bill:billList) {
				System.out.println(bill.toString());
			}
		}
		
		/** Prints all the unpaid bills
		 * @param flatObjects Array that holds all the flat objects
		 */
		public static void listOfAllUnpaidBills(Flat[][] flatObjects) {
			ArrayList<Bill> billList = getAllBills(flatObjects);
				for (Bill bill:billList) {
					if (bill.getPaymentInfo().toLowerCase().equals("false")) {
						System.out.println(bill.toString());
				}
			}
		}
		
		/** Returns the value of all unpaid bills' amount.
		 * @param flatObjects Array that holds all the flat objects
		 * @return an integer which corresponds to the total amount of unpaid bills
		 */
		public static int totalAmountOfUnpaidBills(Flat[][] flatObjects) {
			int amount = 0;
			ArrayList<Bill> billList = getAllBills(flatObjects);
			for (Bill bill:billList) {
				if (bill.getPaymentInfo().toLowerCase().equals("false")) {
					amount = amount + bill.getAmount();
				}
			}
			return amount;
		}
		
		
		/** Prints all the unpaid bills of specific type
		 * @param flatObjects Array that holds all the flat objects
		 * @param billType A string which corresponds to the bill type
		 */
		public static void listOfAllUnpaidBillsOfASpecificType(Flat[][] flatObjects, String billType) {
			ArrayList<Bill> billList = getAllBills(flatObjects);
			for (Bill bill:billList)
					if (bill.getType().toLowerCase().equals(billType.toLowerCase())) {
						if (bill.getPaymentInfo().toLowerCase().equals("false")) {
							System.out.println(bill.toString());
				}
			}
		}
		
		/** Finds and returns the total number of unpaid bills
		 * @param flatObjects Array that holds all the flat objects
		 * @return result An integer which corresponds to the total number of unpaid bills
		 */
		public static int totalNumberOfUnpaidBills(Flat[][] flatObjects) {
			int result = 0;
			ArrayList<Bill> billList = getAllBills(flatObjects);
			for (Bill bill:billList) {
				if(bill.getPaymentInfo().toLowerCase().equals("false")) {
						result++;	
				}
			}
			return result;
		}
		
		/** Finds and returns the value of total number of unpaid specific bills
		 * @param flatObjects Array that holds all the flat objects
		 * @param billType A string which holds the value of type of the bill
		 * @return
		 */
		public static int totalNumberOfUnpaidSpecificBills(Flat[][] flatObjects, String billType) {	
			int result = 0;
			ArrayList<Bill> billList = getAllBills(flatObjects);
			for (Bill bill:billList) {
				if(bill.getType().toLowerCase() == billType.toLowerCase()) {
					if(bill.getPaymentInfo().toLowerCase().equals("false")) {
						result++;	
					}
				}
			}
			return result;
		}
		
		/** Finds and returns the value of total bill amount of a specific floor
		 * @param flatObjects Array that holds all the flat objects
		 * @param floorNumber An integer which holds the value of floor number value
		 * @return an integer which holds the value of total bill amount of a specific floor
		 */
		public static int totalBillAmountOfASpecificFloor(Flat[][] flatObjects, int floorNumber) {
			int amount = 0;
			for (int i = 0; i < flatObjects[floorNumber-1].length; i++) {
				for (Bill bill:flatObjects[floorNumber-1][i].getListOfBills())
				amount = amount + bill.getAmount();
			}
			return amount;
		}
		
		/** Finds and returns the list of all unpaid bills with remaining time information
		 * @param flatObjects Array that holds all the flat objects
		 * @return unpaidBillsWithTime An Arraylist which holds the values of unpaid bills with remaining time information
		 */
		@SuppressWarnings("deprecation")
		public static ArrayList<String> listOfTotalUnpaidBillsWithRemainingTime(Flat[][] flatObjects) {
			Calendar currentDate = Calendar.getInstance();
			ArrayList<String> unpaidBillsWithTime = new ArrayList<String>();
			ArrayList<Bill> billList = getAllBills(flatObjects);
			for(Bill bill:billList) {
				if(bill.getPaymentInfo().toLowerCase().equals("false")) {
					if (bill.getPaymentDeadlineDate().getTime().getDate() >= currentDate.getTime().getDate()) {
					Calendar remainingTime = new GregorianCalendar(0,0,bill.getPaymentDeadlineDate().getTime().getDate() - currentDate.getTime().getDate());
					unpaidBillsWithTime.add("Bill type is: " + bill.getType() + ", Remaining time is: " + remainingTime.getTime().getDate() + " days.");
					}
				}
			}
			return unpaidBillsWithTime;
		}
		
		/** Finds and returns the value of total amount and number of paid bills before certain date
		 * @param flatObjects Array that holds all the flat objects
		 * @param untilDate A calendar which holds the value of the date which will be the end point of the date comparisons.
		 * @return A string which holds the value of number and amount of paid bills before certain date
		 */
		public static String totalAmountAndNumberOfPaidBillsBeforeCertainDate(Flat[][] flatObjects, Calendar untilDate) {
			int numberOfBills = 0;
			int amountOfBills = 0;
			ArrayList<Bill> billList = getAllBills(flatObjects);
			for(Bill bill:billList) {
				if(bill.getPaymentInfo().toLowerCase().equals("true")) {
					if(untilDate.after(bill.getLastUpdateDate())) {
						numberOfBills++;
						amountOfBills = amountOfBills + bill.getAmount();
						}
					}
				}
			return " The number of bills which of those didn't passed the given date is: " + numberOfBills + "\n The total amount that has been paid is: " + amountOfBills;
		}
		
		/** Finds and returns total amount and number of unpaid specific bills which of those passed the deadline
		 * @param flatObjects Array that holds all the flat objects
		 * @param type String that holds the value of bill type
		 * @return A string which holds the vcalue of number and amount of unpaid specific bills which of those passed the deadline.
		 */
		public static String totalAmountAndNumberOfUnpaidSpecificBillsThatPassedDeadline(Flat[][] flatObjects, String type) {			
			int numberOfBills = 0;
			int amountOfBills = 0;
			Calendar currentDate = Calendar.getInstance();
			ArrayList<Bill> billList = getAllBills(flatObjects);
			for(Bill bill:billList) {
				if(bill.getType().toLowerCase().equals(type)) {
				if(bill.getPaymentInfo().toLowerCase().equals("false")) {
					if(currentDate.after(bill.getPaymentDeadlineDate())) {
						numberOfBills++;
						amountOfBills = amountOfBills + bill.getAmount();
						}	
					}
				}
			}
			return " The number of bills which of those passed the deadline date is: " + numberOfBills + "\n The total amount that has been unpaid is: " + amountOfBills;
		}
		
		/** Finds and returns the average amount of unpaid bills with flats that have specific room numbers
		 * @param flatObjects Array that holds all the flat objects
		 * @param numberOfRooms An integer which corresponds to the square meter
		 * @return average A double which corresponds to the average amount of unpaid bills with flats that have specific room numbers
		 */
		public static double averageTotalNumberOfUnpaidBillsOfSpecificRoomedFlats(int numberOfRooms, Flat[][] flatObjects) {			
			int amountOfBills = 0;
			int numberOfBills = 0;
			double average = 0;
			int floorNumber = findFloorNumber(flatObjects);
			for (int i = 0; i < floorNumber; i++) {
				for (int j = 0; j < flatObjects[i].length; j++)
				if(flatObjects[i][j].getFloorNumber() == numberOfRooms) {
					for(Bill bill:flatObjects[i][j].getListOfBills()) {
						if(bill.getPaymentInfo().toLowerCase().equals("false")) {
							amountOfBills = amountOfBills + bill.getAmount();
							numberOfBills++;
						}
					}
				}
			}
			if(numberOfBills == 0){
				return 0;
			}
			average = amountOfBills/numberOfBills;
			return average;
		}
		
		/** Finds and returns the average amount of bills with flats that have square meters greater than sqMeter
		 * @param flatObjects Array that holds all the flat objects
		 * @param sqMeter An integer which corresponds to the square meter
		 * @return average A double which corresponds to the average amount of bills with flats that have square meters greater than sqMeter
		 */
		public static double averageAmountOfBillsWithFlatsWithSqGreaterThan(Flat[][] flatObjects ,int sqMeter) {			
			int amountOfBills = 0;
			int numberOfBills = 0;
			double average = 0;
			int floorNumber = findFloorNumber(flatObjects);
			for (int i = 0; i < floorNumber; i++) {
				for (int j = 0; j < flatObjects[i].length; j++)
				if(flatObjects[i][j].getSquareMeter() > sqMeter) {
					for(Bill bill:flatObjects[i][j].getListOfBills()) {
						if(bill.getPaymentInfo().toLowerCase().equals("false")) {
							amountOfBills = amountOfBills + bill.getAmount();
							numberOfBills++;
						}
					}
				}
			}
			if(numberOfBills == 0){
				return 0;
			}
			average = amountOfBills/numberOfBills;
			return average;
		}
		
}
