package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;

public class WoodwindInstrumentMusician extends Musician {
	
	static ArrayList<String> playedPartsWoodwind = new ArrayList<String>(Arrays.asList("chorus"));
	static ArrayList<String> playedNotesWoodwind = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H"));
	static ArrayList<String> playedTemposWoodwind = new ArrayList<String>(Arrays.asList("Prestissimo", "Vivace", "Allegretto", 
			"Moderato", "Adagietto", "Andante", "Larghetto", "Lento", "Grave", "Larghissimo"));
	static ArrayList<String> playedChangeTemposWoodwind = new ArrayList<String>(Arrays.asList("Lentando", "Ritenuto", "Stretto", "Accelerando"));
	
	public WoodwindInstrumentMusician() {
		super();
		this.playedParts = "chorus";
		this.playedNotes = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H"));
		this.playedTempos = new ArrayList<String>(Arrays.asList("Prestissimo", "Vivace", "Allegretto", 
				"Moderato", "Adagietto", "Andante", "Larghetto", "Lento", "Grave", "Larghissimo"));
		this.playedChangeTempos = new ArrayList<String>(Arrays.asList("Lentando", "Ritenuto", "Stretto", "Accelerando"));
	}
	
}
