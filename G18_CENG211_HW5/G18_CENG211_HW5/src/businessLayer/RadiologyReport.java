package businessLayer;

/**
 * @author Gorkem
 * A report class which is responsible for holding the date of the radiology test results. Since I had no way of collecting these values,
 *  I initialized them randomly.
 */
public class RadiologyReport {

	private RadiologyComponentResult lungs;
	private RadiologyComponentResult cardiac;
	private RadiologyComponentResult liver;
	private RadiologyComponentResult biliary;
	private RadiologyComponentResult spleen;
	private RadiologyComponentResult pancreas;
	private RadiologyComponentResult kidneys;
	private RadiologyComponentResult lymphNodes;
	private RadiologyComponentResult bladder;
	private RadiologyComponentResult bones;
	
	public RadiologyReport() {
		this.setBiliary(RadiologyComponentResult.getRandomComponentLevel());
		this.setBladder(RadiologyComponentResult.getRandomComponentLevel());
		this.setBones(RadiologyComponentResult.getRandomComponentLevel());
		this.setCardiac(RadiologyComponentResult.getRandomComponentLevel());
		this.setKidneys(RadiologyComponentResult.getRandomComponentLevel());
		this.setLiver(RadiologyComponentResult.getRandomComponentLevel());
		this.setLungs(RadiologyComponentResult.getRandomComponentLevel());
		this.setLymphNodes(RadiologyComponentResult.getRandomComponentLevel());
		this.setPancreas(RadiologyComponentResult.getRandomComponentLevel());
		this.setSpleen(RadiologyComponentResult.getRandomComponentLevel());
	}
	
	public String toString() {
		return "\nBiliary: " + getBiliary() + "\nBladder: " + getBladder() + "\nBones: " + getBones() + "\nCardiac: " + getCardiac() +
				"\nKidneys: " + getKidneys() + "\nLiver: " + getLiver() + "\nLungs: " + getLungs() + "\nLymph Nodes: " + getLymphNodes() +
				"\nPancreas: " + getPancreas() + "\nSpleen: " + getSpleen();
	}

	private RadiologyComponentResult getLungs() {
		return lungs;
	}

	private void setLungs(RadiologyComponentResult lungs) {
		this.lungs = lungs;
	}

	private RadiologyComponentResult getCardiac() {
		return cardiac;
	}

	private void setCardiac(RadiologyComponentResult cardiac) {
		this.cardiac = cardiac;
	}

	private RadiologyComponentResult getLiver() {
		return liver;
	}

	private void setLiver(RadiologyComponentResult liver) {
		this.liver = liver;
	}

	private RadiologyComponentResult getBiliary() {
		return biliary;
	}

	private void setBiliary(RadiologyComponentResult biliary) {
		this.biliary = biliary;
	}

	private RadiologyComponentResult getSpleen() {
		return spleen;
	}

	private void setSpleen(RadiologyComponentResult spleen) {
		this.spleen = spleen;
	}

	private RadiologyComponentResult getPancreas() {
		return pancreas;
	}

	private void setPancreas(RadiologyComponentResult pancreas) {
		this.pancreas = pancreas;
	}

	private RadiologyComponentResult getKidneys() {
		return kidneys;
	}

	private void setKidneys(RadiologyComponentResult kidneys) {
		this.kidneys = kidneys;
	}

	private RadiologyComponentResult getLymphNodes() {
		return lymphNodes;
	}

	private void setLymphNodes(RadiologyComponentResult lymphNodes) {
		this.lymphNodes = lymphNodes;
	}

	private RadiologyComponentResult getBladder() {
		return bladder;
	}

	private void setBladder(RadiologyComponentResult bladder) {
		this.bladder = bladder;
	}

	private RadiologyComponentResult getBones() {
		return bones;
	}

	private void setBones(RadiologyComponentResult bones) {
		this.bones = bones;
	}
}
