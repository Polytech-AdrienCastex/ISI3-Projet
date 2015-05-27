package model.elementary;

/**
 * This class represents a point in 2 dimentionnal space.
 */
public class Point
{
    /**
     * Constructor.
     * @param x X coordinates.
     * @param y Y coordinates.
     */
    public Point(Double x, Double y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * X coordinates.
     */
    public Double x;
    /**
     * Y coordinates.
     */
    public Double y;
}
