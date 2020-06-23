package businessLayer;

import java.util.ArrayList;
import java.util.Arrays;

public class PercussionInstrumentMusician extends Musician{
	
	static String playedPartsPercussion = "last2";
	
	public PercussionInstrumentMusician(ArrayList<String> playedNotes, ArrayList<String> playedTempos, ArrayList<String> playedChangeTempos) {
		super();
		this.playedParts = playedPartsPercussion;
		this.playedNotes = playedNotes;
		this.playedTempos = playedTempos;
		this.playedChangeTempos = playedChangeTempos;
	}
}
