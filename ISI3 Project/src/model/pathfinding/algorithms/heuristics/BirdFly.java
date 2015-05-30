package model.pathfinding.algorithms.heuristics;

import model.elementary.Localisable;
import model.elementary.Point;
import model.graph.Node;


/**
 * This class represents the bird fly heuristic to calculate the distance
 * between two nodes.
 */
public class BirdFly implements Heuristic
{
    @Override
    public double getValue(Node n1, Node n2)
    {
        if(!(n1 instanceof Localisable && n2 instanceof Localisable))
            return Double.NaN;
        
        Point p1 = ((Localisable)n1).getLocation();
        Point p2 = ((Localisable)n2).getLocation();
        return euclideanDistance(p1, p2);
    }
    
    /**
     * Get the euclidean distance between <b>p1</b> and <b>p2</b>.
     * @param p1 First point.
     * @param p2 Second point.
     * @return sqrt( (p1.x - p2.x)² + (p1.y - p2.y)² )
     */
    protected double euclideanDistance(Point p1, Point p2)
    {
        double dX = p1.x - p2.x;
        double dY = p1.y - p2.y;
        
        return Math.sqrt(dX*dX + dY*dY);
    }
}
