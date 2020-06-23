package businessLayer;

/**
 * @author Gorkem
 * 
 * A class that is a derived class of Analysis class, Responsible for holding the BloodTestReport.
 *  Also has the derived class's attributes such as date of the analysis and the report id which is different for every analysis in the hospital.
 */
public class BloodTest extends Analysis {
	
	private BloodTestReport bloodTestReport;

	public BloodTest(Patient patient, Doctor doctor, int reportId, Date dateOfAnalysisReport) {
		super(reportId, dateOfAnalysisReport);
		this.bloodTestReport = new BloodTestReport(patient);
	}
	
	public String toString() {
		return "--------------------------------------------\nDate: " + getDateOfAnalysisReport().toString() + "     Id: " + getReportId() + "\n" + getBloodTestReport().toString() + "\n---------------------------------------------\n";
	}
	
	public BloodTestReport getBloodTestReport() {
		return bloodTestReport;
	}
}
