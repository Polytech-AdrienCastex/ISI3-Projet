package model.authorizer;

import java.util.Arrays;
import model.SurfaceType;
import model.graph.Edge;
import model.elementary.Typed;

/**
 * Truc à chenilles
 */
public class AuthSnapSnap extends AuthRobot
{
    @Override
    protected SurfaceType[] getAllowedSurfaceTypes()
    {
        return new SurfaceType[]
        {
            SurfaceType.Plat,
            SurfaceType.Escarpe
        };
    }

    @Override
    protected Boolean canGoThroughWater()
    {
        return false;
    }

    @Override
    protected Boolean canGoThroughFire()
    {
        return false;
    }
}
