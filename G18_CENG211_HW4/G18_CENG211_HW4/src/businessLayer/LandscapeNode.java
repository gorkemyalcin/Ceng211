package businessLayer;

/**
 * @author Gorkem
 *
 */
public class LandscapeNode extends Node {
	
	private String category;
	
	public LandscapeNode(int nodeId, TypeOfCategory typeOfCategory, String name) {
		super(nodeId, typeOfCategory, name);
		this.category = "Landscape";
	}
	
	public LandscapeNode() {
		super();
		this.category = "not initialized";
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
    public boolean equals(Object otherObject)
	{
	    if (otherObject == null)
	        return false;
	    else if (getClass( ) != otherObject.getClass( ))
	        return false;
	    else
	    {LandscapeNode otherNode =  (LandscapeNode)otherObject;
	        return (getNodeId() == (otherNode.getNodeId())) && (getCategory().equals(otherNode.getCategory())) &&
	        	(getTypeOfCategory().equals(otherNode.getTypeOfCategory())) && (getName().equals(otherNode.getName()));
	    }
    }
    
    public int hashCode() {
        int result = 17;
        result = 31 * result;
        for (int i = 0; i<getName().length(); i++) {
        	result = result + (int)getName().charAt(i);
        }
        return result;
      }
}
