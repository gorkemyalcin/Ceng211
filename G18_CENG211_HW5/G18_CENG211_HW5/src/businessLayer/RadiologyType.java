package businessLayer;

import java.util.Random;

/**
 * @author Gorkem
 * An enum class for the types of radiologies.
 *
 */
public enum RadiologyType {
	ULTRASOUND, COMPUTED_TOMOGRAPHY, FLUOROSCOPY,
	MR, X_RAY, NUCLEAR_MEDICINE, PET_SCAN, MAMMOGRAPHY;
	
	public static RadiologyType getRandomComponentLevel() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
	}
}
