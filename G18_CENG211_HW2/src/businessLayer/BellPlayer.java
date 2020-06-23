package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;

public class BellPlayer extends PercussionInstrumentMusician{

	private static ArrayList<String> playedNotesBellPlayer = new ArrayList<String>(Arrays.asList("A","B","F","G"));
	private static ArrayList<String> playedTemposBellPlayer = new ArrayList<String>(Arrays.asList("Grave"));
	private static ArrayList<String> playedChangeTemposBellPlayer = new ArrayList<String>(Arrays.asList("Stretto"));
	
	public BellPlayer() {
		super(playedNotesBellPlayer, playedTemposBellPlayer, playedChangeTemposBellPlayer);
	}
}
