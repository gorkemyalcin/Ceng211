package businessLayer;

import java.util.ArrayList;

/**
 * @author Gorkem
 *
 */
public class Treatment {

	private ArrayList<Medicine> medicineList;
	
	public Treatment(ArrayList<Medicine> medicineList) {
		this.setMedicineList(medicineList);
	}
	public ArrayList<Medicine> getMedicineList() {
		return medicineList;
	}
	public void setMedicineList(ArrayList<Medicine> medicineList) {
		this.medicineList = medicineList;
	}
}
