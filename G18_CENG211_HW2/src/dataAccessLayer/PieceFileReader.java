package dataAccessLayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import businessLayer.MusicalScore;


public class PieceFileReader {

	
	public static ArrayList<ArrayList<MusicalScore>> pieceInfoReader(String fileName) throws IOException {
		ArrayList<MusicalScore> partList = new ArrayList<>();
		ArrayList<ArrayList<MusicalScore>> pieceList = new ArrayList<>();
		String score = "";
		MusicalScore musicalScore = new MusicalScore();
		FileReader reader = new FileReader(fileName);
		BufferedReader inputStream = new BufferedReader(reader);
		StringTokenizer scoreFinder;
		String part = inputStream.readLine();
		while (part != null) {
			scoreFinder = new StringTokenizer(part, " ");
			while (scoreFinder.hasMoreTokens()) {
				score = scoreFinder.nextToken();
				musicalScore.setNote(score.charAt(0));
				musicalScore.setBeat(Double.parseDouble(score.substring(1)));
				}
				partList.add(musicalScore);
			}
			pieceList.add(partList);
			part = inputStream.readLine();
		inputStream.close();
		return pieceList;
	}
}
