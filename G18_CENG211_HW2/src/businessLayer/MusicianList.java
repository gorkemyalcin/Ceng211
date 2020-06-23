package businessLayer;

import java.util.ArrayList;

public class MusicianList {

	private static ArrayList<Musician> musicianList = new ArrayList<Musician>();
	private static BellPlayer bellPlayer = new BellPlayer();
	private static Celloist celloist = new Celloist();
	private static Drummer drummer = new Drummer(); 
	private static Flutist flutist = new Flutist();
	private static Violinist violinist = new Violinist();
	private static Violist violist = new Violist();
	
	public static ArrayList<Musician> initializeMusicianList() {
		musicianList.add(bellPlayer);
		musicianList.add(celloist);
		musicianList.add(drummer);
		musicianList.add(flutist);
		musicianList.add(violinist);
		musicianList.add(violist);
		return musicianList;
	}
}
