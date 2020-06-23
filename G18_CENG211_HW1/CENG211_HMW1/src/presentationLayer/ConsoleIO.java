package presentationLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import businessLayer.Apartment;
import businessLayer.BillQuery;
import businessLayer.Flat;

/**
 * @author Gorkem-Onur
 *
 */
public  class ConsoleIO {
	
	private static void printMenu(){
		System.out.println("\n \n \n.............Welcome to Apartment Biling System.............");
		System.out.println("\n");
		System.out.println("1. To change payment info");
		System.out.println("2. To list all the flats");
		System.out.println("3. To list all the bills of a flat");
		System.out.println("4. To list all the unpaid bills");
		System.out.println("5. To list some specific type of unpaid bills");
		System.out.println("6. To list the bill amount of a specific floor");
		System.out.println("7. To list all the bills with their remaining times");
		System.out.println("8. To list the amount and quantity of paid bills before a given date");
		System.out.println("9. To list the amount and quantity of a specific typed unpaid bills that passed the deadline");
		System.out.println("10. To list the average amount of bills of a certain floor");
		System.out.println("11. To list the average amount of bills those their square meter is greater than a specific value");
		System.out.println("12. To list all the bills");
		System.out.println("13. To see the total amount of unpaid bills");
		System.out.println("X to exit");
		System.out.println("............................................................");
		System.out.println("Please enter the query number that you want to work on.");
	}//end printMenu
	
	@SuppressWarnings("resource")
	public static void simulator(String filename1, String filename2) throws IOException {
		Apartment apartment = new Apartment(filename1, filename2);
		Flat[][] flatObjects = apartment.getFlatObjects();
		while(true) {
			printMenu();
			Scanner keyboard = new Scanner(System.in);
			String choice = keyboard.nextLine();
			
			if(choice.equals("1")) {
				System.out.println("Enter the bill id of whose info you'd like to change");
				keyboard = new Scanner(System.in);
				if(keyboard.hasNextInt()) {
					int billId = keyboard.nextInt();
					if (0 < billId && billId < 13) {
						System.out.println("Enter the payment information(true or false)");
						String paymentInfo = new Scanner(System.in).nextLine();
						BillQuery.changePaymentInfo(flatObjects, billId, paymentInfo);
					}
					else {
						System.out.println("You have entered a bill id that doesn't exist in the database. Please try again");
					}
				}
				else {
					System.out.println("You have entered a non-integer value. Please try again");
				}
				
			}

			else if(choice.equals("2")) {
				System.out.println("Printing flats in the format of 'Flat id, Floor number , Flat number, Number of rooms, Square meter, Bill id, Flat id, Bill amount, Bill type, Payment info, Deadline date and Last update date'");
				BillQuery.listAllFlats(flatObjects);
			}
			
			else if(choice.equals("3")) {
				System.out.println("Enter the flat id of whose bills you'd like to see");
				keyboard = new Scanner(System.in);
				if(keyboard.hasNextInt()) {
					int flatId = keyboard.nextInt();
					if (0 < flatId && flatId < 7) {
						BillQuery.listAllBillsOfAFlat(flatObjects, flatId);
					}
					else {
						System.out.println("You have entered a flat id that doesn't exist in the database. Please try again");
					}
				}
				else {
					System.out.println("You have entered a non-integer value. Please try again");
				}
			}
			
			else if(choice.equals("4")) {
				BillQuery.listOfAllUnpaidBills(flatObjects);
			}
			
			else if(choice.equals("5")) {
				System.out.println("Enter the bill type to see that types unpaid bills.");
				String billType = new Scanner(System.in).nextLine();
				if (billType.equals("water") || billType.equals("electric") || billType.equals("heating") || billType.equals("cleaning")) {
					BillQuery.listOfAllUnpaidBillsOfASpecificType(flatObjects, billType);
				}
				else {
					System.out.println("Corresponding bill type doesn't exist in the database.  Please try again");
				}
			}
			
			else if(choice.equals("6")) {
				System.out.println("Enter the floor number of whose total bill amount you'd like to see.");
				keyboard = new Scanner(System.in);
				if(keyboard.hasNextInt()) {
					int floorNumber = keyboard.nextInt();
					if(floorNumber > 0 && floorNumber < 5) {
						System.out.println(BillQuery.totalBillAmountOfASpecificFloor(flatObjects, floorNumber));
					}
				else {
					System.out.println("You have entered a floor number that doesn't exist in the database. Please try again");
					}
				}
				else {
					System.out.println("You have entered a non-integer value. Please try again");
				}
			}
			
			else if(choice.equals("7")) {
				ArrayList<String> unpaidBillTimeList =BillQuery.listOfTotalUnpaidBillsWithRemainingTime(flatObjects);
				for (String billInfo:unpaidBillTimeList) {
					System.out.println(billInfo);
				}
			}
			
			else if(choice.equals("8")) {
				keyboard = new Scanner(System.in);
				System.out.println(" Please enter the requested date's year value: ");
				if(keyboard.hasNextInt()) {
					int year = keyboard.nextInt();
					if (year > 2000 && year < 2030) {
						System.out.println(" Please enter the date's month value: ");
						if(keyboard.hasNextInt()) {
						int month = keyboard.nextInt();
							if (month > 0 && month < 13) {
								month--;
								System.out.println(" Please enter the date's day value: ");
								if(keyboard.hasNextInt()) {
									int day = keyboard.nextInt();
									if (day > 0 && day < 32) {
										Calendar untilDate = new GregorianCalendar(year, month, day);
										System.out.println(BillQuery.totalAmountAndNumberOfPaidBillsBeforeCertainDate(flatObjects, untilDate));
									}
									else {
										System.out.println("You have entered a day value that doesn't exist in the database. Please try again");
									}
								}
								else {
									System.out.println("You have entered a non-integer value. Please try again");
								}
							}
							else {
								System.out.println("You have entered a month value that doesn't exist in the database. Please try again");
							}
						}
						else {
							System.out.println("You have entered a non-integer value. Please try again");
						}
					}
					else {
						System.out.println("You have entered a year value that doesn't exist in the database. Please try again");
					}
				}
				else {
					System.out.println("You have entered a non-integer value. Please try again");
				}
			}
			
			else if(choice.equals("9")) {
				System.out.println("Enter the bill type of the bill to see the number and amount of unpaid bills whose deadline have passed.");
				String billType = new Scanner(System.in).nextLine();
				if (billType.equals("water") || billType.equals("electric") || billType.equals("heating") || billType.equals("cleaning")) {
					System.out.println(BillQuery.totalAmountAndNumberOfUnpaidSpecificBillsThatPassedDeadline(flatObjects, billType));
				}
				else {
					System.out.println("Corresponding bill type doesn't exist in the database.  Please try again");
				}
			}
			
			else if(choice.equals("10")) {
				System.out.println("Enter the room number of the flats whose bill amount you'd like to see");
				keyboard = new Scanner(System.in);
				if(keyboard.hasNextInt()) {
					int numberOfRooms = keyboard.nextInt();
					if (numberOfRooms > 0 && numberOfRooms < 4) {
						System.out.println(BillQuery.averageTotalNumberOfUnpaidBillsOfSpecificRoomedFlats(numberOfRooms, flatObjects));
					}
				
				else {
					System.out.println("You have entered a room number value that doesn't exist in the database. Please try again");
					}
				}
				else {
					System.out.println("You have entered a non-integer value. Please try again");
				}
			}
			
			else if(choice.equals("11")) {
				System.out.println("Enter the size in square meter of whose average bill amount you'd like to see");
				keyboard = new Scanner(System.in);
				if(keyboard.hasNextInt()) {
				int squareMeter = keyboard.nextInt();
					if (squareMeter > 0 && squareMeter < 200) {
						System.out.println(BillQuery.averageAmountOfBillsWithFlatsWithSqGreaterThan(flatObjects, squareMeter));
					}
					else {
						System.out.println("You have entered a square meter value that doesn't correspond to a meaningful value in the database. Please try again");
					}
				}
				else {
					System.out.println("You have entered a non-integer value. Please try again");
				}
			}
			
			else if(choice.equals("12")) {
				System.out.println("Printing bills in the format of 'Bill id, Flat id, Bill amount, Bill type, Payment info, Deadline date and Last update date'");
				BillQuery.printAllBills(flatObjects);
			}
			
			else if (choice.equals("13")) {
				System.out.println(BillQuery.totalAmountOfUnpaidBills(flatObjects));
			}
			
			else if((choice).toLowerCase().equals("x")) {
				System.out.println("Goodbye!");
				break;
			}
			
			else {
				System.out.println("You have entered a value that doesn't have a corresponding query. Please try values from the list.\n");
			}	
		}
	}//end simulator
}//end ConsoleIO
