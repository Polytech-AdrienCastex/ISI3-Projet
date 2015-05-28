package model.graph;

import model.Observable;
import model.xml.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents an edge in a graph.
 */
public class Edge extends Observable implements Serializable
{
    /**
     * Create an edge between two nodes of a graph.
     * @param start Start node.
     * @param end End node.
     */
    public Edge(Node start, Node end)
    {
        this.start = start;
        this.end = end;
        
        this.start.addEdge(this);
        this.end.addEdge(this);
    }
    
    /**
     * Start node
     */
    private final Node start;
    /**
     * End node
     */
    private final Node end;
    
    /**
     * Get the node at the beginning of the edge
     * @return Node
     */
    public Node getStartNode()
    {
        return start;
    }
    
    /**
     * Get the node at the end of the edge
     * @return Node
     */
    public Node getStopNode()
    {
        return end;
    }
    
    /**
     * Dispose the current edge.
     * <p>
     * It will remove the link between the two linked nodes.
     */
    public void unlink()
    {
        getStartNode().removeEdge(this);
        getStopNode().removeEdge(this);
    }

    @Override
    public Element toXML(Document elementBuilder)
    {
        Element element = elementBuilder.createElement("edge");
        element.setAttribute("nd1", start.getId().toString());
        element.setAttribute("nd2", end.getId().toString());
        return element;
    }
    

    @Override
    public String toString()
    {
        return "(" + this.getStartNode().getId() + " ; " + this.getStopNode().getId() + ")";
    }
}
