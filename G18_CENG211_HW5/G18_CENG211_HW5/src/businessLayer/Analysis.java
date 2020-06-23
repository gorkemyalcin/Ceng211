package businessLayer;

/**
 * @author Gorkem
 *
 */
public class Analysis {

	private int reportId;
	private Date dateOfAnalysisReport;

	public Analysis(int reportId, Date dateOfAnalysisReport) {
		this.setReportId(reportId);
		this.setDateOfAnalysisReport(dateOfAnalysisReport);
	}
	
	public Analysis() {
		this.setReportId(-1);
		this.setDateOfAnalysisReport(new Date());
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public Date getDateOfAnalysisReport() {
		return dateOfAnalysisReport;
	}

	public void setDateOfAnalysisReport(Date dateOfAnalysisReport) {
		this.dateOfAnalysisReport = dateOfAnalysisReport;
	}
}
