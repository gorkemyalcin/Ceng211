package dataAccessLayer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.GregorianCalendar;

import businessLayer.Bill;
import businessLayer.Flat;

/**
 * @author Gorkem-Onur
 *
 */
public class FileIOApartment {
	
	/**
	 * @param fileName1 which is apartment information file
	 * @param fileName2 which is bill information file
	 * @return ArrayList which includes flat information
	 * @throws IOException
	 */
	public static ArrayList<Flat> apartmentInfoReader(String fileName1, String fileName2) throws IOException {
			ArrayList<Flat> flatList = new ArrayList<>();
			ArrayList<Bill> billList = bilingInfoReader(fileName2);
			int flatId = 0;
			int floorNumber = 0;
			int flatNumber = 0;
			int numberOfRooms = 0;
			int squareMeter = 0;
			FileReader f = new FileReader(fileName1);
			BufferedReader inputStream = new BufferedReader(f);
			StringTokenizer wordFinder;
			String line = inputStream.readLine();
			while (line != null) {
				wordFinder = new StringTokenizer(line, ",");
				while (wordFinder.hasMoreTokens()) {
					flatId = Integer.parseInt(wordFinder.nextToken());
					floorNumber = Integer.parseInt(wordFinder.nextToken());
					flatNumber = Integer.parseInt(wordFinder.nextToken());
					numberOfRooms = Integer.parseInt(wordFinder.nextToken());
					squareMeter = Integer.parseInt(wordFinder.nextToken());
					ArrayList<Bill> billsList = new ArrayList<>();
					for(Bill bill:billList) {
						if (bill.getFlatId() == flatId) {
							billsList.add(bill);
						}
					}
					flatList.add(new Flat(flatId, floorNumber, flatNumber, numberOfRooms, squareMeter, billsList));
				}	
				line = inputStream.readLine();
			}
			inputStream.close();
			return flatList;
		}
	
	/**
	 * @param fileName1 which is bill information file
	 * @return ArrayList which includes bill information
	 * @throws IOException
	 */
	public static ArrayList<Bill> bilingInfoReader(String fileName) throws IOException{
			ArrayList<Bill> billList = new ArrayList<>();
			int billId = 0;
			int flatId = 0;
			int Amount = 0;
			String Type = null;
			String paymentInfo = null;
			int year = 0;
			int month = 0;
			int day = 0;
			Calendar paymentDeadlineDate = new GregorianCalendar(0,0,0);
			Calendar lastUpdateDate = Calendar.getInstance();
			FileReader f = new FileReader(fileName);
			BufferedReader inputStream = new BufferedReader(f);
			StringTokenizer wordFinder;
			String line = inputStream.readLine();
			while (line != null) {
				wordFinder = new StringTokenizer(line, ",");
				while (wordFinder.hasMoreTokens()) {
					billId = Integer.parseInt(wordFinder.nextToken());
					flatId = Integer.parseInt(wordFinder.nextToken());
					Amount = Integer.parseInt(wordFinder.nextToken());
					Type = wordFinder.nextToken();
					paymentInfo = wordFinder.nextToken();
					StringTokenizer dateFinder = new StringTokenizer(wordFinder.nextToken(), "-");
					while (dateFinder.hasMoreTokens()) {
						year = Integer.parseInt(dateFinder.nextToken());
						month = Integer.parseInt(dateFinder.nextToken());
						day = Integer.parseInt(dateFinder.nextToken());
					}
					paymentDeadlineDate = new GregorianCalendar(year, month-1, day);
					String lastUpdateDateBulk=(wordFinder.nextToken());
					StringTokenizer lastUpdateDateTokenizer = new StringTokenizer(lastUpdateDateBulk, "-");
					while (lastUpdateDateTokenizer.hasMoreTokens()) {
						year = Integer.parseInt(lastUpdateDateTokenizer.nextToken());
						month = Integer.parseInt(lastUpdateDateTokenizer.nextToken());
						day = Integer.parseInt(lastUpdateDateTokenizer.nextToken());
					}
					lastUpdateDate = new GregorianCalendar(year, month-1, day);
					billList.add(new Bill(billId, flatId, Amount, Type, paymentInfo, paymentDeadlineDate, lastUpdateDate));
				}	
				line = inputStream.readLine();
			}
			inputStream.close();


			return billList;
	}
	
}
