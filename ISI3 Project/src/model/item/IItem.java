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
     * @return false if no action has been done.
     */
    public boolean actionNode(Node n);
    
    /**
     * Test if this item can be used on the node in parameter.
     * @param n Node to test.
     * @return true if this item can be used.
     */
    public boolean canUse(Node n);
}
