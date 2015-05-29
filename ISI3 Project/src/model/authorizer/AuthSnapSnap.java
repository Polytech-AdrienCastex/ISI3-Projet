package model.authorizer;

import model.SurfaceType;
import model.graph.Edge;
import model.elementary.Typed;

/**
 * Truc à chenilles
 */
public class AuthSnapSnap extends AuthFlammable {
    @Override
    public Boolean canUseEdge(Edge e) {
        //impossible chemin escarpé
        if (e instanceof Typed)
            return ((Typed)e).getType() == SurfaceType.Escarpe;
        
        return super.canUseEdge(e);
    }    
}
