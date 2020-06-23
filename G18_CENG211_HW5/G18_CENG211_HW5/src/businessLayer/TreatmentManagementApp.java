package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptionHandlers.AnalysisNotFoundException;
import exceptionHandlers.IllegalBirthdateException;
import exceptionHandlers.PatientNotFoundException;
import presentationLayer.ConsoleIO;
/**
 * @author Gorkem
 * In this homework, I used different types of inheritance in Doctor-Surgeon, Patient-Types of Patients, Analysis-Radiology||BloodTest and Treatment-Therapy||Surgery
 * Generics, in one method in testScheduler which was responsible for returning an analysis of type <T extends Analysis> T. 
 * Which was responsible for returning a generic type of an analysis.
 * Exceptions, in the ConsoleIO or the Simulation class. Also I created them in the exceptionHandlers package.
 * 
 * I created some starting people to be used in the simulation to both test and to make it easier on other people who are testing this program.
 * All the simulation is done in the ConsoleIO class which was created here and started.
 */
public class TreatmentManagementApp {
	
	public static void main(String[] args) throws IllegalBirthdateException, InterruptedException, AnalysisNotFoundException, PatientNotFoundException {
		List<Doctor> doctorList = new ArrayList<Doctor>();
		List<Patient> patientList = new ArrayList<Patient>();
		TestTechnician testTechnician  = new TestTechnician();
		SurgeryScheduler surgeryScheduler = new SurgeryScheduler();
		Hospital hospital = new Hospital(patientList, doctorList, testTechnician, surgeryScheduler);
		initializeStartingPeople(doctorList, patientList, hospital);
		ConsoleIO simulation = new ConsoleIO();
		simulation.start(hospital);
	}
	
	public static void initializeStartingPeople(List<Doctor> doctorList,List<Patient> patientList, Hospital hospital) {
		patientList.add(new WalkingCasePatient("Emily Kaldwin", new Date(1996, 12, 15), 2001, BloodType.AB_POSITIVE, new ArrayList<Date>(Arrays.asList(new Date(2019, 1, 2)))));
		patientList.add(new EmergencyPatient("Alex Garfield", new Date(1982, 11, 12), 2002, BloodType.A_POSITIVE, new ArrayList<Date>(Arrays.asList(new Date(2019, 1, 5)))));
		patientList.add(new InmatePatient("Artour Babaev", new Date(1997, 5, 23), 2003, BloodType.O_POSITIVE, new ArrayList<Date>(Arrays.asList(new Date(2019, 1, 5)))));
		patientList.add(new WalkingCasePatient("Billie Lurk", new Date(1985, 2, 5), 2004, BloodType.A_NEGATIVE, new ArrayList<Date>(Arrays.asList(new Date(2018, 12, 31)))));
		patientList.add(new InmatePatient("Aurelia Hammerlock", new Date(1976, 6, 2), 2005, BloodType.O_NEGATIVE, new ArrayList<Date>(Arrays.asList(new Date(2019, 1, 2)))));
		doctorList.add(new Doctor("Warner Bautch", DoctorDiscipline.ALLERGIST, 1001, new ArrayList<Examination>(Arrays.asList(new Examination(null, patientList.get(0), new Date(2019, 1, 2)))), hospital));
		doctorList.add(new Doctor("Valene Mouser", DoctorDiscipline.DERMATOLOGIST, 1002, new ArrayList<Examination>(Arrays.asList(new Examination(null, patientList.get(1), new Date(2019, 1, 5)))) , hospital));
		doctorList.add(new Doctor("Camie Dejohn", DoctorDiscipline.NEUROLOGIST, 1003, new ArrayList<Examination>(Arrays.asList(new Examination(null, patientList.get(2), new Date(2019, 1, 5)))), hospital));
		doctorList.add(new Surgeon("Guy Beahm", DoctorDiscipline.ANESTHESIOLOGIST, 1004, new ArrayList<Examination>(Arrays.asList(new Examination(null, patientList.get(3), new Date(2018, 12, 31)))), hospital));
		doctorList.add(new Surgeon("John Price", DoctorDiscipline.PSYCHIATRIST, 1005, new ArrayList<Examination>(Arrays.asList(new Examination(null, patientList.get(4), new Date(2019, 1, 2)))), hospital));
		for (Doctor doctor:doctorList) {
			doctor.getExaminationList().get(0).setDoctor(doctor);
		}
	}

}
