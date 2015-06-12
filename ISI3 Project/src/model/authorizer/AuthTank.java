package model.authorizer;

import model.SurfaceType;

/**
 * Authorizer all ground
 */
public class AuthTank extends AuthRobot
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
        return true;
    }

    @Override
    public String toString()
    {
        return "Tank";
    }
}
