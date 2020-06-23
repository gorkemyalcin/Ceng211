package businessLayer;

import java.util.ArrayList;

/**
 * @author Gorkem
 *
 */
public class InmatePatient extends Patient{

	public InmatePatient(String name, Date birthDate, int idNumber, BloodType bloodType, ArrayList<Date> appointmentDates) {
		super(PatientType.INMATE, name, birthDate, idNumber, bloodType, appointmentDates);
	}

}
