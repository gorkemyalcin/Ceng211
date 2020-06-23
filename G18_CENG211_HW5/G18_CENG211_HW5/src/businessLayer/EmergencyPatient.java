package businessLayer;

import java.util.ArrayList;

/**
 * @author Gorkem
 *
 */
public class EmergencyPatient extends Patient {

	public EmergencyPatient(String name, Date birthDate, int idNumber, BloodType bloodType, ArrayList<Date> appointmentDates) {
		super(PatientType.EMERGENCY, name, birthDate, idNumber, bloodType, appointmentDates);
	}

}
