package businessLayer;


import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

/**
 * @author Gorkem
 * MapPlotter is a class for mapping the nodes and edges onto a jframe object. It uses IztechMap classes containers, finds the coordinates to draw,
 * then draws circles for nodes and lines for edges.
 * This wasn't asked for in the homework but I was studying the topic and tried it on the homework.
 *
 */
public class MapPlotter extends JFrame{
	
	private static final long serialVersionUID = 5663231331614197201L;
	private List<Node> nodeContainer;
	private List<Edge> edgeContainer;
	
	public MapPlotter() throws IOException {
		IztechMap iztechMap = new IztechMap();
		this.edgeContainer = iztechMap.getEdgeContainer();
		this.nodeContainer = iztechMap.getNodeContainer();
		setEdgeCoordinates();
	}
	
	/* 
	 * Paint method finds the nodes and edges coordinates and draws circles for nodes lines for edges at the coordinates,
	 *	there are some additions at the end because of alignment reasons.
	 * 
	 */
	public void paint(Graphics g) {
		for (Node node:getNodeContainer()) {
			g.setColor(Color.blue);
			g.fillOval((int)Math.round(node.getPoint().getX())+300,(int)Math.round(node.getPoint().getY())+150,30,30);
			g.drawString(node.getNodeId() + ", "+ node.getName(),(int)Math.round(node.getPoint().getX())+300 , (int)Math.round(node.getPoint().getY())+150);
		}
		for (Edge edge:getEdgeContainer()) {
			g.setColor(Color.white);
			g.drawLine((int)Math.round(edge.getVertice1().getPoint().getX())+320, (int)Math.round(edge.getVertice1().getPoint().getY())+160, 
					(int)Math.round(edge.getVertice2().getPoint().getX())+320, (int)Math.round(edge.getVertice2().getPoint().getY())+160);
		}
	}

	/**
	 * Finds the edges vertices and assigns the edges vertices coordinates accordingly
	 */
	private void setEdgeCoordinates() {
		for (Edge edge:getEdgeContainer()) {
			for (Node node:getNodeContainer()) {
				if (edge.getVertice1().equals(node)) {
					edge.getVertice1().setPoint(node.getPoint());
					}
				else if (edge.getVertice2().equals(node)) {
					edge.getVertice2().setPoint(node.getPoint());
				}
			}
		}
	}
	
	/**
	 * @throws IOException
	 * starting method for MapPlotter
	 */
	public void plot() throws IOException {
		MapPlotter mapPlotter = new MapPlotter();
		mapPlotter.createWindow();
	}
	
	/**
	 * Initializes the map frame.
	 */
	public void createWindow() {
		setTitle("Iztech Map");
		setSize(1200,800);
		setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
        getContentPane().setBackground(Color.white);
	}

	public List<Node> getNodeContainer() {
		return nodeContainer;
	}

	public void setNodeContainer(List<Node> nodeContainer) {
		this.nodeContainer = nodeContainer;
	}

	public List<Edge> getEdgeContainer() {
		return edgeContainer;
	}

	public void setEdgeContainer(List<Edge> edgeContainer) {
		this.edgeContainer = edgeContainer;
	}
	
}
