package model.graph;

/**
 *
 */
public class Edge
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
}
