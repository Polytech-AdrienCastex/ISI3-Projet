package model.authorizer;

import java.util.Arrays;
import model.SurfaceType;
import model.elementary.Fireable;
import model.elementary.Typed;
import model.elementary.Waterable;
import model.graph.Edge;
import model.graph.Node;

/**
 *
 */
public abstract class AuthRobot implements Authorizer
{
    @Override
    public Boolean canUseEdge(Edge e)
    {
        if(e instanceof Typed && !Arrays.asList(getAllowedSurfaceTypes()).contains(((Typed)e).getType()))
            return false;
        
        if(e instanceof Waterable && ((Waterable)e).isUnderWater() && !canGoThroughWater())
            return false;
        
        return true;
    }

    @Override
    public Boolean canUseNode(Node n)
    {
        if(n instanceof Fireable && ((Fireable)n).isOnFire()&& !canGoThroughFire())
            return false;
        
        return true;
    }
    
    protected abstract SurfaceType[] getAllowedSurfaceTypes();
    protected abstract Boolean canGoThroughWater();
    protected abstract Boolean canGoThroughFire();
}
