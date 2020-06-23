package businessLayer;

import java.io.File;
import java.io.IOException;

import presentationLayer.ConsoleIO;

/**
 * @author Gorkem-Onur
 *
 */
public class ApartmentBillingManager {
	String filename1;
	String filename2;
	
	public ApartmentBillingManager(String filename1, String filename2) {
		this.filename1 = filename1;
		this.filename2 = filename2;
	}// end constructor.
	
	/** Finds and returns the most recent bill information .csv file to the App class.
	 * @return String which is the most recent bill information file.
	 * @throws IOException
	 */
	public static String findBillFile() throws IOException{
		String billFile = null;
		File directory = new File(System.getProperty("user.dir"));
		File[] allFilesInDir = directory.listFiles();
		int count = 0;
		for(File file:allFilesInDir) {
			if (file.toString().endsWith(".csv")) {
				count++;
				if (count == 2) {
					billFile = file.toString();
				}
			}
		}
		return billFile;
	}//end findBillFile
	
	public void start() throws IOException {
		findBillFile();
		ConsoleIO.simulator(filename1, filename2);
	}
}
