package businessLayer;

import java.io.IOException;
import java.util.ArrayList;

import dataAccessLayer.PieceFileReader;

public class Concert {
	
	private Maestro maestro;
	private ArrayList<Musician> musicianList;
	private ArrayList<ArrayList<MusicalScore>> pieceList;

	public Concert(String fileName) throws IOException {
		ArrayList<Musician> musicianList = MusicianList.initializeMusicianList();
		this.musicianList = musicianList;
		Maestro maestro = new Maestro();
		this.maestro = maestro;
		ArrayList<ArrayList<MusicalScore>> pieceList = PieceFileReader.pieceInfoReader(fileName);
		this.pieceList = pieceList;
	}
	
	public void play(String fileName) throws IOException {
		ArrayList<ArrayList<MusicalScore>> pieceList = PieceFileReader.pieceInfoReader(fileName);
		ArrayList<Integer> choruses = determineChoruses(pieceList, fileName);
		ArrayList<String> tempos = maestro.findTempos(fileName);
		String changeTempo = maestro.findChangeTempo(fileName);
		int totalPartNumber = pieceList.size();
		System.out.println("Piece is played " + changeTempo); //TODO piece number
		for (int i = 0; i < totalPartNumber; i++) {
			for (int j = 0; j < 6; j++)
				musicianList.get(j).playPiece(choruses, tempos.get(i), changeTempo, pieceList, i);
		}

		
	}
	
	
	
	private ArrayList<Integer> determineChoruses(ArrayList<ArrayList<MusicalScore>> pieceList, String fileName) throws IOException{
		ArrayList<Integer> chorusList = new ArrayList<Integer>();
		pieceList = PieceFileReader.pieceInfoReader(fileName);
		int i = 0;
		int j = 0;
		for (i = 0; i < pieceList.size(); i++) {
			for (j = 0; j < pieceList.size(); j++) {
				while(i != j) {
					if (pieceList.get(i).equals(pieceList.get(j))) {
						if (!chorusList.contains(i)) {
							chorusList.add(i);
						}
					}
				}
			}
		}
		return chorusList;
	}
}
