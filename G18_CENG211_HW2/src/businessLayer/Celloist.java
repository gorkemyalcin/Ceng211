package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;

public class Celloist extends StringInstrumentMusician{

	static ArrayList<String> playedTemposCelloist = new ArrayList<String>(Arrays.asList("Prestissimo", "Vivace", "Allegretto", "Moderato", "Adagietto",
			"Andante", "Larghetto", "Lento", "Grave", "Larghissimo"));
	
	public Celloist() {
		super(playedTemposCelloist);
	}
}
