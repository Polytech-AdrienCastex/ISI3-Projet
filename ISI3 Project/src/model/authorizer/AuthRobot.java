package model.authorizer;

import java.util.Arrays;
import model.SurfaceType;
import model.elementary.Fireable;
import model.elementary.Typed;
import model.elementary.Waterable;
import model.graph.Edge;
import model.graph.Node;

/**
 * Abstract authorizer model for robots
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
    
    /**
     * Get the type of surfaces allowed 
     * @return surface type allowed.
     */
    protected abstract SurfaceType[] getAllowedSurfaceTypes();
    
    /**
     * Can the robot go through water ?
     * @return true if he can go through water
     */
    protected abstract Boolean canGoThroughWater();
    
    /**
     * Can the robot go through fire ?
     * @return true if he can go through fire
     */
    protected abstract Boolean canGoThroughFire();
}
