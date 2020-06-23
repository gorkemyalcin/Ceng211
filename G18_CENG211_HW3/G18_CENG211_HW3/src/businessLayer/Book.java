/**
 * 
 */
package businessLayer;

/**
 * @author Gorkem
 *
 */
public class Book extends RentableItem {
	
	private String author;
	private String publisher;
	
	public Book(int id, String name, String type, String rentPolicy, String author, String publisher) {
		super(id, name, type, rentPolicy);
		this.setAuthor(author);
		this.setPublisher(publisher);
	}
	
	public Book() {
		super();
		this.setAuthor(null);
		this.setPublisher(null);
	}
	public String toString() {
		return getId() + ", " + getName() + ", " + getType() + ", " + getRentPolicy() + ", " + author + ", " + publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
