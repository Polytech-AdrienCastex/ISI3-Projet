package model.authorizer;

import model.SurfaceType;

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
            SurfaceType.Plat
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

    @Override
    public String toString()
    {
        return "Snap snap";
    }
}
