package businessLayer;

public class Movie extends RentableItem {
	
	private String actor;
	private String producer;
	private String genre;
	
	public Movie(int id, String name, String type, String rentPolicy, String actor, String genre, String producer) {
		super(id, name, type, rentPolicy);
		this.setActor(actor);
		this.setProducer(producer);
		this.setGenre(genre);
	}
	
	public Movie() {
		super();
		this.setActor(null);
		this.setProducer(null);
		this.setGenre(null);
	}
	
	public String toString() {
		return getId() + ", " + getName() + ", " + getType() + ", " + getRentPolicy() + ", " + getActor() + ", " + getGenre() + "," + getProducer();
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
