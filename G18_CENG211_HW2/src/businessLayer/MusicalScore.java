package businessLayer;

public class MusicalScore {
	
	private char note;
	private Double beat;
	
	public MusicalScore(char note, Double beat) {
		this.note = note;
		this.beat = beat;
	}
	
	public MusicalScore() {
		this.note = '\u0000';
		this.beat = 0.0;
	}
	
	public char getNote() {
		return note;
	}
	
	public void setNote(char note) {
		this.note = note;
	}
	
	public Double getBeat() {
		return beat;
	}
	
	public void setBeat(Double beat) {
		this.beat = beat;
	}
	
	public String toString() {
		return note + "," + beat;
	}
}
