package businessLayer;

import java.io.IOException;
import java.util.ArrayList;

import dataAccessLayer.PieceFileReader;

public class Maestro {

	public Maestro() {
		
	}
	
	public ArrayList<ArrayList<MusicalScore>> getPieceList(String fileName) throws IOException {
		ArrayList<ArrayList<MusicalScore>> pieceList = PieceFileReader.pieceInfoReader(fileName);
		return pieceList;
	}
	
	private ArrayList<Double> findBeats(String fileName) throws IOException {
		ArrayList<ArrayList<MusicalScore>> pieceList = getPieceList(fileName);
		ArrayList<Double> beatList = new ArrayList<Double>();
		for (ArrayList<MusicalScore> part:pieceList) {
			Double beatCount = 0.0;
			for (MusicalScore score: part) {
				beatCount = score.getBeat() + beatCount;
			}
			beatList.add(beatCount);
		}
		return beatList;
	}
	
	public ArrayList<String> findTempos(String fileName) throws IOException{
		ArrayList<String> tempoList = new ArrayList<String>();
		ArrayList<Double> beatList = findBeats(fileName);
		for (Double beat:beatList) {
			if (beat < 8) {
				tempoList.add("Prestissimo");
			}
			else if (beat < 16) {
				tempoList.add("Vivace");
			}
			else if (beat < 18) {
				tempoList.add("Allegretto");
			}
			else if (beat < 22) {
				tempoList.add("Moderato");
			}
			else if (beat < 23) {
				tempoList.add("Adagietto");
			}
			else if (beat < 24) {
				tempoList.add("Andante");
			}
			else if (beat < 27) {
				tempoList.add("Larghetto");
			}
			else if (beat < 29) {
				tempoList.add("Lento");
			}
			else if (beat < 33) {
				tempoList.add("Grave");
			}
			else if (beat < 37) {
				tempoList.add("Larghissimo");
			}
		}
		return tempoList;
	}
	
	public String findChangeTempo(String fileName) throws IOException{
		String resultChangeTempo = "";
		Double changeTempo = 0.0;
		ArrayList<Double> beatList = findBeats(fileName);
		for (Double beat:beatList) {
			changeTempo = changeTempo + beat;
		}
		if (changeTempo < 83) {
			resultChangeTempo = "Lentando";
		}
		else if (changeTempo < 125 ) {
			resultChangeTempo = "Ritenuto";
		}
		else if (changeTempo < 132) {
			resultChangeTempo = "Stretto";
		}
		else if (changeTempo < 152) {
			resultChangeTempo = "Accelerando";
		}
		return resultChangeTempo;
	}
	
	
}
