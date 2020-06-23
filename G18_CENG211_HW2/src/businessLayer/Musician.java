package businessLayer;

import java.util.ArrayList;

public class Musician {

	String typeOfMusician;
	String name;
	String playedParts;
	ArrayList<String> playedNotes; 
	ArrayList<String> playedTempos;
	ArrayList<String> playedChangeTempos;
	/*if (this.playedParts.equals("all")) {//string
	if(this.getPlayedTempos().contains(tempo)) {
	for (ArrayList<MusicalScore> part:pieceList) {
		for(MusicalScore musicalScore:part) {
			String stringNote = String.valueOf(musicalScore.getNote());
			if (this.getPlayedNotes().contains(stringNote)) {
				System.out.print(musicalScore.getNote()+ " ");*/
	public Musician() {

	}
	public String toString() {
		return playedParts + ", " + playedNotes + ", " + playedTempos + ", " + playedChangeTempos;
	}
	
	public void playPiece(ArrayList<Integer> choruses, String tempo, String changeTempo, ArrayList<ArrayList<MusicalScore>> pieceList, int partNumber) {
		System.out.println(this.name + " is played:");
		if (this.name.toLowerCase().equals("violinist")) {
			if(!this.getPlayedTempos().contains(tempo)) {
				System.out.print("Part " + partNumber);
				for (MusicalScore musicalScore:pieceList.get(partNumber)) {
							System.out.print(musicalScore.getNote()+ " ");
				}
			}
			System.out.println(changeTempo);
		}
		else if (this.name.toLowerCase().equals("violist")) {
			if (changeTempo.toLowerCase().equals("ritenuto")) {
				System.out.print("Part " + partNumber);
				for (MusicalScore musicalScore:pieceList.get(0)) {
					System.out.print(musicalScore.getNote()+ " ");
				}
			}
			System.out.println(changeTempo);
		}
		
		else if (this.name.toLowerCase().equals("celloist")) {
			System.out.print("Part " + partNumber);
			for (MusicalScore musicalScore:pieceList.get(partNumber)) {
						System.out.print(musicalScore.getNote()+ " ");
				}
			System.out.println(changeTempo);
		}
		
		else if (this.name.toLowerCase().equals("flutist")) {
			if (choruses.contains(partNumber)) {
				System.out.print("Part " + partNumber);
				for (MusicalScore musicalScore:pieceList.get(partNumber)) {
					System.out.print(musicalScore.getNote()+ " ");
				}
			}
		}
		
		else if (this.name.toLowerCase().equals("drummer")) {
			if(this.getPlayedTempos().contains(tempo)) {
				System.out.print("Part " + partNumber);
				for (MusicalScore musicalScore:pieceList.get(partNumber)) {
					if (this.getPlayedNotes().contains(musicalScore.getNote())) {
						System.out.print(musicalScore.getNote()+ " ");
					}
					else {
						System.out.print("X ");
					}
				}
			}
		}
		
		else if (this.name.toLowerCase().equals("bellplayer")) {
			if(this.getPlayedTempos().contains(tempo)) {
				if(this.getPlayedChangeTempos().contains(changeTempo)) {
					System.out.print("Part " + partNumber);
					for (MusicalScore musicalScore:pieceList.get(partNumber)) {
						if (this.getPlayedNotes().contains(musicalScore.getNote())) {
							System.out.print(musicalScore.getNote()+ " ");
						}
						else {
							System.out.print("X ");
						}
					}
				}
			}
		}
	}
	
	public String getTypeOfMusician() {
		return this.typeOfMusician;
	}
	
	private void setTypeOfMusician(String getTypeOfMusician) {
		this.typeOfMusician = typeOfMusician; //TODO preconditions
	}
	
	public String getName() {
		return this.name;
	}
	
	private void setName(String name) {
		this.name = name; //TODO preconditions
	}
	
	public String getPlayedParts() {
		return this.playedParts;
	}
	
	private void setPlayedParts(String playedParts) {
		this.playedParts = playedParts; //TODO preconditions
	}
	
	public ArrayList<String> getPlayedNotes() {
		return this.playedNotes;
	}
	
	private void setPlayedNotes(ArrayList<String> playedNotes) {
		this.playedNotes = playedNotes; //TODO preconditions
	}
	
	public ArrayList<String> getPlayedTempos() {
		return this.playedTempos;
	}
	
	private void setPlayedTempos(ArrayList<String> playedTempos) {
		this.playedTempos = playedTempos; //TODO preconditions
	}
	
	public ArrayList<String> getPlayedChangeTempos() {
		return this.playedChangeTempos;
	}
	
	private void setPlayedChangeTempos(ArrayList<String> playedChangeTempos) {
		this.playedChangeTempos = playedChangeTempos; //TODO preconditions
	}
}

