package businessLayer;

public class Edge {
	
	private Node vertice1;
	private Node vertice2;
	private double distance;
	
	public Edge(Node vertice1, Node vertice2, double distance) {
		this.setVertice1(vertice1);
		this.setVertice2(vertice2);
		this.setDistance(distance);
	}

	public String toString() {
		return "--Vertice 1: " + vertice1 + " Vertice 2: " + vertice2 + " Distance inbetween them: " + distance + "--"  ;
	}
	
	public Node getVertice1() {
		return vertice1;
	}

	public void setVertice1(Node vertice1) {
		this.vertice1 = vertice1;
	}

	public Node getVertice2() {
		return vertice2;
	}

	public void setVertice2(Node vertice2) {
		this.vertice2 = vertice2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
