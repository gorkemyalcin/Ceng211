package businessLayer;

import java.util.Random;

/**
 * @author Gorkem
 * A report class which is responsible for holding the date of the blood test results. Since I had no way of collecting these values,
 *  I just initialized them randomly except for blood type, for which I used the parameter patient, then got the patients blood type and initialized that way.
 *  Didn't wanted to give 2 different blood types for the patient, 
 *  since if I randomized the blood type then it would be different from when registering the patient, because I also take the patients blood type when registering.
 */
public class BloodTestReport {
	
	private BloodType bloodType;
	private boolean hiv;
	private ComponentLevel calcium;
	private ComponentLevel albumin;
	private ComponentLevel protein;
	private ComponentLevel bilirubin;
	private ComponentLevel chloride;
	private ComponentLevel creatinine;
	private ComponentLevel bloodSugar;
	private ComponentLevel phosphorus;
	private ComponentLevel potassium;
	private ComponentLevel sodium;
	private ComponentLevel cholesterol;
	private ComponentLevel triglycerides;
	private ComponentLevel hdlCholesterol;
	private ComponentLevel ldlCholesterol;
	
	public BloodTestReport(Patient patient) {
		this.setBloodType(patient.getBloodType()); //:D 
		Random rand = new Random();
		if (rand.nextInt() % 2 == 0) {
			this.setHiv(true);
		}
		else {
			this.setHiv(false);
		}
		this.setCalcium(ComponentLevel.getRandomComponentLevel());
		this.setAlbumin(ComponentLevel.getRandomComponentLevel());
		this.setProtein(ComponentLevel.getRandomComponentLevel());
		this.setBilirubin(ComponentLevel.getRandomComponentLevel());
		this.setChloride(ComponentLevel.getRandomComponentLevel());
		this.setCreatinine(ComponentLevel.getRandomComponentLevel());
		this.setBloodSugar(ComponentLevel.getRandomComponentLevel());
		this.setPhosphorus(ComponentLevel.getRandomComponentLevel());
		this.setPotassium(ComponentLevel.getRandomComponentLevel());
		this.setSodium(ComponentLevel.getRandomComponentLevel());
		this.setCholesterol(ComponentLevel.getRandomComponentLevel());
		this.setTriglycerides(ComponentLevel.getRandomComponentLevel());
		this.setHdlCholesterol(ComponentLevel.getRandomComponentLevel());
		this.setLdlCholesterol(ComponentLevel.getRandomComponentLevel());
	}
	
	public String toString() {
		return "Blood type: " + getBloodType() + "\nHIV: " + isHiv() + "\nAlbumin: " + getAlbumin() + "\nBilirubin: " + getBilirubin() +
				"\nBlood Sugar: " + getBloodSugar() + "\nCalcium: " + getCalcium() + "\nChloride: " + getChloride() + "\nCholesterol: " + 
				getCholesterol() + "\nCreatinine: " + getCreatinine() + "\nHDL Cholesterol: " + getHdlCholesterol() + "\nLDL Cholesterol: " +
				getLdlCholesterol() + "\nPhosphorus: " + getPhosphorus() +"\nPotassium: " + getPotassium() + "\nProtein: " + getProtein() + "\nTriglycerides: " + 
				getTriglycerides() + "\nSodium: " + getSodium();
	}

	private BloodType getBloodType() {
		return bloodType;
	}

	private void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

	private ComponentLevel getCalcium() {
		return calcium;
	}

	private void setCalcium(ComponentLevel calcium) {
		this.calcium = calcium;
	}

	private ComponentLevel getAlbumin() {
		return albumin;
	}

	private void setAlbumin(ComponentLevel albumin) {
		this.albumin = albumin;
	}

	private ComponentLevel getProtein() {
		return protein;
	}

	private void setProtein(ComponentLevel protein) {
		this.protein = protein;
	}

	private boolean isHiv() {
		return hiv;
	}

	private void setHiv(boolean hiv) {
		this.hiv = hiv;
	}

	private ComponentLevel getBilirubin() {
		return bilirubin;
	}

	private void setBilirubin(ComponentLevel bilirubin) {
		this.bilirubin = bilirubin;
	}
	private ComponentLevel getChloride() {
		return chloride;
	}

	private void setChloride(ComponentLevel chloride) {
		this.chloride = chloride;
	}

	private ComponentLevel getCreatinine() {
		return creatinine;
	}

	private void setCreatinine(ComponentLevel creatinine) {
		this.creatinine = creatinine;
	}

	private ComponentLevel getBloodSugar() {
		return bloodSugar;
	}

	private void setBloodSugar(ComponentLevel bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	private ComponentLevel getPhosphorus() {
		return phosphorus;
	}

	private void setPhosphorus(ComponentLevel phosphorus) {
		this.phosphorus = phosphorus;
	}

	private ComponentLevel getPotassium() {
		return potassium;
	}

	private void setPotassium(ComponentLevel potassium) {
		this.potassium = potassium;
	}

	private ComponentLevel getSodium() {
		return sodium;
	}

	private void setSodium(ComponentLevel sodium) {
		this.sodium = sodium;
	}

	private ComponentLevel getCholesterol() {
		return cholesterol;
	}

	private void setCholesterol(ComponentLevel cholesterol) {
		this.cholesterol = cholesterol;
	}

	private ComponentLevel getTriglycerides() {
		return triglycerides;
	}

	private void setTriglycerides(ComponentLevel triglycerides) {
		this.triglycerides = triglycerides;
	}

	private ComponentLevel getHdlCholesterol() {
		return hdlCholesterol;
	}

	private void setHdlCholesterol(ComponentLevel hdlCholesterol) {
		this.hdlCholesterol = hdlCholesterol;
	}

	private ComponentLevel getLdlCholesterol() {
		return ldlCholesterol;
	}

	private void setLdlCholesterol(ComponentLevel ldlCholesterol) {
		this.ldlCholesterol = ldlCholesterol;
	}
}
