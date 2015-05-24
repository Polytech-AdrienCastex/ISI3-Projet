package sources.model.graph;

import sources.model.Observable;
import sources.model.xml.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 */
public class Edge extends Observable implements Serializable
{
    /**
     * Create an edge between two nodes of a graph
     * @param start Start node
     * @param end End node
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

    @Override
    public Element toXML(Document elementBuilder)
    {
        Element element = elementBuilder.createElement("edge");
        element.setAttribute("nd1", start.getId().toString());
        element.setAttribute("nd2", end.getId().toString());
        return element;
    }
}