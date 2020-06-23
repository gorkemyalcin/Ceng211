package businessLayer;

import java.util.ArrayList;

/**
 * @author Gorkem
 * An abstract patient class for the patients in the hospital.
 * It has a dateOfAppointments attribute which works like a schedule for the patient.
 * I used Eclipses hashCode and equals methods in this class.
 */
public abstract class Patient {

	private PatientType typeOfPatient;
	private String name;
	private Date birthDate;
	private int patientId;
	private BloodType bloodType;
	private ArrayList<Analysis> analysis;
	private ArrayList<Date> datesOfAppointments;
	private Date surgeryDate;

	public Patient(PatientType typeOfPatient, String name, Date birthDate, int patientId, BloodType bloodType, ArrayList<Date> datesOfAppointments) {
		this.setTypeOfPatient(typeOfPatient);
		this.setName(name);
		this.setBirthDate(birthDate);
		this.setPatientId(patientId);
		this.setBloodType(bloodType);
		this.setDatesOfAppointments(datesOfAppointments);
		this.setSurgeryDate(null);
	}
	
	public String toString() {
		return getPatientId() + "," + getName() + ", " + getTypeOfPatient();
	}
	
	public void setAnalysis(ArrayList<Analysis> analysis) {
		this.analysis = analysis;
	}
	
	/**
	 * @param date
	 * @return specific bloodTest if found, null otherwise
	 */
	public BloodTest getSpecificBloodTest(Date date) {
		for (Analysis analysis:getAnalysis()) {
			if (analysis instanceof BloodTest){
				if(analysis.getDateOfAnalysisReport().equals(date)) {
					return (BloodTest)analysis;
				}
			}
		}
		return null;
	}

	/**
	 * @param date
	 * @return specific radiology if found, null otherwise
	 */
	public Radiology getSpecificRadiology(Date date) {
		for (Analysis analysis:getAnalysis()) {
			if (analysis instanceof Radiology){
				if(analysis.getDateOfAnalysisReport().equals(date)) {
					return (Radiology)analysis;
				}
			}
		}
		return null;
	}
	

	public ArrayList<Analysis> getAnalysis() {
		return analysis;
	}

	public void addAnalysis(Analysis analysis) {
		this.analysis.add(analysis);
	}

	public PatientType getTypeOfPatient() {
		return typeOfPatient;
	}

	public void setTypeOfPatient(PatientType typeOfPatient) {
		this.typeOfPatient = typeOfPatient;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public BloodType getBloodType() {
		return bloodType;
	}

	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	public ArrayList<Date> getDatesOfAppointments() {
		return datesOfAppointments;
	}

	public void setDatesOfAppointments(ArrayList<Date> datesOfAppointments) {
		this.datesOfAppointments = datesOfAppointments;
	}

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((analysis == null) ? 0 : analysis.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((bloodType == null) ? 0 : bloodType.hashCode());
		result = prime * result + ((datesOfAppointments == null) ? 0 : datesOfAppointments.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + patientId;
		result = prime * result + ((surgeryDate == null) ? 0 : surgeryDate.hashCode());
		result = prime * result + ((typeOfPatient == null) ? 0 : typeOfPatient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		if (analysis == null) {
			if (other.analysis != null)
				return false;
		} else if (!analysis.equals(other.analysis))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (bloodType != other.bloodType)
			return false;
		if (datesOfAppointments == null) {
			if (other.datesOfAppointments != null)
				return false;
		} else if (!datesOfAppointments.equals(other.datesOfAppointments))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (patientId != other.patientId)
			return false;
		if (surgeryDate == null) {
			if (other.surgeryDate != null)
				return false;
		} else if (!surgeryDate.equals(other.surgeryDate))
			return false;
		if (typeOfPatient != other.typeOfPatient)
			return false;
		return true;
	}

}
