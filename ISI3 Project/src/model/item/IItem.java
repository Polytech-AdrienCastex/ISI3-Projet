package model.item;

import model.graph.Edge;
import model.graph.Node;

/**
 * Interface item
 */
public interface IItem {
    /**
     * Action that need to be done on the edge in parameter
     * @param e Edge
     */
    public void actionEdge(Edge e);
    
    /**
     * Action that need to be done on the node in parameter
     * @param n Node
     */
    public boolean actionNode(Node n);
    
    public boolean canUse(Node n);
}
