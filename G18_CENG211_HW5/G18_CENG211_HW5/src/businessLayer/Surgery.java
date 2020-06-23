package businessLayer;

import java.util.ArrayList;

/**
 * @author Gorkem
 * A surgery class which is derived from treatment class and controls the surgery operation. Also sets the patients type to inmate.
 */
public class Surgery extends Treatment {

	private Surgeon surgeon;
	private Date dateOfSurgery;
	private Patient patient;
	private SurgeryType surgeryType;
	private int numberOfDaysInHospitalCareAfterSurgery;
	private boolean done;
	
	public Surgery(ArrayList<Medicine> medicineList, Surgeon surgeon, Date dateOfSurgery,
			Patient patient, SurgeryType surgeryType, int numberOfDaysInHospitalCareAfterSurgery) {
		super(medicineList);
		this.setSurgeon(surgeon);
		this.setDateOfSurgery(dateOfSurgery);
		this.setPatient(patient);
		this.patient.setTypeOfPatient(PatientType.INMATE);
		this.setSurgeryType(surgeryType);
		this.setNumberOfDaysInHospitalCareAfterSurgery(numberOfDaysInHospitalCareAfterSurgery);
		this.setDone(false);
	}
	
	public String toString() {
		return "Surgeon: " + getSurgeon() + " Patient: " + getPatient() + " Surgery date: " + getDateOfSurgery() + " Type of surgery: " + getSurgeryType();
	}

	public int getNumberOfDaysInHospitalCareAfterSurgery() {
		return numberOfDaysInHospitalCareAfterSurgery;
	}

	public void setNumberOfDaysInHospitalCareAfterSurgery(int numberOfDaysInHospitalCareAfterSurgery) {
		this.numberOfDaysInHospitalCareAfterSurgery = numberOfDaysInHospitalCareAfterSurgery;
	}

	public SurgeryType getSurgeryType() {
		return surgeryType;
	}

	public void setSurgeryType(SurgeryType surgeryType) {
		this.surgeryType = surgeryType;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDateOfSurgery() {
		return dateOfSurgery;
	}

	public void setDateOfSurgery(Date dateOfSurgery) {
		this.dateOfSurgery = dateOfSurgery;
	}

	public Surgeon getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(Surgeon surgeon) {
		this.surgeon = surgeon;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
