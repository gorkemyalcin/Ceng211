package businessLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gorkem
 * A helper class for operating the tests such as blood and radiology.
 */
public class TestTechnician {
	
	private ArrayList<Analysis> analysises;

	public TestTechnician() {
		this.setAnalysises(new ArrayList<Analysis>());
	}
	
	/**
	 * @param doctor 
	 * @param patient
	 * @param dateOfAnalysisReport
	 * Makes a new blood test for the patient, adds it to the analysis list.
	 */
	public void makeBloodTest(Doctor doctor, Patient patient, Date dateOfAnalysisReport) {
		int id = findMaxIdAnalysis();
		BloodTest bloodTest = new BloodTest(patient, doctor, id,  dateOfAnalysisReport);
		getAnalysises().add(bloodTest);
	}
	
	/**
	 * @param doctor 
	 * @param patient
	 * @param dateOfAnalysisReport
	 * Makes a new radiology test for the patient, adds it to the analysis list.
	 */
	public void makeRadiologyTest(Doctor doctor, Patient patient, Date dateOfAnalysisReport) {
		int id = findMaxIdAnalysis();
		Radiology radiology = new Radiology(patient, doctor, id,  dateOfAnalysisReport, RadiologyType.getRandomComponentLevel());//CHANGE ANALYSIS TO RADILIOGOY
		getAnalysises().add(radiology);
	}

	/**
	 * @return finds and returns the maximum id of all the analysis.
	 */
	private int findMaxIdAnalysis() {
		int id = 0;
		for (Analysis analysis:getAnalysises()) {
			if (analysis.getReportId() > id)
				id = analysis.getReportId();
		}
		return id;
	}


	public ArrayList<Analysis> getAnalysises() {
		return analysises;
	}

	public void setAnalysises(ArrayList<Analysis> analysises) {
		this.analysises = analysises;
	}
	
	/**
	 * @param date, specific date which is searched with 
	 * @return the analysis for the found test, since the return type is <T extends Analysis> T, it can be either blood test or radiology test.
	 */
	@SuppressWarnings("unchecked")//Generic
	public <T extends Analysis> T findAndReturnLastAnalysis(Date date) {
		if (getAnalysises().get(getAnalysises().size()-1).getDateOfAnalysisReport().equals(date)) {
			return (T) getAnalysises().get(getAnalysises().size()-1);
		}
		return null;
	}

	/**
	 * @param patient
	 * @return List of analysises
	 * Finds and returns the list of the analysis that the patient had.
	 */
	public List<Analysis> findAnalysisesOfAPatient(Patient patient) {
		List<Analysis> returnList = new ArrayList<Analysis>();
		for (Analysis analysis:getAnalysises()) {
			for (Date dateOfAnalysis:patient.getDatesOfAppointments()) {
				if (analysis.getDateOfAnalysisReport().equals(dateOfAnalysis)){
					returnList.add(analysis);
				}
			}
		}
		return returnList;
	}

}
