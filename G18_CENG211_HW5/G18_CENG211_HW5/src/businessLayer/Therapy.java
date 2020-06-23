package businessLayer;

import java.util.ArrayList;

/**
 * @author Gorkem
 */
public class Therapy extends Treatment{
	
	private TherapyType typeOfTherapy;

	public Therapy(ArrayList<Medicine> medicineList, TherapyType typeOfTherapy) {
		super(medicineList);
		this.setTypeOfTherapy(typeOfTherapy);
	}

	public TherapyType getTypeOfTherapy() {
		return typeOfTherapy;
	}

	public void setTypeOfTherapy(TherapyType typeOfTherapy) {
		this.typeOfTherapy = typeOfTherapy;
	}
}
