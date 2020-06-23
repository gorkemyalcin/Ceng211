package businessLayer;

/**
 * @author Gorkem
 *	Class that holds the values for an examinations doctor, patient, and the date of the examination.
 *	I used Eclipse's options for creating the hashCode and equals methods.
 */
public class Examination {

	private Doctor doctor;
	private Patient patient;
	private Date dateOfExamination;

	public Examination(Doctor doctor, Patient patient, Date dateOfExamination) {
		this.setDoctor(doctor);
		this.setPatient(patient);
		this.setDateOfExamination(dateOfExamination);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfExamination == null) ? 0 : dateOfExamination.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
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
		Examination other = (Examination) obj;
		if (dateOfExamination == null) {
			if (other.dateOfExamination != null)
				return false;
		} else if (!dateOfExamination.equals(other.dateOfExamination))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getDateOfExamination() {
		return dateOfExamination;
	}

	public void setDateOfExamination(Date dateOfExamination) {
		this.dateOfExamination = dateOfExamination;
	}

}
