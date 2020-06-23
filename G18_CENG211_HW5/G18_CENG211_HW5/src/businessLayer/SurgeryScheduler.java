package businessLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gorkem
 * A class that is responsible for all the surgery operations.
 */
public class SurgeryScheduler {
	
	private ArrayList<Surgery> surgeries;
	
	public SurgeryScheduler() {
		this.setSurgeries(new ArrayList<Surgery>());
	}
	
	/**
	 * @param patient
	 * @return finds all the surgeries for a patient and returns the collected surgeries' list
	 */
	public List<Surgery> findSurgeriesForAPatient(Patient patient){
		List<Surgery> surgeriesOfAPatient = new ArrayList<Surgery>(); 
		for (Surgery surgery:getSurgeries()) {
			if (surgery.getPatient().equals(patient)) {
				surgeriesOfAPatient.add(surgery);
			}
		}
		return surgeriesOfAPatient;
	}
	
	/**
	 * @param surgery
	 * adds a surgery to its own list
	 */
	public void addSurgery(Surgery surgery) {
		getSurgeries().add(surgery);
	}

	public ArrayList<Surgery> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(ArrayList<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

	/**
	 * @param patient
	 * @param date
	 * @return true if its a correct date for surgery, false otherwise.
	 * Checks if a patient has surgery on a specific day.
	 */
	public boolean isTodayTheSurgeryDayForPatient(Patient patient, Date date, Doctor doctor) {
		for (Surgery surgery:getSurgeries()) {
			if (surgery.getPatient().equals(patient) && surgery.getDateOfSurgery().equals(date) && surgery.getSurgeon().equals(doctor)) {
				return true;
			}
		}
		return false;
	}

}
