package model.authorizer;

import model.graph.Edge;
import model.graph.Node;

/**
 * This interface represents the ability an object has to say if it can go
 * through a specified node or through a specified edge.
 */
public interface Authorizer
{
    /**
     * If a edge can be used
     * @param e Edge
     * @return True if the edge can be used
     */
    public Boolean canUseEdge(Edge e);
    
    /**
     * If the node can be used
     * @param n Node
     * @return True if the node can be used
     */
    public Boolean canUseNode(Node n);
}
