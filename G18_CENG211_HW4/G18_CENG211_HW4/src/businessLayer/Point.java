package businessLayer;

public class Point {

	
	private double x;
	private double y;
	
	public Point(double x, double y) {
		this.setX(x);
		this.setY(y);
	}
	
	public String toString() {
		return "X: " + getX() + " Y: " + getY(); 
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
}
