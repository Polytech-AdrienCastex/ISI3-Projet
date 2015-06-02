package model.authorizer;

import model.SurfaceType;

/**
 * Thing all ground
 */
public class Auth4x4 extends AuthRobot
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
        return true;
    }

    @Override
    protected Boolean canGoThroughFire()
    {
        return false;
    }

    @Override
    public String toString()
    {
        return "4x4";
    }
}
