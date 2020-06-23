package presentationLayer;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

import businessLayer.BuildingNode;
import businessLayer.Edge;
import businessLayer.IztechMap;
import businessLayer.LandscapeNode;
import businessLayer.MapPlotter;
import businessLayer.Node;
import businessLayer.TypeOfCategory;
 
/**
 * @author Gorkem
 *
 */
public class ConsoleIO {
	public static void main(String[] args) throws IOException {
		simulator();
	}
	
	private static void printMenu() {
		System.out.println("\n\n********** IztechMap **********\n");
		System.out.println("1. Find the shortest path between 2 locations");
		System.out.println("2. Add a location");
		System.out.println("3. Remove a location");
		System.out.println("4. See reachable locations from a source location and a distance.");
		System.out.println("5. See the neighbours of a given location");
		System.out.println("6. Add an edge between nodes");
		System.out.println("7. Check if a node is in the map");
		System.out.println("8. See a map with the nodes and connecting edges, \n\t needs an alt-tab to disable background.");
		System.out.println("X. Exit");
	}
	
	@SuppressWarnings("resource")
	public static void simulator() throws IOException {
		while(true) {
			IztechMap iztechMap = new IztechMap();
			printMenu();
			Scanner keyboard = new Scanner(System.in);
			String choice = keyboard.nextLine();
			if (choice.equals("1")) {
				System.out.println("Please enter the source locations name.");
				String sourceNodeName = keyboard.nextLine().toLowerCase();
				Node sourceNode = iztechMap.findNodeWithName(sourceNodeName);
				if (sourceNode != null) {
					System.out.println("Please enter the destination locations name.");
					String destinationNodeName = keyboard.nextLine().toLowerCase();
					Node destinationNode = iztechMap.findNodeWithName(destinationNodeName);
					if (destinationNode != null) {
						LinkedList<Node> shortestPath = iztechMap.getShortestPath(sourceNode, destinationNode);
						if (shortestPath.size() != 0) {
							System.out.println("Shortest path between " + destinationNode.getName() + " and " + sourceNode.getName() + " is: ");
							System.out.println(iztechMap.getShortestPath(sourceNode, destinationNode).toString());
							System.out.println("Distance between " + destinationNode.getName() + " and " + sourceNode.getName() + " is: ");
							System.out.println(iztechMap.getShortestPathsDistance(shortestPath));
						}
					}
					else {
						System.out.println("Destination node doesn't exist in the map");
					}
				}
				else {
					System.out.println("Source node does not exist in the map");
				}
			}
			else if (choice.equals("2")) {//TODO do the selections with integers.
				System.out.println("Enter the name of your new node:");
				String newName = keyboard.nextLine();
				newName = newName.substring(0,1).toUpperCase() + newName.substring(1);//Capital first letter
				int newId = iztechMap.getMaxIdOfNodes() + 1;//new node id
				System.out.println("Enter the category of your new node (1 for Building, 2 for Landscape):");
				String newCategoryChoice = keyboard.nextLine();
				if (newCategoryChoice.equals("1")) {
					System.out.println("Enter the type of category of your new node\n"
							+ "1 for Department, 2 for Facility, 3 for Administrative, 4 for Cafeteria");
					String newTypeOfCategoryChoice = keyboard.nextLine();
					if (newTypeOfCategoryChoice.equals("1")) {
						TypeOfCategory newTypeOfCategory = TypeOfCategory.DEPARTMENT;
						Node newNode = new BuildingNode(newId, newTypeOfCategory, newName);
						iztechMap.addNodeToMap(newNode);
						iztechMap.updateFiles(iztechMap);
						System.out.println("Succesfully added the node");
					}
					else if (newTypeOfCategoryChoice.equals("2")) {
						TypeOfCategory newTypeOfCategory = TypeOfCategory.FACILITY;
						Node newNode = new BuildingNode(newId, newTypeOfCategory, newName);
						iztechMap.addNodeToMap(newNode);
						iztechMap.updateFiles(iztechMap);
						System.out.println("Succesfully added the node");
					}
					else if (newTypeOfCategoryChoice.equals("3")) {
						TypeOfCategory newTypeOfCategory = TypeOfCategory.ADMINISTRATIVE;
						Node newNode = new BuildingNode(newId, newTypeOfCategory, newName);
						iztechMap.addNodeToMap(newNode);
						iztechMap.updateFiles(iztechMap);
						System.out.println("Succesfully added the node");
					}
					else if (newTypeOfCategoryChoice.equals("4")) {
						TypeOfCategory newTypeOfCategory = TypeOfCategory.CAFETERIA;
						Node newNode = new BuildingNode(newId, newTypeOfCategory, newName);
						iztechMap.addNodeToMap(newNode);
						iztechMap.updateFiles(iztechMap);
						System.out.println("Succesfully added the node");
					}
					else {
						System.out.println("Type of category cannot be anything else than Department, Facility, Administrative or Cafeteria. In our case 1,2,3,4.");
					}
				}
				else if (newCategoryChoice.equals("2")){
					System.out.println("Enter the type of category of your new node\n"
							+ "1 for Waterfall, 2 for Beach, 3 for Historical Ruin");
					String newTypeOfCategoryChoice = keyboard.nextLine();
					if (newTypeOfCategoryChoice.equals("1")) {
						Node newNode = new LandscapeNode(newId, TypeOfCategory.WATERFALL, newName);
						iztechMap.addNodeToMap(newNode);
						iztechMap.updateFiles(iztechMap);
						System.out.println("Succesfully added the node");
					}
					else if (newTypeOfCategoryChoice.equals("2")) {
						Node newNode = new LandscapeNode(newId, TypeOfCategory.BEACH, newName);
						iztechMap.addNodeToMap(newNode);
						iztechMap.updateFiles(iztechMap);
						System.out.println("Succesfully added the node");
					}
					else if (newTypeOfCategoryChoice.equals("3")) {
						Node newNode = new LandscapeNode(newId, TypeOfCategory.HISTORICAL_RUIN, newName);
						iztechMap.addNodeToMap(newNode);
						iztechMap.updateFiles(iztechMap);
						System.out.println("Succesfully added the node");
					}
					else {
						System.out.println("Type of category of the node cannot be anything else than Historical Ruin, Waterfall or Beach, in our case 1,2,3.");
					}
				}
				else {
					System.out.println("Category of the node cannot be anything else than Building or Landscape, in our case 1 or 2.");
				}
			}
			else if (choice.equals("3")) {
				System.out.println("Enter the name of the node that you want to delete:");
				String removeName = keyboard.nextLine();
				Node removeNode = iztechMap.findNodeWithName(removeName);
				if (removeNode != null) {
					iztechMap.removeNodeFromMap(removeNode);
					iztechMap.removeEdgesWithGivenNodeFromMap(removeNode);
					int removeId = removeNode.getNodeId();
					iztechMap.updateIds(removeId);
					iztechMap.updateFiles(iztechMap);
					System.out.println("Succesfully removed the node.");
				}
				else {
					System.out.println("Cannot find the node named as: " + removeName);
				}
			}
			else if (choice.equals("4")) {
				System.out.println("Enter the source nodes name");
				String sourceName = keyboard.nextLine();
				Node sourceNode = iztechMap.findNodeWithName(sourceName);
				if (sourceNode != null) {
					System.out.println("Enter the distance amount");
					String distanceString = keyboard.nextLine();
					try{
						Double distance = Double.parseDouble(distanceString);
						iztechMap.getListOfPossibleReachableLocationWithAGivenDistance(sourceNode, distance);
					}
					catch(NumberFormatException exception){
						System.out.println("The distance has to be either integer or double");
					}
				}
				else {
					System.out.println("Can not find the node named: " + sourceName);
				}
			}
			else if (choice.equals("5")) {
				System.out.println("Enter the node name that you want to see its neighbours:");
				String nodeName = keyboard.nextLine();
				Node node = iztechMap.findNodeWithName(nodeName);
				if (iztechMap.getIztechMap().get(node) != null) {
					Collections.sort(iztechMap.getIztechMap().get(node));
					System.out.println("If there are any, neighbours are:");
					for (Node neighbourNode:iztechMap.getIztechMap().get(node)) {
						System.out.println(neighbourNode.toString());
					}
				}
				else {
					System.out.println("No neighbour can be found for the node");
				}
			}
			else if (choice.equals("6")) {
				System.out.println("Enter the first node name that you want to add an edge to:");
				String firstNodeName = keyboard.nextLine();
				Node node1 = iztechMap.findNodeWithName(firstNodeName);
				if (node1 != null) {
					System.out.println("Enter the second node name that you want to add an edge to:");
					String secondNodeName = keyboard.nextLine();
					Node node2 = iztechMap.findNodeWithName(secondNodeName);
					if (node2 != null) {
						Edge edge = new Edge(node1, node2, 0.0);
						iztechMap.setEdgeDistance(edge);
						iztechMap.addEdgeToMap(edge);
						iztechMap.updateFiles(iztechMap);
						System.out.println("Succesfully added the edge");
					}
				}
			}
			else if (choice.equals("7")) {
				System.out.println("Enter the node name that you want to check if its in the map:");
				String nodeName = keyboard.nextLine();
				Node node = iztechMap.findNodeWithName(nodeName);
				if (iztechMap.getIztechMap().get(node) != null) {
					System.out.println("It exists!!");
				}
			}
			else if (choice.equals("8")) {
				MapPlotter mapPlotter = new MapPlotter();
				mapPlotter.plot();
			}
			else if (choice.toLowerCase().equals("x")) {
				System.out.println("Exiting...");
				break;
			}
			else {
				System.out.println("You entered an unexpected input, try again!\n");
			}
		}
	}
}
