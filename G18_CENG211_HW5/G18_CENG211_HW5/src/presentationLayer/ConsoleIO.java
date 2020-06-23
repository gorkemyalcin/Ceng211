package presentationLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import businessLayer.Analysis;
import businessLayer.BloodTest;
import businessLayer.BloodType;
import businessLayer.Date;
import businessLayer.Doctor;
import businessLayer.EmergencyPatient;
import businessLayer.Examination;
import businessLayer.Hospital;
import businessLayer.InmatePatient;
import businessLayer.Medicine;
import businessLayer.Patient;
import businessLayer.Radiology;
import businessLayer.Surgeon;
import businessLayer.Surgery;
import businessLayer.SurgeryType;
import businessLayer.WalkingCasePatient;
import exceptionHandlers.AnalysisNotFoundException;
import exceptionHandlers.IllegalBirthdateException;
import exceptionHandlers.PatientNotFoundException;

/**
 * @author Gorkem
 * This is the main simulation class for the whole process.
 * This class has many helper methods to be used in its simulation method to simplify and make reusable codes in the program.
 * 
 * I included a tree in the .zip file which has how the simulation can branch out.
 * If you are a doctor you have to enter your id number to access your operations and doctor ids are between 1001 and 1005.
 * All the appointment dates are in the app class, however to test the program, 
 * you can use 1001 for doctor id and 02/01/2019 for the date to show a patient with id of 2001.
 * All the information of the created patients, dates and doctors are in the app class.
 * To examine a patient, enter the corresponding id when you list the patients for a specific day
 */
public class ConsoleIO {

	public ConsoleIO() {
	}

	public void printMenu() {
		System.out.println("************** Welcome to IZTECH Hospital **************\n");
		System.out.println("Are you a doctor or a receptionist?\n 1 for doctor, 2 for receptionist, X to quit");
	}
	
	private void printDoctorMenu(Doctor doctor) {
		System.out.println("\nWelcome back " + doctor.getName() + "!\n");
		System.out.println("1. See all the patients that you will see in specific day.");
		System.out.println("2. See all the patients who are under your care.");
		System.out.println("3. Search for an analysis, if found see the result.");
		System.out.println("4. See all the patients that you examined.");
		System.out.println("5. Search for an patient that you examined in the past, if exists show the patient.");
		System.out.println("6. Search for an appointed surgery for a patient, if there are any.");
		System.out.println("X to quit.");
	}

	private boolean checkDayValue(int day) {
		return day < 32 && day > 0;
	}

	private boolean checkMonthValue(int month) {
		return month < 13 && month > 0;
	}

	private boolean checkYearValue(int year) {
		return year < 2025 && year > 1900;
	}
	
	private void printGivenMedicines(Patient patient, ArrayList<Medicine> medicineList) {
		System.out.println("You gave " + patient.getName() + " these medicines: ");
		for (Medicine medicine:medicineList) {
			System.out.println(medicine.toString());
		}
	}

	private BloodType returnProperBloodType(String bloodTypeChoice) {
		BloodType bloodType = BloodType.UNKNOWN;
		switch (bloodTypeChoice.toLowerCase()) {
		case "0-":
			bloodType = BloodType.O_NEGATIVE;
			break;
		case "0+": 
			bloodType = BloodType.O_POSITIVE;
			break;
		case "a-":
			bloodType = BloodType.A_NEGATIVE;
			break;
		case "a+": 
			bloodType = BloodType.A_POSITIVE;
			break;
		case "b-":
			bloodType = BloodType.B_NEGATIVE;
			break;
		case "b+": 
			bloodType = BloodType.B_POSITIVE;
			break;
		case "ab-":
			bloodType = BloodType.AB_NEGATIVE;
			break;
		case "ab+":
			bloodType = BloodType.AB_POSITIVE;
			break;
		}
		return bloodType;
	}
	
	private void printMedicineQuestion() {
		System.out.println("Please enter the medicine ids separated with commas.\n"
				+ " Entering something else than the given format will result in "
				+ "you saying there is no need for medicines for the patient. 1,2,4,6");
		int count = 0;
		for (Medicine medicine:Medicine.values()) {
			System.out.println(count + "," + medicine);
			count++;
		}
	}

	/**
	 * @param day
	 * @param month
	 * @param year
	 * @return true if its correct, false if not
	 * Checks if the given date is in correct format
	 */
	private boolean checkHourlessDate(int day, int month, int year) {
		if (checkDayValue(day)) {
			if (checkMonthValue(month)) {
				if (checkYearValue(year)) {
					return true;
				}
			}
		}
		return false;
	}	

	/**
	 * @param date
	 * @return true if the format of the string is correct
	 */
	private boolean checkDateString(String date) {
		return date.length() == 10 && date.substring(2,3).equals("/") && date.substring(5,6).equals("/");
	}

	/**
	 * @param hospital
	 * @param patientName
	 * @param patientBirthdate
	 * @param patientId
	 * @param bloodType
	 * @param choice
	 * @param appointmentDate
	 * @param doctor
	 * @return Patient that was created
	 * Registers and returns a patient accordingly to its type.
	 */
	private Patient registerAndReturnAccordinglyToType(Hospital hospital, String patientName, Date patientBirthdate, 
			int patientId, BloodType bloodType, String choice, Date appointmentDate, Doctor doctor) {
		Patient newPatient;
		if (choice.equals("1")) {
			newPatient = new InmatePatient(patientName, patientBirthdate, patientId, bloodType, new ArrayList<Date>(Arrays.asList(appointmentDate)));
			hospital.addNewPatient(newPatient);
		}
		else if (choice.equals("2")) {
			newPatient = new EmergencyPatient(patientName, patientBirthdate, patientId, bloodType, new ArrayList<Date>(Arrays.asList(appointmentDate)));
			hospital.addNewPatient(newPatient);
		}
		else {
			newPatient = new WalkingCasePatient(patientName, patientBirthdate, patientId, bloodType, new ArrayList<Date>(Arrays.asList(appointmentDate)));
			hospital.addNewPatient(newPatient);
		}
		System.out.println("Succesfully registered the new patient");
		return newPatient;
	}

	/**
	 * @param input String which we hope has a format of DD/MM/YYYY
	 * @return the date object that is created from the string, null if its in a wrong format
	 */
	private Date initializeDateForInput(String input) {
		if (checkDateString(input)) {
			StringTokenizer dateTokenizer = new StringTokenizer(input, "/");
			try {
				int day = Integer.parseInt(dateTokenizer.nextToken());
				int month = Integer.parseInt(dateTokenizer.nextToken());
				int year = Integer.parseInt(dateTokenizer.nextToken());
				if (checkHourlessDate(day, month, year)) {
					Date date = new Date(year, month, day);
					return date;
				}
				else {
					System.out.println("You have entered a date value that doesn't exist in any calendar. Wrong integers!");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Please enter integers for date values.");
			}
		}
		return null;
	}
	
	/**
	 * @param keyboard
	 * @return Takes the date input and initializes it to a date if its in a correct date format
	 */
	private Date takeDateInputForSurgery(Scanner keyboard) {
		System.out.println("Please enter the surgery date in DD/MM/YYYY format.");
		String dateString = keyboard.nextLine();
		Date date = initializeDateForInput(dateString);
		return date;
	}
	
	/**
	 * @param keyboard
	 * @return Takes the date input and initializes it to a date if its in a correct date format
	 */
	private Date takeDateInput(Scanner keyboard) {
		System.out.println("Please enter the date in DD/MM/YYYY format.");
		String dateString = keyboard.nextLine();
		Date date = initializeDateForInput(dateString);
		return date;
	}

	
	
	/**
	 * @param hospital
	 * @param patient
	 * Finds and prints if there are any analysises for the parameter patient.
	 */
	private void getAndPrintAnalysises(Hospital hospital, Patient patient) {
		List<Analysis> analysis = hospital.getTestTechnican().findAnalysisesOfAPatient(patient);
		try {
			if (analysis.size() == 0) {
				throw new AnalysisNotFoundException();
			}
			else {
				System.out.println("Name: " + patient.getName() + "\n" + hospital.getTestTechnican().findAnalysisesOfAPatient(patient).toString());
			}
		}
		catch(AnalysisNotFoundException e) {
			System.out.println("Couldn't find an analysis for " + patient.getName());
		}
	}

	private void updateAfterRegisteringSurgery(Surgery surgery, Hospital hospital, Scanner keyboard, Doctor doctor, Date todaysDate) {
		createAndUpdateWithSurgery(surgery, hospital, keyboard, doctor);
		updateDoctorScheduleAfterRegisteringSurgery(surgery.getPatient(), doctor, todaysDate);
		printSuccesfulSurgeryRegistrationMessage(surgery.getDateOfSurgery());
	}
	
	/**
	 * @param hospital
	 * @param patient
	 * @param keyboard
	 * @param doctor
	 * @param todaysDate
	 * Registers a surgery for a doctors patient where the surgeon is the doctor who examined the patient in the first place.
	 */
	private void registerSurgeryForSurgeryCapable(Hospital hospital, Patient patient, Scanner keyboard, Doctor doctor, Date todaysDate) {
		Surgeon surgeon = (Surgeon)doctor;
		ArrayList<Medicine> medicineList = initializePatientsMedicines(keyboard);
		Date surgeryDate = takeDateInputForSurgery(keyboard);
		if (surgeryDate != null) {
			System.out.println("How many days should the patient rest after surgery.");
			try {
				int afterSurgeryRestDay = keyboard.nextInt();
				Surgery surgery = new Surgery(medicineList, surgeon, surgeryDate, patient, SurgeryType.getRandomSurgery(), afterSurgeryRestDay);
				updateAfterRegisteringSurgery(surgery, hospital, keyboard, doctor, todaysDate);
			}
			catch(InputMismatchException e) {
				System.out.println("Please enter an integer only.");
			}
		}
	}

	/**
	 * @param hospital
	 * @param patient
	 * @param keyboard
	 * @param doctor
	 * @param todaysDate
	 * Registers a surgery for a doctors patient. Since doctors can not perform a surgery if they are not surgeons, assigns a new surgeon for the job.
	 */
	private void registerSurgeryForNotSurgeryCapable(Hospital hospital, Patient patient, Scanner keyboard, Doctor doctor, Date todaysDate) {
		System.out.println("You are not a surgeon, a surgeon will be assigned to " + patient.getName() + ".");
		Surgeon surgeon = hospital.getRandomSurgeon();
		System.out.println("Assigned surgeon is " + surgeon.getName() + " with the id of: " + surgeon.getDoctorId());
		ArrayList<Medicine> medicineList = initializePatientsMedicines(keyboard);
		Date surgeryDate = takeDateInputForSurgery(keyboard);
		if (surgeryDate != null) {
			System.out.println("How many days should the patient rest after surgery.");
			try {
				int afterSurgeryRestDay = keyboard.nextInt();
				Surgery surgery = new Surgery(medicineList, surgeon, surgeryDate, patient, SurgeryType.getRandomSurgery(), afterSurgeryRestDay);
				updateAfterRegisteringSurgery(surgery, hospital, keyboard, doctor, todaysDate);
			}
			catch(InputMismatchException e) {
				System.out.println("Please enter an integer only.");
			}
		}
	}
	
	private void performSurgery(Patient patient, Doctor doctor, Date date) throws InterruptedException {
		System.out.println("Surgery is now underway for " + patient.getName());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Surgery was succesful, " + patient.getName() + "is now resting.");
		updateAfterSurgery(patient, doctor, date);
	}

	private void printSuccesfulSurgeryRegistrationMessage(Date surgeryDate) {
		System.out.println("\nSurgery succesfully registered, and will be held on " + surgeryDate.toString());
		System.out.println("Returning to main menu.\n");
	}

	private void createAndUpdateWithSurgery(Surgery surgery, Hospital hospital, Scanner keyboard, Doctor doctor) {
		surgery.getPatient().setSurgeryDate(surgery.getDateOfSurgery());
		surgery.getPatient().getDatesOfAppointments().add(surgery.getDateOfSurgery());
		initializeVisitDaysAfterSurgery(surgery.getPatient(), surgery.getSurgeon(), surgery.getDateOfSurgery(), surgery.getNumberOfDaysInHospitalCareAfterSurgery());
		hospital.getSurgeryScheduler().addSurgery(surgery);
		doctor.addToCheckedPatients(surgery.getPatient());
	}


	/**
	 * @param patient
	 * @param surgeon
	 * @param surgeryDate
	 * @param restDays
	 * Initializes the visit days, makes the appointments for the after surgery examinations.
	 */
	private void initializeVisitDaysAfterSurgery(Patient patient, Surgeon surgeon, Date surgeryDate, int restDays) {
		surgeon.getExaminationList().add(new Examination(surgeon,patient,surgeryDate));
		Date visitDay = surgeryDate;
		while (restDays != 0) {
			visitDay = surgeryDate.addOneDay(visitDay);
			restDays--;
			surgeon.getExaminationList().add(new Examination(surgeon,patient,visitDay));
			patient.getDatesOfAppointments().add(visitDay);
		}
	}

	/**
	 * @param keyboard
	 * @return Given medicines' ArrayList
	 * Asks the user for the medicines for the patientç
	 */
	private ArrayList<Medicine> initializePatientsMedicines(Scanner keyboard) {
		System.out.println("Enter the medicines that the patient will need after surgery.");
		printMedicineQuestion();
		String medicines = keyboard.nextLine();
		ArrayList<Medicine> medicineList = new ArrayList<Medicine>();
		if (checkMedicineInput(medicines)) {
			addMedicines(medicineList, medicines);
		}
		return medicineList;
	}

	/**
	 * @param medicineList
	 * @param medicines
	 * Adds the given medicine strings medicines to the medicine ArrayList.
	 */
	private void addMedicines(ArrayList<Medicine> medicineList, String medicines) {
		StringTokenizer medicineTokenizer = new StringTokenizer(medicines,",");
		while (medicineTokenizer.hasMoreTokens()) {
			int nextMedicine = Integer.parseInt(medicineTokenizer.nextToken());
			if (nextMedicine < Medicine.values().length ) {
				medicineList.add(Medicine.values()[nextMedicine]);
			}
			else {
				System.out.println(nextMedicine + ", doesnt exists as a medicine id. Nothing was done for that id!");
			}
		}
	}

	/**
	 * @param hospital
	 * @param doctor
	 * @param patient
	 * @param date
	 * Makes a blood test and prints the result of the test.
	 * @throws InterruptedException 
	 */
	private void makeBloodTest(Hospital hospital, Doctor doctor, Patient patient, Date date) throws InterruptedException {
		System.out.println("Patient has gone to the test technician to take a blood test.");
		TimeUnit.SECONDS.sleep(2);
		hospital.getTestTechnican().makeBloodTest(doctor, patient, date);
		System.out.println("Results are in!");
		Analysis analysis = hospital.getTestTechnican().findAndReturnLastAnalysis(date);
		BloodTest bloodTest =  (BloodTest)analysis;	
		TimeUnit.SECONDS.sleep(1);
		System.out.println(bloodTest.toString());
	}

	/**
	 * @param hospital
	 * @param doctor
	 * @param patient
	 * @param radiologyDate
	 * Makes a radiology test and prints the result of the test.
	 * @throws InterruptedException 
	 */
	private void makeRadiologyTest(Hospital hospital, Doctor doctor, Patient patient, Date radiologyDate) throws InterruptedException {
		System.out.println("Patient has gone to the test technician to take a radiology test.");
		TimeUnit.SECONDS.sleep(2);
		hospital.getTestTechnican().makeRadiologyTest(doctor, patient, radiologyDate);
		System.out.println("Results are in!");
		Analysis analysis = hospital.getTestTechnican().findAndReturnLastAnalysis(radiologyDate);
		Radiology radiologyTest =  (Radiology)analysis;
		TimeUnit.SECONDS.sleep(1);
		System.out.println(radiologyTest.toString());	
	}

	/**
	 * @param hospital
	 * @param appointmentDate
	 * @return The doctor who has been taken appointment from, null if there are no empty schedules.
	 */
	private Doctor takeAppointment(Hospital hospital, Date appointmentDate) {
		for (Doctor doctor:hospital.getDoctorList()) {
			for (Examination examination:doctor.getExaminationList()) {
				if (!appointmentDate.equals(examination.getDateOfExamination())) {
					doctor.getExaminationList().add(new Examination(doctor, null, appointmentDate));
					return doctor;
				}
			}
		}
		System.out.println("No free spaces for appointments at the moment. Please try again later.");
		return null;
	}

	/**
	 * @param medicineChoices String which should be a type of (1,2,3,4,6,2,3,5)
	 * @return if its a correct input string true, if not false.
	 */
	private boolean checkMedicineInput(String medicineChoices) {//TODO 12,15,16
		int count = 0;
		for (char character:medicineChoices.toCharArray()) {
			if (count % 2 == 0) {
			}
			else {
				if (character == ',') {
				}
				else {
					return false;
				}
			}
		}
		return true;
	}
	
	private void updateAfterSurgery(Patient patient, Doctor doctor, Date examinationDate) {
		Examination examinationToBeRemoved = null;
		for (Date date1:patient.getDatesOfAppointments()) {
			if (date1.equals(examinationDate)) {
				examinationToBeRemoved = doctor.findExamination(examinationDate);
			}
		}
		doctor.getExaminationList().remove(examinationToBeRemoved);
		patient.setSurgeryDate(new Date());
	}
	
	private void updateDoctorScheduleAfterRegisteringSurgery(Patient patient, Doctor doctor, Date examinationDate) {
		Examination examinationToBeRemoved = null;
		for (Date date1:patient.getDatesOfAppointments()) {
			if (date1.equals(examinationDate)) {
				examinationToBeRemoved = doctor.findExamination(examinationDate);
			}
		}
		doctor.getExaminationList().remove(examinationToBeRemoved);
	}
	
	private void updateAfterFinishingWithPatient(Patient patient, Doctor doctor, Date examinationDate, Hospital hospital) {
		Examination examinationToBeRemoved = null;
		for (Date date1:patient.getDatesOfAppointments()) {
			if (date1.equals(examinationDate)) {
				examinationToBeRemoved = doctor.findExamination(examinationDate);
			}
		}
		doctor.getExaminationList().remove(examinationToBeRemoved);
		doctor.getCheckedPatients().add(patient);
		hospital.getPatientList().remove(patient);
		hospital.getPastPatients().add(patient);
		System.out.println("Good bye " + patient.getName());
	}
	
	/**
	 * @param hospital
	 * @throws IllegalBirthdateException when receptionist has a birthdate that can not happen(30/02/2019)
	 * @throws InterruptedException I used these .sleep() methods for making the simulation to have more life
	 * @throws AnalysisNotFoundException 
	 * @throws PatientNotFoundException
	 * Whole simulation is in this class. There is one error that I couldn't solve. After some operations for the doctors
	 * Main menu gets printed twice for some reason. Mostly happens when we ask the doctor to enter an id of a patient for some operations.
	 * I created some comment pieces in the code for it to be clearer.
	 */
	public void start(Hospital hospital) throws IllegalBirthdateException, InterruptedException, AnalysisNotFoundException, PatientNotFoundException {
		while (true) {
			printMenu();
			Scanner keyboard = new Scanner(System.in);
			String jobChoice = keyboard.nextLine();
			if (jobChoice.equals("1")) {
				while(true) {
					System.out.println("Please enter your id for the operations. X to exit.\n"
							+ "(We currently have 5 hired doctors, their ids are ranging from 1001 to 1005.)");
					String doctorIdString = keyboard.nextLine();
					if (doctorIdString.toLowerCase().equals("x")) {
						break;
					}
					try {
						int doctorId = Integer.parseInt(doctorIdString);
						Doctor doctor = hospital.findDoctorWithId(doctorId);
						if (doctor != null) {
							while (true) {
								printDoctorMenu(doctor);
								String doctorChoice = keyboard.nextLine();
								if (doctorChoice.equals("1")) {
									Date date = takeDateInput(keyboard);
									if (date != null) {
										System.out.println("On " + date.toString() + " you will see these patients:\n");
										List<Patient> patientsOnASpecificDay = doctor.findSpecificDayPatients(date);//Finds a doctors specific dates patients to be printed
										if (patientsOnASpecificDay.size() == 0) {
											System.out.println("You will see no one today. Free day!\n");
										}
										else {
											System.out.println(patientsOnASpecificDay.toString().substring(1,patientsOnASpecificDay.toString().length()-1));
											System.out.println("\nWould you like to visit a patient? Enter the patients id to visit the patient.\n"
													+ " If the entered value is not an id, you will be returned to the main menu.");
											String visitChoice = keyboard.nextLine();
											try {
												int patientId = Integer.parseInt(visitChoice);
												for (Patient patient:patientsOnASpecificDay) {
													if (patient.getPatientId() == patientId) {
														if (hospital.getSurgeryScheduler().isTodayTheSurgeryDayForPatient(patient, date, doctor) && doctor.isSurgeon()) { // if its the day of the surgery, perform the surgery.
															performSurgery(patient, doctor, date);				
														}
														else {
															System.out.println("You have arrived in " + patient.getName() + "'s room for an examination!");
															System.out.println("You are examining the patient");
															TimeUnit.SECONDS.sleep(2);
															System.out.println("You have examined the patient. \n "
																	+ "Do you think a blood test or a radiology test is needed for " + patient.getName()+ " ? "
																	+ "B for blood test, R for radiology test, if its not necessary enter something else");
															String testChoice = keyboard.nextLine();
															if (testChoice.toLowerCase().equals("r")) {
																makeRadiologyTest(hospital, doctor, patient, date);
																System.out.println("\n According to these results, what is your suggestion for the patient?\n"
																		+ " S for surgery, P to write down some prescription or something else to let the patient go.");
																String choice = keyboard.nextLine();
																if (choice.toLowerCase().equals("s")) {
																	if (doctor.isSurgeon()) {
																		registerSurgeryForSurgeryCapable(hospital, patient, keyboard, doctor, date);//if the current doctor is surgery capable, register accordingly
																	}
																	else {
																		registerSurgeryForNotSurgeryCapable(hospital, patient, keyboard, doctor, date);//if the current doctor is not surgery capable, register with a surgeon from hospital
																	}
																}
																else if (choice.toLowerCase().equals("p")) {
																	printMedicineQuestion();
																	String medicines = keyboard.nextLine();
																	if (checkMedicineInput(medicines)) {
																		ArrayList<Medicine> medicineList = new ArrayList<Medicine>();
																		addMedicines(medicineList, medicines);
																		printGivenMedicines(patient, medicineList);
																		updateAfterFinishingWithPatient(patient, doctor, date, hospital);
																	}
																	else {
																		System.out.println("You have entered medicine ids in wrong format.");
																	}
																}
																else {
																	updateAfterFinishingWithPatient(patient, doctor, date, hospital);
																}																																		
															}
															else if (testChoice.toLowerCase().equals("b")) {
																makeBloodTest(hospital, doctor, patient, date);
																System.out.println("\n According to these results, what is your suggestion for the patient?\n"
																		+ " S for surgery, P to write down some prescription or something else to let the patient go.");
																String choice = keyboard.nextLine();
																if (choice.toLowerCase().equals("s")) {
																	if (doctor.isSurgeon()) {
																		registerSurgeryForSurgeryCapable(hospital, patient, keyboard, doctor, date);
																	}
																	else {
																		registerSurgeryForNotSurgeryCapable(hospital, patient, keyboard, doctor, date);
																	}
																}
																else if (choice.toLowerCase().equals("p")) {
																	printMedicineQuestion();
																	System.out.println("From the given results please choose from given medicines.\n"
																			+ " You can choose with their id number separated by commas. (1,3,5,2,7)");
																	String medicineChoices = keyboard.nextLine();
																	if (checkMedicineInput(medicineChoices)) {
																		ArrayList<Medicine> medicineList = new ArrayList<Medicine>();
																		addMedicines(medicineList, medicineChoices);
																		printGivenMedicines(patient, medicineList);
																		updateAfterFinishingWithPatient(patient, doctor, date, hospital);
																	}
																	else {
																		System.out.println("You have entered medicine ids in wrong format.");
																	}
																}
																else {
																	updateAfterFinishingWithPatient(patient, doctor, date, hospital);
																}
															}
															else {
																System.out.println("Does the patient need medicine or a surgery even without taking any tests?\n"
																		+ " P to write a prescription, S to appoint a surgery.\n If you enter something else patient will leave the hospital.");
																String choice = keyboard.nextLine();
																if (choice.toLowerCase().equals("p")) {
																	System.out.println("From the given results please choose from given medicines."
																			+ " You can choose with their id number separated by commas. (1,3,5,2,7)");
																	Medicine[] medicines = Medicine.values();
																	String medicineChoices = keyboard.nextLine();
																	if (checkMedicineInput(medicineChoices)) {
																		StringTokenizer medicineTokenizer = new StringTokenizer(medicineChoices, ",");
																		System.out.println("You gave " + patient.getName() + " the medicines below.\n");
																		int count = 0;
																		while(medicineTokenizer.hasMoreTokens()) {
																			System.out.println(count + "," +medicines[Integer.parseInt(medicineTokenizer.nextToken())]);
																			count++;
																		}
																		updateAfterFinishingWithPatient(patient, doctor, date, hospital);
																	}
																	else {
																		System.out.println("You have entered medicine ids in wrong format.");
																	}
																}
																else if (choice.toLowerCase().equals("s")) {
																	if (doctor.isSurgeon()) {
																		registerSurgeryForSurgeryCapable(hospital, patient, keyboard, doctor, date);
																	}
																	else {
																		registerSurgeryForNotSurgeryCapable(hospital, patient, keyboard, doctor, date);
																	}
																}
																else {
																	updateAfterFinishingWithPatient(patient, doctor, date, hospital);
																}
															}	
														}
													}
												}
											}
											catch (NumberFormatException e) {
											}
										}
									}
								}
								else if (doctorChoice.equals("2")) {
									if (doctor.getExaminationList().size() == 0) {
										System.out.println("You currently have noone under your care.");
									}
									else {
										Set<Patient> setPatient = new HashSet<Patient>();
										for (Examination examination:doctor.getExaminationList()) {
											setPatient.add(examination.getPatient());
										}
										System.out.println(setPatient.toString());
									}
								}
								else if (doctorChoice.equals("3")) {
									System.out.println("Whose analysis would you like to search for,"
											+ " please enter your method of searching.\n 1 for patient id, 2 for name. ");
									String searchChoice = keyboard.nextLine();
									if (searchChoice.equals("1")) {
										System.out.println("Please enter the patients id");
										try {
											int patientId = keyboard.nextInt();
											Patient patient = hospital.findPatientWithId(patientId);	
											try {
												if (patient == null) {
													throw new PatientNotFoundException();
												}
												else {
													getAndPrintAnalysises(hospital, patient);
												}
											}
											catch (PatientNotFoundException e) {
												System.out.println("Couldn't find anyone with id " + patientId);
											}
										}
										catch (InputMismatchException e){
											System.out.println("For id, you can only enter integers. Try again.");
										}
									}
										
									else if (searchChoice.equals("2")) {
										System.out.println("Please enter patients full name.");
										String patientName = keyboard.nextLine();
										Patient patient = hospital.findPatientWithName(patientName);	
										try {
											if (patient == null) {
												throw new PatientNotFoundException();
											}
											else {
												getAndPrintAnalysises(hospital, patient);
											}
										}
										catch (PatientNotFoundException e) {
											System.out.println("Couldn't find anyone with name: " + patientName);
										}
									}
								}
								else if (doctorChoice.equals("4")) {
									if (doctor.getCheckedPatients().size() == 0) {
										System.out.println("You haven't examined anyone yet!");
									}
									else {
										System.out.println(doctor.getCheckedPatients().toString().substring(1,doctor.getCheckedPatients().size() - 1));
									}
								}
								else if (doctorChoice.equals("5")) {
									System.out.println("Who would you like to search for,\n"
											+ " please enter your method of searching. 1 for patient id, 2 for name. ");
									String searchChoice = keyboard.nextLine();
									if (searchChoice.equals("1")) {
										System.out.println("Please enter the patients id");
										try {
											int patientId = keyboard.nextInt();
											Patient patient = hospital.findPatientWithId(patientId);
											try {
												if (patient != null) {
													if (doctor.getCheckedPatients().contains(patient)) {
														System.out.println(patient.toString());
													}
													else {
														throw new PatientNotFoundException();
													}
												}
											}
											catch (PatientNotFoundException e) {
												System.out.println("Couldn't find anyone with id " + patientId);
											}
										}
										catch (InputMismatchException e){
											System.out.println("For id, you can only enter integers. Try again.");
										}
									}
									else if (searchChoice.equals("2")) {
										System.out.println("Please enter patients full name.");
										String patientName = keyboard.nextLine();
										Patient patient = hospital.findPatientWithName(patientName);
										try {
											if (patient == null) {
												throw new PatientNotFoundException();
											}
										
											else {
												if (doctor.getCheckedPatients().contains(patient)) {
													System.out.println(patient.toString());
												}
												else {
													System.out.println("You are not the doctor of " + patient.getName());
												}
											}
										}
										catch (PatientNotFoundException e) {
											System.out.println("Couldn't find anyone with name: " + patientName);
										}
									}
									else {
										System.out.println("You can only choose from 1 and 2. Please try again.");
									}
								}
								else if (doctorChoice.equals("6")) {
									System.out.println("Whose surgery would you like to search for,"
											+ " please enter your method of searching.\n 1 for patient id, 2 for name. ");
									String searchChoice = keyboard.nextLine();
									if (searchChoice.equals("1")) {
										System.out.println("Please enter the patients id");
										try {
											int patientId = keyboard.nextInt();
											Patient patient = hospital.findPatientWithId(patientId);
											try {
												if (patient != null) {
													if (hospital.getSurgeryScheduler().findSurgeriesForAPatient(patient).size() == 0) {
														System.out.println("There are no surgeries for " + patient.getName());
													}
													else {
														System.out.println(hospital.getSurgeryScheduler().findSurgeriesForAPatient(patient).toString().
																substring(1,hospital.getSurgeryScheduler().findSurgeriesForAPatient(patient).size() - 1));
													}
												}
												else {
													throw new PatientNotFoundException();
												}
											}
											catch (PatientNotFoundException e) {
												System.out.println("Couldn't find anyone with id " + patientId);
											}
										}
										catch (InputMismatchException e){
											System.out.println("For id, you can only enter integers. Try again.");
										}
									}
									else if (searchChoice.equals("2")) {
										System.out.println("Please enter patients full name.");
										String patientName = keyboard.nextLine();
										Patient patient = hospital.findPatientWithName(patientName);
										try {
											if (patient != null) {
												if (hospital.getSurgeryScheduler().findSurgeriesForAPatient(patient).size() == 0) {
													System.out.println("There are no surgeries for " + patient.getName());
												}
												else {
													System.out.println(hospital.getSurgeryScheduler().findSurgeriesForAPatient(patient).toString().
															substring(1,hospital.getSurgeryScheduler().findSurgeriesForAPatient(patient).size() - 1));
												}
											}
											else {
												throw new PatientNotFoundException();
											}
										}
										catch (PatientNotFoundException e) {
											System.out.println("Couldn't find anyone with the name " + patientName);
										}
									}
									else {
										System.out.println("You can only choose from 1 and 2. Please try again.");
									}
								}
								else if (doctorChoice.toLowerCase().equals("x")) {
									break;
								}							
							}
						}
						else {
							System.out.println("We do not have your id in the database. Please try again.");
						}
					}
					catch (NumberFormatException e) {
						System.out.println("Please do enter an integer");
					}
				}
			}
			else if (jobChoice.equals("2")) {
				System.out.println("Welcome back receptionist.\n");
				while (true) {
					System.out.println("Are there any new patients to register? Y for yes, N for no.");
					String patientChoice = keyboard.nextLine();
					if (patientChoice.toLowerCase().equals("y")) {
						int patientId = hospital.findMaxPatientId() + 1;
						System.out.println("Please enter the patient name. ");
						String patientName = keyboard.nextLine();
						System.out.println("Do you know the patients blood type? \nPlease enter in this format if you do, A+,A-,B+,B-,AB+,AB-,0-,0+."
								+ "\n if not specified it will be entered as unknown.");
						String bloodTypeChoice = keyboard.nextLine();
						BloodType bloodType = returnProperBloodType(bloodTypeChoice);
						System.out.println("Enter the patients birthdate in DD/MM/YYYY format.");
						String patientBirthdateString = keyboard.nextLine();
						if (checkDateString(patientBirthdateString)) {
							StringTokenizer dateTokenizer = new StringTokenizer(patientBirthdateString, "/");
							try {
								int day = Integer.parseInt(dateTokenizer.nextToken());
								int month = Integer.parseInt(dateTokenizer.nextToken());
								int year = Integer.parseInt(dateTokenizer.nextToken());
								if (checkHourlessDate(day, month, year)) {
									Date patientBirthdate = new Date(day, month, year);
									while (true) {
										System.out.println("At what date do you want an appointment? Enter in DD/MM/YYYY format.");
										String wantedDateString = keyboard.nextLine();
										if (checkDateString(wantedDateString)) {
											dateTokenizer = new StringTokenizer(wantedDateString, "/");
											try {
												int dateDay = Integer.parseInt(dateTokenizer.nextToken());
												int dateMonth = Integer.parseInt(dateTokenizer.nextToken());
												int dateYear = Integer.parseInt(dateTokenizer.nextToken());
												if (checkHourlessDate(dateDay, dateMonth, dateYear)) {
													Date appointmentDate = new Date(dateYear, dateMonth, dateDay);
													Doctor doctor = takeAppointment(hospital, appointmentDate);
													System.out.println("Succesfully took an appointment. Your doctor is " + doctor.getName());
													System.out.println("What type of a patient are you registering? \n1 for an inmate,"
															+ " 2 for an emergency case.\n If not specified it will be assigned "
															+ "as a walking case automatically.");
													String patientTypeChoice = keyboard.nextLine();
													Patient newPatient = registerAndReturnAccordinglyToType(hospital, patientName, patientBirthdate,
															patientId, bloodType, patientTypeChoice, appointmentDate, doctor);
													Examination examination = doctor.findExamination(appointmentDate);
													if (examination != null) {
														examination.setPatient(newPatient);
														break;
													}
												}	
												else {
													System.out.println("The date you want is not available for you, please try again.");
												}
											}
											catch (NumberFormatException e){
												System.out.println("Please do not enter letters into the date value");
											}
										}
									}
								}
							}
							catch (NumberFormatException e) {
								System.out.println("Please do not enter letters into the birthdate value");
							}
						}
						else {
							System.out.println("Birthdate has to be in DD/MM/YYYY format");
						}
					}
					else if (patientChoice.toLowerCase().equals("n")) {
						System.out.println("Are you done for the day? Y for yes, anything else for no");
						String doneReceptionChoice = keyboard.nextLine();
						if (doneReceptionChoice.toLowerCase().equals("y")) {
							break;
						}
						else {
						}
					}
				}
			}
			else if (jobChoice.toLowerCase().equals("x")) {
				System.out.println("Exiting!");
				break;
			}
			else {
				System.out.println("Please choose 1 or 2 to do operations, x to exit.");
			}
		}
	}
}
