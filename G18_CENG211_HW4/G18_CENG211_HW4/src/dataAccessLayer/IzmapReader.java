package dataAccessLayer;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import businessLayer.BuildingNode;
import businessLayer.Edge;
import businessLayer.IztechMap;
import businessLayer.LandscapeNode;
import businessLayer.Node;
import businessLayer.TypeOfCategory;


/**
 * @author Gorkem
 *
 */
public class IzmapReader {
	public static List<Node> IzmapNodeReader() throws IOException {
		int nodeId = 0;
		String category;
		String readTypeOfCategory;
		String name;
		List<Node> nodeContainer = new ArrayList<Node>();
		FileReader f = new FileReader("iztech.izmap");
		BufferedReader inputStream = new BufferedReader(f);
		String line = inputStream.readLine();
		while (line.length() != 0) {
			StringTokenizer idFinder = new StringTokenizer(line, " ");
			if (idFinder.hasMoreTokens()) {
				String tokenThatCanBeCommentOrNewLine = idFinder.nextToken();
				if (tokenThatCanBeCommentOrNewLine.equals("#")){//read comment,do nothing
				}
				else {
					nodeId = Integer.parseInt(tokenThatCanBeCommentOrNewLine);
					int removeFromStart = String.valueOf(nodeId).length();
					line = line.substring(removeFromStart + 2,line.length()-1);//remove the id, white space and the brace
					StringTokenizer wordFinder = new StringTokenizer(line, ",");
					category = wordFinder.nextToken();
					if (category.toLowerCase().equals("building")) {
						readTypeOfCategory = wordFinder.nextToken().substring(1);//remove white space
						if (readTypeOfCategory.toLowerCase().equals("department")) {
							TypeOfCategory typeOfCategory = TypeOfCategory.DEPARTMENT;
							name = wordFinder.nextToken().substring(1);// whitespace
							Node newNode = new BuildingNode(nodeId, typeOfCategory, name);
							nodeContainer.add(newNode);
						}
						else if (readTypeOfCategory.toLowerCase().equals("facility")) {
							TypeOfCategory typeOfCategory = TypeOfCategory.FACILITY;
							name = wordFinder.nextToken().substring(1);// whitespace
							Node newNode = new BuildingNode(nodeId, typeOfCategory, name);
							nodeContainer.add(newNode);
						}
						else if(readTypeOfCategory.toLowerCase().equals("administrative")) {
							TypeOfCategory typeOfCategory = TypeOfCategory.ADMINISTRATIVE;
							name = wordFinder.nextToken().substring(1);// whitespace
							Node newNode = new BuildingNode(nodeId, typeOfCategory, name);
							nodeContainer.add(newNode);
						}
						else if(readTypeOfCategory.toLowerCase().equals("cafeteria")) {
							TypeOfCategory typeOfCategory = TypeOfCategory.CAFETERIA;
							name = wordFinder.nextToken().substring(1);// whitespace
							Node newNode = new BuildingNode(nodeId, typeOfCategory, name);
							nodeContainer.add(newNode);
						}
						else {
							line = inputStream.readLine();//if type of category doesn't meet the requirements, disperses the line.
						}
					}
					else if (category.toLowerCase().equals("landscape")) {
						readTypeOfCategory = wordFinder.nextToken().substring(1);
						if (readTypeOfCategory.toLowerCase().equals("waterfall")){
							TypeOfCategory typeOfCategory = TypeOfCategory.WATERFALL;
							name = wordFinder.nextToken().substring(1);// whitespace
							Node newNode = new LandscapeNode(nodeId, typeOfCategory, name);
							nodeContainer.add(newNode);
						} 
						else if (readTypeOfCategory.toLowerCase().equals("beach")) {
							TypeOfCategory typeOfCategory = TypeOfCategory.BEACH;
							name = wordFinder.nextToken().substring(1);// whitespace
							Node newNode = new LandscapeNode(nodeId, typeOfCategory, name);
							nodeContainer.add(newNode);
						} 
						else if (readTypeOfCategory.toLowerCase().equals("historical ruin")){
							TypeOfCategory typeOfCategory = TypeOfCategory.HISTORICAL_RUIN;
							name = wordFinder.nextToken().substring(1);
							Node newNode = new LandscapeNode(nodeId, typeOfCategory, name);
							nodeContainer.add(newNode);
						}
						else {
							line = inputStream.readLine();
						}	
					}
				}	
			}
			line = inputStream.readLine();
		}	
		inputStream.close();
		return nodeContainer;
	}
	
	private static boolean lineIsComment(String line) {
		if (line.substring(0,1).equals("#")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static List<Edge> IzmapEdgeReader() throws IOException{
		List<Edge> edgeContainer = new ArrayList<Edge>();
		FileReader f = new FileReader("iztech.izmap");
		BufferedReader inputStream = new BufferedReader(f);
		String line = inputStream.readLine();
		line = inputStream.readLine();
		while (line != null) {
			if (line.length() == 0) {//reaching the new line segment
				line = inputStream.readLine();
				while (lineIsComment(line)) {
					line = inputStream.readLine();
				}
				while(line != null) {
					StringTokenizer edgeFinder = new StringTokenizer(line, " <--> ");
					int firstVertice = Integer.parseInt(edgeFinder.nextToken());
					Node node1 = IztechMap.findNode(firstVertice);
					int secondVertice = Integer.parseInt(edgeFinder.nextToken());
					Node node2 = IztechMap.findNode(secondVertice);
					IztechMap.findNode(secondVertice);
					Edge edge = new Edge(node1, node2, 0.0);
					edgeContainer.add(edge);
					line = inputStream.readLine();
				}
				inputStream.close();
				return edgeContainer;
			}
			else {// if we are in the first part, read a newline
				line = inputStream.readLine();
			}
		}
		inputStream.close();
		return edgeContainer;
	}
}
