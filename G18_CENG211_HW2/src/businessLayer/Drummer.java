package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;

public class Drummer extends PercussionInstrumentMusician{

	private static ArrayList<String> playedNotesDrummer = new ArrayList<String>(Arrays.asList("C","D","E"));
	private static ArrayList<String> playedTemposDrummer = new ArrayList<String>(Arrays.asList("Prestissimo", "Vivace", "Allegretto"));
	private static ArrayList<String> playedChangeTemposDrummer = new ArrayList<String>(Arrays.asList("Lentando", "Ritenuto", "Stretto", "Accelerando"));
	
	public Drummer() {
		super(playedNotesDrummer, playedTemposDrummer, playedChangeTemposDrummer);
	}
	
	public ArrayList<String> getPlayedNotes() {
		return this.playedNotes;
	}
	
	public ArrayList<String> getPlayedTempos() {
		return this.playedTempos;
	}
	
	public ArrayList<String> getPlayedChangeTempos() {
		return this.playedChangeTempos;
	}
}
