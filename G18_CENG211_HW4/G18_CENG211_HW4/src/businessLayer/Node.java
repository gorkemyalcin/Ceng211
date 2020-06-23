package businessLayer;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Gorkem
 *
 */

public abstract class Node implements Comparable<Node>{

	private int nodeId;
	private TypeOfCategory typeOfCategory;
	private String name;
	private Point point;

	public Node(int nodeId, TypeOfCategory typeOfCategory, String name) {
		this.setNodeId(nodeId);
		this.setTypeOfCategory(typeOfCategory);
		this.setName(name);
		this.point = new Point(0,0);
	}
	
	public Node() {
		this.setNodeId(-1);
		this.setTypeOfCategory(TypeOfCategory.DEFAULT);
		this.setName("not initiailzed");
	}
	
    public void paint(Graphics g){
        g.setColor(Color.blue);
        g.drawOval((int)Math.round(getPoint().getX()),(int)Math.round(getPoint().getY()),100,100);
        g.fillOval((int)Math.round(getPoint().getX()),(int)Math.round(getPoint().getY()),100,100);
    }
	
	public String toString() {
		return "NodeID: " + getNodeId() + ", Name: " + getName();
	}

	public int compareTo(Node otherNode) {
		return getName().compareTo(otherNode.getName());
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	
    public abstract boolean equals(Object otherObject);
    
    public abstract int hashCode();

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public TypeOfCategory getTypeOfCategory() {
		return typeOfCategory;
	}

	public void setTypeOfCategory(TypeOfCategory typeOfCategory) {
		this.typeOfCategory = typeOfCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
