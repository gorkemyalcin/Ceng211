package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;

public class Violist extends StringInstrumentMusician{

	static ArrayList<String> playedTemposViolist = new ArrayList<String>(Arrays.asList("Prestissimo", "Vivace", "Allegretto", "Moderato", "Adagietto",
			"Andante", "Larghetto", "Lento", "Grave", "Larghissimo"));

	public Violist() {
		super(playedTemposViolist);
	}

}
