package dataAccessLayer;

import java.io.IOException;
import java.io.PrintWriter;
import businessLayer.Movie;
import businessLayer.Book;
import businessLayer.RentableItem;
import businessLayer.Storage;

public class StorageFileCreator {
	
	public static void StorageFileWriterJSON(Storage storage) throws IOException {
		PrintWriter printWriter = new PrintWriter("StorageDatabase.json");
		printWriter.write("[\n");
		for(RentableItem item:storage.getStorage()) {
			int id = item.getId();
			String name = item.getName();
			String type = item.getType();
			String rentPolicy = item.getRentPolicy();
			if (type.toLowerCase().equals("book")) {
				String author = ((Book) item).getAuthor();
				String publisher = ((Book) item).getPublisher();
				printWriter.write("{\n\"_id\":" + id + ",\n\"type\":\"" + type + "\",\n\"book_name\":\"" + name + "\",\n\"rent_policy\":\"" + rentPolicy
						+ "\",\n\"author\":\"" + author + "\",\n\"publisher\":\"" + publisher + "\"}, \n");
			}
			else if (type.toLowerCase().equals("movie")) {
				String actor = ((Movie) item).getActor();
				String genre = ((Movie) item).getGenre();
				String producer = ((Movie) item).getProducer();
				printWriter.write("{\n\"_id\":" + id + ",\n\"type\":\"" + type + "\",\n\"movie_name\":\"" + name + "\",\n\"rent_policy\":\"" + rentPolicy
						+ "\",\n\"genre\":\"" + genre + "\",\n\"actor\":\"" + actor + "\",\n\"producer\":\"" + producer + "\"}, \n");
			}
		}
		printWriter.write("]");
		printWriter.close();
	}
	
	public static void StorageFileWriterXML(Storage storage) throws IOException{
		PrintWriter printWriter = new PrintWriter("StorageDatabase.xml");
		printWriter.print("<item-info>\n\n");
		for(RentableItem item:storage.getStorage()) {
			int id = item.getId();
			String name = item.getName();
			String type = item.getType();
			String rentPolicy = item.getRentPolicy();
			if (type.toLowerCase().equals("book")) {
				String author = ((Book) item).getAuthor();
				String publisher = ((Book) item).getPublisher();
				printWriter.write("<book>\n" + "<id>" + id + "</id>\n" + "<type>" + type + "</type>\n" + "<book_name>" + name + "</book_name>\n" + 
						"<rent_policy>" + rentPolicy + "</rent_policy> \n" + "<author>" + author + "</author>\n" +
						"<publisher>" + publisher + "</publisher>\n </book>\n\n");
			}
			else if (type.toLowerCase().equals("movie")) {
				String actor = ((Movie) item).getActor();
				String genre = ((Movie) item).getGenre();
				String producer = ((Movie) item).getProducer();
				printWriter.write("<movie>\n" + "<id>" + id + "</id>\n" + "<type>" + type + "</type>\n" + "<book_name>" + name + "</book_name>\n" + 
						"<rent_policy>" + rentPolicy + "</rent_policy> \n" + "<genre>" + genre + "</genre>\n" + "<actor>" + actor + "</actor>\n" + 
						"<producer>" + producer + "</producer>\n</movie>\n\n");
			}
		}
		printWriter.write("</item-info>");
		printWriter.close();
	}
}