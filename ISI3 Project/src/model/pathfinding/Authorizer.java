package model.pathfinding;

import model.graph.Edge;
import model.graph.Node;

public interface Authorizer {
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
