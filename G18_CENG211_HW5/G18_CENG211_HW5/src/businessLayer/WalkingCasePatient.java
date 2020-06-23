package businessLayer;

import java.util.ArrayList;

/**
 * @author Gorkem
 *
 */
public class WalkingCasePatient extends Patient{

	public WalkingCasePatient(String name, Date birthDate, int idNumber, BloodType bloodType, ArrayList<Date> appointmentDates) {
		super(PatientType.WALKING_CASE, name, birthDate, idNumber, bloodType, appointmentDates);
		
	}
}
