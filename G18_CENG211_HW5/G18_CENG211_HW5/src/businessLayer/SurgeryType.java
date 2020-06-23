package businessLayer;

import java.util.Random;

/**
 * @author Gorkem
 *
 */
public enum SurgeryType {
	APPENDECTOMY, BREAST_BIOPSY, CATARACT, 
	HYSTERECTOMY, HYSTEROSCOPY, MASTECTOMY, 
	PROSTATECTOMY, ORTHOPEDIC, RECONSTRUCTIVE,
	ROBOTIC, VASCULAR, UROLOGIC, ENDOSCOPY, RHINOPLASTY;
	
	public static SurgeryType getRandomSurgery() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
	}
}
