package dataAccessLayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import businessLayer.BuildingNode;
import businessLayer.Edge;
import businessLayer.IztechMap;
import businessLayer.LandscapeNode;
import businessLayer.Node;


/**
 * @author Gorkem
 *
 */
public class IzmapWriter {

	public static void IzmapFileWriter(IztechMap iztechMap) throws FileNotFoundException {
		PrintWriter printWriter = new PrintWriter("iztech.izmap");
		printWriter.write("# Node (Location) definitions\n");
		String typeOfCategory;
		List<Node> nodeContainer = iztechMap.getNodeContainer();
		for (Node node:nodeContainer) {
			if (node instanceof BuildingNode) {
				BuildingNode writeNode = (BuildingNode) node;
				if (writeNode.getTypeOfCategory().toString().equals("DEPARTMENT")){
					typeOfCategory = "Department";
				}
				else if (writeNode.getTypeOfCategory().toString().equals("CAFETERIA")) {
					typeOfCategory = "Cafeteria";
				}
				else if (writeNode.getTypeOfCategory().toString().equals("FACILITY")) {
				    typeOfCategory = "Facility";
				}
				else {
					typeOfCategory = "Administrative";
				}
				printWriter.write(writeNode.getNodeId() + " [" + writeNode.getCategory() + ", " + typeOfCategory + ", " + writeNode.getName() + "]\n");
			}
			else if (node instanceof LandscapeNode) {
				LandscapeNode writeNode = (LandscapeNode) node;
				if (writeNode.getTypeOfCategory().toString().equals("BEACH")){
					typeOfCategory = "Beach";
				}
				else if (writeNode.getTypeOfCategory().toString().equals("HISTORICAL_RUIN")) {
					typeOfCategory = "Historical Ruin";
				}
				else {
					typeOfCategory = "Waterfall";
				}
				printWriter.write(writeNode.getNodeId() + " [" + writeNode.getCategory() + ", " + typeOfCategory + ", " + writeNode.getName() + "]\n");
			}
		}
		printWriter.write("\n# Edges From/To <--> To/From\n" + 
				"# Remember that every arrow (<-->) is bi-directional.\n");
		List<Edge> edgeContainer = iztechMap.getEdgeContainer();
		for (Edge edge:edgeContainer) {
			printWriter.write(edge.getVertice1().getNodeId() + " <--> " + edge.getVertice2().getNodeId() + "\n");
		}
		printWriter.close();
	}
}