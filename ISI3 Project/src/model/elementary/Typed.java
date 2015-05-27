package model.elementary;

import model.SurfaceType;

/**
 * This interface represents an object which can be typed with a SurfaceType.
 */
public interface Typed
{
    /**
     * Get the type of the surface.
     * @return The type of the surface.
     */
    public SurfaceType getType();
}
