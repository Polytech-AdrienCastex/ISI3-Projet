package model.authorizer;

import model.SurfaceType;

/**
 * Truc à pattes
 */
public class AuthPafPaf extends AuthRobot
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
        return true;
    }

    @Override
    protected Boolean canGoThroughFire()
    {
        return false;
    }
}
