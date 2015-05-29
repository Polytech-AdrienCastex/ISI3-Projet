
package model.authorizer;

import model.elementary.Fireable;
import model.graph.Edge;
import model.graph.Node;


public class AuthFlammable implements Authorizer {
    /**
     * Cannot go through edge in water
     * @param e Edge to test
     * @return true if the thing can go
     */
    @Override
    public Boolean canUseEdge(Edge e) {        
        return true;
    }

    /**
     * Cannot go through node in fire
     * @param n Node to test
     * @return true if the thing can go
     */
    @Override
    public Boolean canUseNode(Node n) {
        if (n instanceof Fireable)
            return !((Fireable)n).isOnFire();
        
        return true;
    }    
}
