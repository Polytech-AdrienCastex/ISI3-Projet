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

    @Override
    public String toString()
    {
        return "Paf paf";
    }
}
