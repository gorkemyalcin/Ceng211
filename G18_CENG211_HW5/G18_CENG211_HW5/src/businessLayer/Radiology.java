package businessLayer;

/**
 * @author Gorkem
 * 
 * A class that is a derived class of Analysis class, Responsible for holding the radiologyReport and typeOfRadiology.
 *  Also has the derived class's attributes such as date of the analysis and the report id which is different for every analysis in the hospital.
 */
public class Radiology extends Analysis{

	private RadiologyType typeOfRadiology;
	private RadiologyReport radiologyReport;

	public Radiology(Patient patient, Doctor doctor, int reportId, Date dateOfAnalysisReport, RadiologyType typeOfRadiology) {
		super(reportId, dateOfAnalysisReport);
		this.typeOfRadiology = typeOfRadiology;//random
		this.radiologyReport = new RadiologyReport();//random
	}
	
	public String toString() {
		return "\n-------------------------------------------------\nDate: " + getDateOfAnalysisReport().toString() + " Type of Radiology " + getTypeOfRadiology() + "     Id: " + getReportId() + "\n" + getFindingsOfRadiology().toString() + "\n---------------------------------------------\n";
	}
	
	public RadiologyType getTypeOfRadiology() {
		return typeOfRadiology;
	}
	
	public void setTypeOfRadiology(RadiologyType typeOfRadiology) {
		this.typeOfRadiology = typeOfRadiology;
	}
	
	public RadiologyReport getFindingsOfRadiology() {
		return radiologyReport;
	}
}
