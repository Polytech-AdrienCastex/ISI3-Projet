package model.authorizer;

import model.graph.Edge;
import model.graph.Node;

/**
 * Thing all ground
 */
public class Auth4x4 implements Authorizer {
    /**
     * Can go through everything
     * @param e
     * @return 
     */
    @Override
    public Boolean canUseEdge(Edge e) {
        return true;
    }

    /**
     * Can go through everything
     * @param n
     * @return 
     */
    @Override
    public Boolean canUseNode(Node n) {
        return true;
    }
}
