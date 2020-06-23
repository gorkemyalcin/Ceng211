package presentationLayer;

import java.io.IOException;

import businessLayer.Concert;


public class ConcertApp {
	public static void main (String[] args) throws IOException {
		Concert concert = new Concert("piece1.txt");
		System.out.println("2");
		/*for (int i = 1; i < 5; i++) {
			concert.play("piece" + i + ".txt");*/
	}
}
