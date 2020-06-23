package presentationLayer;

import java.io.IOException;
import businessLayer.ApartmentBillingManager;

public class ApartmentBillingApp {

	/**
	 * @author Gorkem & Onur
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String billFileName = ApartmentBillingManager.findBillFile();
		ApartmentBillingManager bilingManager = new ApartmentBillingManager("HW1-ApartmentInfo.csv", billFileName);
		bilingManager.start();
	}
}

