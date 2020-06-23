package businessLayer;

import java.util.Random;

/**
 * @author Gorkem
 * A helper enum class for radiology test results
 *
 */
public enum RadiologyComponentResult {
	NORMAL, NEEDS_ATTENTION;
	
	public static RadiologyComponentResult getRandomComponentLevel() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
	}
}
