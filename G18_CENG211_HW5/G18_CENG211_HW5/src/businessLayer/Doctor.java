package businessLayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Gorkem
 *	A class which is has the following attributes, most of them are self explanatory. Every doctor has its own discipline, 
 * I created an enum class with around 20 disciplines to be used. Every doctor has their individual id numbers. Every id is in format 100x. 
 * For example in the app class I have 5 doctors with id's 1001, 1002, 1003, 1004, 1005. Which is used for clearer system operations,
 *  since patients ids start with 200x, there is no confliction between doctors and patients.
 *  If a doctor is also a surgeon then its own boolean value of surgeon becomes true. 
 *  Examination list is used for a kind of agenda or schedule. It holds the values for the doctors all examinations.
 *  Checked patients are for, after doctor is done with a patient, patient gets added to checkedPatients set. We had no link between doctor and patient but
 *  this wasn't for every patient. It's just that when a doctor is done with a patient, we just add the patient here for using the set in the upcoming doctor operations.
 *  This was easier and simpler so I did it this way.
 */
public class Doctor {

	private String name;
	private DoctorDiscipline discipline;
	private int doctorId;
	private Hospital hospital;
	protected boolean surgeon;
	private List<Examination> examinationList;
	private Set<Patient> checkedPatients;

	
	public Doctor(String name, DoctorDiscipline discipline, int doctorId,
			ArrayList<Examination> examinationList, Hospital hospital) {
		this.setName(name);
		this.setDiscipline(discipline);
		this.setDoctorId(doctorId);
		this.setHospital(hospital);
		this.setSurgeon(false);
		List<Date> schedule = new ArrayList<Date>();
		schedule.add(new Date(06,05,2018));//hiring date.
		this.setCheckedPatients(new HashSet<Patient>());
		this.setExaminationList(examinationList);
	}
	
	
	/**
	 * @param patient to be added, since it is a set I don't check if the set contains this very patient.
	 * 
	 */
	public void addToCheckedPatients(Patient patient) {
		if (patient != null) {
			getCheckedPatients().add(patient);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DoctorDiscipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(DoctorDiscipline discipline) {
		this.discipline = discipline;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public boolean isSurgeon() {
		return surgeon;
	}

	public void setSurgeon(boolean surgeon) {
		this.surgeon = surgeon;
	}

	public Set<Patient> getCheckedPatients() {
		return checkedPatients;
	}

	public void setCheckedPatients(Set<Patient> checkedPatients) {
		this.checkedPatients = checkedPatients;
	}


	/**
	 * @param date
	 * @return List of patients which have an appointment with the current doctor on the very date that is in the parameter.
	 * 
	 */
	public List<Patient> findSpecificDayPatients(Date date) {
		List<Patient> specificDayPatientList = new ArrayList<Patient>();
		if (getExaminationList().size() == 0) {
			return specificDayPatientList;
		}
		for (Examination examination:getExaminationList()) {
			if (examination.getDateOfExamination().compareTo(date) == 0){
				specificDayPatientList.add(examination.getPatient());
			}
		}
		return specificDayPatientList;
	}

	public List<Examination> getExaminationList() {
		return examinationList;
	}

	public void setExaminationList(List<Examination> examinationList) {
		this.examinationList = examinationList;
	}


	/**
	 * @param appointmentDate
	 * @return An examination that is on the same time with the given parameter.
	 */
	public Examination findExamination(Date appointmentDate) {
		for (Examination examination:getExaminationList()) {
			if (examination.getDateOfExamination().equals(appointmentDate)) {
				return examination;
			}
		}
		return null;
	}
	
}
