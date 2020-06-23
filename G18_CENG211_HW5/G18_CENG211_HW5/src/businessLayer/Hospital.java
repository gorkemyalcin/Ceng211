package businessLayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Gorkem
 *
 *	A hospital class which holds the values of all patients in it, all doctors in it, 
 * 2 helpers who are test technician and surgery scheduler who both are responsible for their own areas of expertise.
 */
public class Hospital {

	private List<Patient> patientList;
	private List<Doctor> doctorList;
	private TestTechnician testTechnican;
	private SurgeryScheduler surgeryScheduler;
	private Set<Patient> pastPatients;
	
	public Hospital(List<Patient> patientList, List<Doctor> doctorList, TestTechnician testTechnician, SurgeryScheduler surgeryScheduler) {
		this.patientList = patientList;
		this.setPastPatients(new HashSet<Patient>());
		this.doctorList = doctorList;
		this.setTestTechnican(testTechnician);
		this.setSurgeryScheduler(surgeryScheduler);
	}

	public Hospital() {
		this.patientList = new ArrayList<Patient>();
		this.doctorList = new ArrayList<Doctor>();
	}

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	public List<Doctor> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}


	/**
	 * @return finds and returns the maximum id number of the patients.
	 */
	public int findMaxPatientId() {
		int maxId = 0;
		for (Patient patient:getPatientList()) {
			if (patient.getPatientId() > maxId) {
				maxId = patient.getPatientId();
			}
		}
		return maxId;
	}

	/**
	 * @param newPatient to be added
	 * Adds a patient to the patientList
	 */
	public void addNewPatient(Patient newPatient) {
		if (newPatient != null) {
		getPatientList().add(newPatient);
		}
	}

	/**
	 * @param doctorId specific doctor id
	 * @return finds and returns the doctor who has the same id as the parameter. Null if not found
	 */
	public Doctor findDoctorWithId(int doctorId) {
		for(Doctor doctor:getDoctorList()) {
			if (doctorId == doctor.getDoctorId()) {
				return doctor;
			}
		}
		return null;
	}

	/**
	 * @param patientId specific patient id
	 * @return finds and returns the patient who has the same id as the parameter. Null if not found
	 */
	public Patient findPatientWithId(int patientId) {
		for(Patient patient:getPatientList()) {
			if (patientId == patient.getPatientId()) {
				return patient;
			}
		}
		for(Patient patient:getPastPatients()) {
			if (patientId == patient.getPatientId()) {
				return patient;
			}
		}
		return null;
	}

	public TestTechnician getTestTechnican() {
		return testTechnican;
	}

	public void setTestTechnican(TestTechnician testTechnican) {
		this.testTechnican = testTechnican;
	}
	
	/**
	 * @param patientName specific patient name
	 * @return finds and returns the patient who has the same name as the parameter. Null if not found
	 */
	public Patient findPatientWithName(String patientName) {
		for(Patient patient:getPatientList()) {
			if (patientName.toLowerCase().equals(patient.getName().toLowerCase())) {
				return patient;
			}
		}
		for(Patient patient:getPastPatients()) {
			if (patientName.toLowerCase().equals(patient.getName().toLowerCase())) {
				return patient;
			}
		}
		return null;
	}

	public SurgeryScheduler getSurgeryScheduler() {
		return surgeryScheduler;
	}

	public void setSurgeryScheduler(SurgeryScheduler surgeryScheduler) {
		this.surgeryScheduler = surgeryScheduler;
	}

	/**
	 * @return surgeon who is a random surgeon from hospital.
	 */
	public Surgeon getRandomSurgeon() {
		Random rand = new Random();
		while (true) {
			Doctor surgeonCandidate = getDoctorList().get(rand.nextInt(getDoctorList().size()));
			if (surgeonCandidate instanceof Surgeon) {
				return (Surgeon)surgeonCandidate;
			}
		}
	}

	public Set<Patient> getPastPatients() {
		return pastPatients;
	}

	public void setPastPatients(Set<Patient> pastPatients) {
		this.pastPatients = pastPatients;
	}
}
