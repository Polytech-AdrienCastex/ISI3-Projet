package model.item;

import java.util.Random;
import model.elementary.Fireable;
import model.elementary.Waterable;
import model.graph.Edge;
import model.graph.Node;

/**
 * FireHose
 * Firefighters will appreciate it
 */
public class FireHose implements IItem
{
    private final static int WATER_EDGE_PROBABILITY = 5; // %
    
    private final double intensity; //power force of the weapon
    private final Random rnd;
    
    public FireHose(double intensity)
    {
        this.intensity = intensity;
        this.rnd = new Random();
    }
    
    @Override
    public void actionEdge(Edge e)
    {
        if(e instanceof Waterable)
        {
            Waterable we = (Waterable)e;
            we.setUnderWater(we.isUnderWater() || rnd.nextInt(100) < WATER_EDGE_PROBABILITY);
        }
    }

    /**
     * Reduce intensity on the fire in the node in parameter
     * @param n Node in fire
     * @return true if the action has been performed
     */
    @Override
    public boolean actionNode(Node n)
    {
        if (n instanceof Fireable && ((Fireable)n).isOnFire())
        {
            Fireable f = (Fireable)n;
            f.setFireIntensity(f.getFireIntensity() - intensity);
            return true;
        }
        else
            return false;
    }    
}
