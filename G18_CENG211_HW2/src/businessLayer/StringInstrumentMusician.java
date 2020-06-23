package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;

public class StringInstrumentMusician extends Musician {
	
	public StringInstrumentMusician(ArrayList<String> playedTempos) {
		super();
		this.playedParts = "all";
		this.playedNotes = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H"));
		this.playedTempos = playedTempos;
		this.playedChangeTempos = new ArrayList<String>(Arrays.asList("Lentando", "Ritenuto", "Stretto", "Accelerando"));
	}
	
}
