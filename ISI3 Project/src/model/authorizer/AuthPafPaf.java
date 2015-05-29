package model.authorizer;

import model.graph.Edge;
import model.elementary.Waterable;

/**
 * Truc à pattes
 */
public class AuthPafPaf extends AuthFlammable {
    @Override
    public Boolean canUseEdge(Edge e) {       
        //impossible chemin inondé
        if (e instanceof Waterable)
            return ((Waterable)e).isUnderWater();
        
        return super.canUseEdge(e);
    }        
}
