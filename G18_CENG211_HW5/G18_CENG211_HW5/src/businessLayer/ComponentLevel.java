package businessLayer;

import java.util.Random;

/**
 * @author Gorkem
 * A helper enum class for the blood test results.
 */
public enum ComponentLevel {
	LOW, NORMAL, HIGH;
	
	public static ComponentLevel getRandomComponentLevel() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
	}
}
