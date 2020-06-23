package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;

public class Violinist extends StringInstrumentMusician{
	
	static ArrayList<String> playedTemposViolinist = new ArrayList<String>(Arrays.asList("Vivace", "Allegretto", "Moderato", "Adagietto",
			"Andante", "Larghetto", "Lento", "Grave", "Larghissimo"));

	public Violinist() {
		super(playedTemposViolinist);
	}

}
