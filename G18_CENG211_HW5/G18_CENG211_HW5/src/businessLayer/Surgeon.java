package businessLayer;

import java.util.ArrayList;

/**
 * @author Gorkem
 *
 */
public class Surgeon extends Doctor{

	public Surgeon(String name, DoctorDiscipline discipline, int doctorId, ArrayList<Examination> examinationList, Hospital hospital) {
		super(name, discipline, doctorId, examinationList, hospital);
		this.surgeon = true;
	}
}
