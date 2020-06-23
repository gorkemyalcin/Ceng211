package businessLayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import dataAccessLayer.IzmapReader;
import dataAccessLayer.IzmapWriter;

/**
 * @author Gorkem
 *
 * IztechMap is a class which has the HashMap for the nodes and neighbours of the nodes, and edgeContainer which holds the edges of the graph
 * a node container which holds all the nodes in the graph. We can not create any other object than the one we read from "iztech.izmap" because 
 * while initializing the map, I created the default and only constructor in a way so that only the file's contents will be held in the map.
 * Also I created some private methods for initializing the node coordinates for mapping them on jframe.
 * 
 * The other 4 fields defined below are for finding the shortest path between two nodes.
 */
public class IztechMap {
	
	private HashMap<Node, ArrayList<Node>> iztechMap;
	private List<Edge> edgeContainer;
	private List<Node> nodeContainer;
	private HashSet<Node> checkedNodes;
	private HashSet<Node> uncheckedNodes;
	private HashMap<Node, Node> predecessors;
	private HashMap<Node, Double> distanceMap;
	
	public IztechMap() throws IOException {
		this.nodeContainer = IzmapReader.IzmapNodeReader();
		intializeNodeCoordinates();
		this.setIztechMap(initializeFinalMap());
		this.edgeContainer = initializeDistanceForEdges(IzmapReader.IzmapEdgeReader());
	}
	
	public List<Edge> getEdgeContainer(){
		return edgeContainer;
	}

	public List<Node> getNodeContainer(){
		return nodeContainer;
	}
	
	public HashMap<Node, ArrayList<Node>> getIztechMap() {
		return iztechMap;
	}
	
	private void setIztechMap(HashMap<Node, ArrayList<Node>> iztechMap) {
		this.iztechMap = iztechMap;
	}
	
	/**
	 * @param iztechMap
	 * @throws FileNotFoundException
	 * Updates the files after mutating the iztechMap
	 */
	public void updateFiles(IztechMap iztechMap) throws FileNotFoundException {
		IzmapWriter.IzmapFileWriter(iztechMap);
	}
	
	/**
	 * @param removeId
	 * After removing an item, checks if every number are in numerical order
	 * If element with the id 3 is removed, instead of being 1,2,4 becomes 1,2,3
	 */
	public void updateIds(int removeId) {
		for (Node node:getNodeContainer()) {
			if (node.getNodeId() > removeId) {
				node.setNodeId(node.getNodeId() - 1);
			}
		}
	}
	
	/**
	 * @param node
	 * Adds a node to the map, if its already in the map doesn't add it and prints an error message.
	 */
	public void addNodeToMap(Node node) {
		if (!getNodeContainer().contains(node)) {
			getNodeContainer().add(node);
			iztechMap.put(node, new ArrayList<Node>());
		}
		else {
			System.out.println("Given node exist in the map");
		}
	}
	
	/**
	 * @return the maximum id of all nodes
	 * iterates over the node container and returns the maximum id'd node
	 */
	public int getMaxIdOfNodes() {
		int id = 0;
		for (Node node:getNodeContainer()) {
			if (node.getNodeId() >= id) {
				id = node.getNodeId();
			}
		}
		return id;
	}
	
	/**
	 * @param nodeName name of the node that method will search for
	 * @return Found node if found, null otherwise.
	 * Finds and returns the node with the corresponding nodename, null otherwise.
	 */
	public Node findNodeWithName(String nodeName) {
		for (Node node:getNodeContainer()) {
			if (node.getName().toLowerCase().equals(nodeName.toLowerCase())) {
				return node;
			}
		}
		System.out.println("Node doesnt exist in the map");
		return null;
	}
	

	/**
	 * @param removeNode A node whose edges will get removed
	 * Finds and removes all edges with the given node as one of its vertex
	 */
	public void removeEdgesWithGivenNodeFromMap(Node removeNode) {
		ArrayList<Edge> removeList = new ArrayList<Edge>();
		for (Edge edge:getEdgeContainer()) {
			if (edge.getVertice1().equals(removeNode) || edge.getVertice2().equals(removeNode))
				removeList.add(edge);
		}
		for (Edge edge:removeList) {
			getEdgeContainer().remove(edge);
		}
	}
	
	/**
	 * @param node
	 * Checks if a node is in the map and removes it if found.
	 */
	public void removeNodeFromMap(Node node) {
		if (getNodeContainer().contains(node)) {
			getNodeContainer().remove(node);
		}
		else {
			System.out.println("Given node doesn't exist in the map");
		}
	}
	
	/**
	 * @param edge
	 * Adds an edge to the edge container.
	 * @throws IOException 
	 */
	public void addEdgeToMap(Edge edge) {
		if (!getEdgeContainer().contains(edge)) {
			getEdgeContainer().add(edge);
			setEdgeDistance(edge);
		}
		else {
			System.out.println("Given edge exist in the map");
		}
	}
	
	/**
	 * @param Edge whose distance value will be initialized.
	 * Accordingly to its nodes, defines the edge's distance value.
	 */
	public void setEdgeDistance(Edge edge) {
		if (edge != null) {
			int defaultValue = 6;
			Node node1 = edge.getVertice1();
			Node node2 = edge.getVertice2();
			if (node1 != null && node2 != null) {
				if (getNodeContainer().contains(node1) && getNodeContainer().contains(node2)) {
					if (node1.equals(node2)) {
						edge.setDistance(0);
					}
					List<TypeOfCategory> typeOfCategoryPool = new ArrayList<TypeOfCategory>();
					typeOfCategoryPool.add(node1.getTypeOfCategory());
					typeOfCategoryPool.add(node2.getTypeOfCategory());
					if (typeOfCategoryPool.contains(TypeOfCategory.DEPARTMENT)) {
						if (typeOfCategoryPool.contains(TypeOfCategory.CAFETERIA) && typeOfCategoryPool.size() == 2) {
							edge.setDistance((defaultValue * 2) - 3);
		
						}
						else if (typeOfCategoryPool.contains(TypeOfCategory.ADMINISTRATIVE) && typeOfCategoryPool.size() == 2) {
							edge.setDistance((defaultValue / 2));
						}
						else if (typeOfCategoryPool.contains(TypeOfCategory.BEACH) && typeOfCategoryPool.size() == 2) {
							edge.setDistance((((defaultValue * defaultValue) / 2) + 4));
						}
						else if (typeOfCategoryPool.size() == 2 || typeOfCategoryPool.size() == 1) {
							edge.setDistance(defaultValue);
						}
					}
					else if (typeOfCategoryPool.contains(TypeOfCategory.CAFETERIA)) {
						if (typeOfCategoryPool.contains(TypeOfCategory.WATERFALL) && typeOfCategoryPool.size() == 2) {
							edge.setDistance(defaultValue / 3);
						}
						else if (typeOfCategoryPool.contains(TypeOfCategory.HISTORICAL_RUIN) && typeOfCategoryPool.size() == 2) {
							edge.setDistance(defaultValue * defaultValue);
						}
						else if (typeOfCategoryPool.contains(TypeOfCategory.FACILITY) && typeOfCategoryPool.size() == 2) {
							edge.setDistance(Math.sqrt(defaultValue));
						}
						else if (typeOfCategoryPool.size() == 2 || typeOfCategoryPool.size() == 1) {
							edge.setDistance(defaultValue);
						}
					}
					else if (typeOfCategoryPool.contains(TypeOfCategory.ADMINISTRATIVE)) {
						if (typeOfCategoryPool.size() == 2 || typeOfCategoryPool.size() == 1) {
							edge.setDistance(defaultValue);
						}
					}					
					else if (typeOfCategoryPool.contains(TypeOfCategory.WATERFALL)) {
						if (typeOfCategoryPool.contains(TypeOfCategory.FACILITY) && typeOfCategoryPool.size() == 2) {
							edge.setDistance((defaultValue * 5) / 2);
						}
						else if (typeOfCategoryPool.size() == 2 || typeOfCategoryPool.size() == 1) {
							edge.setDistance(defaultValue);
						}
					}					
					else if (typeOfCategoryPool.contains(TypeOfCategory.HISTORICAL_RUIN)) {
						if (typeOfCategoryPool.size() == 2 || typeOfCategoryPool.size() == 1) {
							edge.setDistance(defaultValue);
						}
					}			
					else if (typeOfCategoryPool.contains(TypeOfCategory.BEACH)) {
						if (typeOfCategoryPool.size() == 2 || typeOfCategoryPool.size() == 1) {
							edge.setDistance(defaultValue);
						}
					}
					else {
						edge.setDistance(defaultValue);
					}
				}
			}
		}
	}

	/**
	 * @param edgeContainer the ArrayList which holds the edge values without their distance initialized.
	 * @return the ArrayList of edges which are finalized.
	 * @throws IOException
	 * Accordingly to its nodes, defines the edge's distance value for every edge in the map.
	 */
	private List<Edge> initializeDistanceForEdges(List<Edge> edgeContainer) throws IOException {
		for (Edge edge:edgeContainer) {
			setEdgeDistance(edge);
		}
		return edgeContainer;
	}
	
	/**
	 * @param iztechMap the HashMap for every node as key, node's neighbour as value which are empty at first
	 * @return the updated HashMap
	 * @throws IOException
	 */
	private HashMap<Node, ArrayList<Node>> initializeNeighbours(HashMap<Node, ArrayList<Node>> iztechMap) throws IOException{
		List<Node> nodeContainer = IzmapReader.IzmapNodeReader();
		List<Edge> edgeContainer = IzmapReader.IzmapEdgeReader();
		initializeDistanceForEdges(edgeContainer);
		for (Node node:nodeContainer) {
			for (Edge edge:edgeContainer) {
				if (node.getNodeId() == edge.getVertice1().getNodeId()) {//to add the second vertex of the edge
					Node neighbourNode = edge.getVertice2();
					if (!iztechMap.get(node).contains(neighbourNode)) {
						iztechMap.get(node).add(neighbourNode);
					}
				}
				if (node.getNodeId() == edge.getVertice2().getNodeId()) {//to add the first vertex of the edge
					Node neighbourNode = edge.getVertice1();
					if (!iztechMap.get(node).contains(neighbourNode)) {
						iztechMap.get(node).add(neighbourNode);
					}
				}
			}
		}
		return iztechMap;
	}
	
	/**
	 * @return the final hashmap where it has all the keys as the nodes, values as the arraylist of nodes neighbours.
	 * @throws IOException
	 */
	private HashMap<Node, ArrayList<Node>> initializeFinalMap() throws IOException {
		HashMap<Node, ArrayList<Node>> iztechMap = initializeNeighbours(initalizeDefaultMap());
		return iztechMap;
	}
	
	/**
	 * @param id Node's id
	 * @return Node with given id if found, null otherwise.
	 * @throws IOException
	 */
	public static Node findNode(int id) throws IOException {//TODO static public, used in a different class, only for searching purposes
		Node returnNode = null;
		List<Node> nodeContainer = IzmapReader.IzmapNodeReader();
		for (Node node:nodeContainer) {
			if (id == node.getNodeId()) {
				returnNode = node;
			}
		}
		return returnNode;
	}

	/**
	 * @return the HashMap where it has only the node's as keys and empty ArrayList as values
	 * @throws IOException
	 */
	private HashMap<Node, ArrayList<Node>> initalizeDefaultMap() throws IOException {
		HashMap<Node, ArrayList<Node>> iztechMap = new HashMap<Node, ArrayList<Node>>();
		List<Node> nodeContainer = IzmapReader.IzmapNodeReader();
		for (Node node:nodeContainer) {
			ArrayList<Node> list = new ArrayList<Node>();
			iztechMap.put(node, list);
		}
		return iztechMap;
	}
	
	
	/**
	 * Accordingly to the amount of nodes, initializes the node's coordinates so that they will be equally away from each other.
	 */
	private void intializeNodeCoordinates(){
		Point point = new Point(250,500);//r is 250
		getNodeContainer().get(0).setPoint(point);
		for (int i = 1; i < getNodeContainer().size(); i++) {
			point = new Point(250 + (250 * Math.sin(Math.toRadians(360 * i / getNodeContainer().size()))),
					250 + (250 * Math.cos(Math.toRadians(360 * i / getNodeContainer().size()))));
			nodeContainer.get(i).setPoint(point);
		}
	}
	
	//start of finding the shortest path.
	
	/**
	 * @param sourceNode the node which will be the source node
	 * @param givenDistance the distance which will be the checked distance from the paths distances.
	 * Finds the shortest paths from sourceNode to every other node, finds it path distance,
	 * checks if they are less than the given distance, if it is prints the destination.
	 */
	public void getListOfPossibleReachableLocationWithAGivenDistance(Node sourceNode, double givenDistance) {
		for (Node candidateDestinationNode:getNodeContainer()) {
			LinkedList<Node> candidatePath = getShortestPathWithoutPrinting(sourceNode, candidateDestinationNode);
			double candidatePathDistance = getShortestPathsDistance(candidatePath);
			if (candidatePathDistance <= givenDistance && candidatePathDistance != 0.0) {
				System.out.println(candidateDestinationNode.toString());
			}
		}
	}
	
	/**
	 * @param shortestPath a LinkedList which is the shortest path between 2 nodes
	 * @return the path's double distance value
	 * Finds and returns the LinkedList shortest paths total distance value.
	 */
	public Double getShortestPathsDistance(LinkedList<Node> shortestPath) {
		Double distance = 0.0;
		LinkedList<Node> shortestPathCopy = new LinkedList<Node>(shortestPath);
		ListIterator<Node> listIterator = shortestPathCopy.listIterator();
		if (listIterator.hasNext()) {
			Node node1 = shortestPathCopy.removeFirst();
			while (listIterator.hasNext()) {
				Node node2 = shortestPathCopy.removeFirst();
				distance = distance + getDistanceBetweenTwoNodes(node1, node2);
				if (listIterator.hasNext()) {
					node1 = shortestPathCopy.removeFirst();
					distance = distance + getDistanceBetweenTwoNodes(node1, node2);
				}
			}
			return distance;
		}
		else {
			return distance;
		}
	}
	
	/**
	 * @param source Node which is the source of the shortest path
	 * @param destination Node which is the destination of the shortest path
	 * @return the LinkedList which holds the nodes of the shortest path from source to destination
	 */
	private LinkedList<Node> getShortestPathWithoutPrinting(Node source, Node destination){
		findShortestDistance(source);
		LinkedList<Node> shortestPath = new LinkedList<Node>();
		Node step = destination;
		if (predecessors.get(step) == null) {
			if (!source.equals(destination)) {
			}
		}
		else {
			shortestPath.add(destination);
			while (predecessors.get(step) != null) {//while the destination has a predecessor (which is a step node on the way of going back)
				step = predecessors.get(step);
				shortestPath.add(step);
			}
			//reverse atm.
		}
		return shortestPath;
	}
	
	/**
	 * @param source Node which is the source of the shortest path
	 * @param destination Node which is the destination of the shortest path
	 * @return the LinkedList which holds the nodes of the shortest path from source to destination
	 * This method informs the user if there are no path found between source and destination
	 */
	public LinkedList<Node> getShortestPath(Node source, Node destination){
		findShortestDistance(source);
		LinkedList<Node> shortestPath = new LinkedList<Node>();
		Node step = destination;
		if (predecessors.get(step) == null) {
			if (!source.equals(destination)) {
				System.out.println("No path can be found for " + source.getName() + " to " + destination.getName());
			}
		}
		else {
			shortestPath.add(destination);
			while (predecessors.get(step) != null) {//while the destination has a predecessor (which is a step node on the way of going back)
				step = predecessors.get(step);
				shortestPath.add(step);
			}
			//reverse atm.
		}
		return shortestPath;
	}
	
	/**
	 * @param node
	 * @return the HashMap distanceMaps value for node key if exists,
	 *  else returns the MAX_VALUE because it will always be preceded by other distance values
	 */
	private double getShortestDistance(Node node) {
        Double distance = distanceMap.get(node);
        if ((distance == null) || (distance < 0)) { // check Null
            return Double.MAX_VALUE;
        } else {
            return distance;
        }
    }
	
	/**
	 * @param uncheckedNodes the Set which holds the values for all unchecked but currently ready to be checked nodes
	 * @return the node which will hold the minimum value for all the traverses it did around the edges(all the distance it moved already).
	 * We want the minimum distance since there will be a better chance at acquiring the shortest path with minimum distance traversed node.
	 */
	private Node getNodeOfTheMinimumDistancePassedFromUnchecked(Set<Node> uncheckedNodes) {
		Node minimumNode = null;
		for (Node node:uncheckedNodes) {
			if (minimumNode == null) {
				minimumNode = node;
			}
			else {
				if(getShortestDistance(node) < getShortestDistance(minimumNode)) {
					minimumNode = node;
				}
			}
		}
		return minimumNode;
	}
	
	/**
	 * @param node which we will check its neighbours
	 * Finds the nodes neighbours, for all the neighbours, 
	 * updates the distance map's NEIGHBOURS value with the parameter node's distance passed, and the distance between neighbours and the parameter node.
	 * Adds the neighbours to the uncheckedNodes to be checked.
	 * Adds the neighbour as key and node as value because neighbour now has a predecessor as the parameter node since we are done with the parameter node.
	 */
	private void findMinimalDistanceOfTheNodesNeighbours(Node node) {
		List<Node> neighboursOfNode = getNeighbours(node);
		for (Node candidateNode:neighboursOfNode) {
			if (getShortestDistance(candidateNode) > (getShortestDistance(node) + getDistance(node, candidateNode))) {
				distanceMap.put(candidateNode, getShortestDistance(node) + getDistance(node, candidateNode));
				predecessors.put(candidateNode, node);
				uncheckedNodes.add(candidateNode);
			}
		}
	}
	
	/**
	 * @param node1
	 * @param node2
	 * @return if the edge exists, finds the distance between two nodes and returns it, returns 0.0 otherwise
	 */
	private Double getDistanceBetweenTwoNodes(Node node1, Node node2) {
		Double distance = 0.0;
		for (Edge edge:getEdgeContainer()) {
			if (edge.getVertice1().equals(node1) && edge.getVertice2().equals(node2)) {
				distance = distance + edge.getDistance();
				return distance;
			}
			else if (edge.getVertice1().equals(node2) && edge.getVertice2().equals(node1)) {
				distance = distance + edge.getDistance();
				return distance;
			}
		}
		return distance;
	}
	
	/**
	 * MIGHT BE UNNECESSARY
	 */
	private double getDistance(Node node, Node candidateNode) {
		for (Edge edge:getEdgeContainer()) {
			if (edge.getVertice1().equals(node) && edge.getVertice2().equals(candidateNode)) {
				return edge.getDistance();
			}
		}
		System.out.println("error in getdistance");
		return -1.0;
	}

	/**
	 * @param node
	 * @return ArrayList of nodes which are neighbours of the parameter node.
	 */
	private ArrayList<Node> getNeighbours(Node node) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		for (Edge edge:getEdgeContainer()) {
			if (edge.getVertice1().equals(node) && !neighbours.contains(edge.getVertice2()) && !isChecked(edge.getVertice2())) {
					neighbours.add(edge.getVertice2());
			}
		}
		return neighbours;
	}

	/**
	 * @param node
	 * @return true if the parameter node is checked for distances already, false otherwise.
	 */
	private boolean isChecked(Node node) {
		if (checkedNodes.contains(node))
			return true;
		else {
			return false;
		}
	}

	/**
	 * @param nodeFrom
	 * Starts from the source node, checks every neighbour and adds the neighbours to the unchecked list, then checks the neighbours' neighbours
	 * if the neighbours' neighbours are already checked, doesn't add them to the uncheckled list.
	 * Finishes when there is no more nodes in the unchecked list
	 */
	private void findShortestDistance(Node nodeFrom) {
		 checkedNodes = new HashSet<Node>();
		 uncheckedNodes = new HashSet<Node>();
	     distanceMap = new HashMap<Node, Double>();
	     predecessors = new HashMap<Node, Node>();
	     distanceMap.put(nodeFrom, 0.0);
	     uncheckedNodes.add(nodeFrom);
	     while (uncheckedNodes.size() > 0) {
	    	 Node node = getNodeOfTheMinimumDistancePassedFromUnchecked(uncheckedNodes);
	    	 checkedNodes.add(node);
	    	 uncheckedNodes.remove(node);
	    	 findMinimalDistanceOfTheNodesNeighbours(node);
	     }
	}
	//shortest path finder ends here
}
