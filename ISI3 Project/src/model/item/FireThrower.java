package model.item;

import java.util.Random;
import model.elementary.Fireable;
import model.graph.Edge;
import model.graph.Node;

/**
 * FireThrower
 */
public class FireThrower implements IItem
{
    private final static int FIRE_NODE_PROBABILITY = 20; // %
    
    private final double intensity; //power force of the weapon
    private final Random rnd;
    
    /**
     * Contructor
     * @param intensity intensity of the weapon 
     */
    public FireThrower(double intensity)
    {
        this.intensity = intensity;
        this.rnd = new Random();
    }
    
    @Override
    public void actionEdge(Edge e)
    {
        // nothing
    }

    /**
     * Increase intensity on the fire in the node in parameter
     * @param n Node in fire
     * @return true if the action has been performed
     */
    @Override
    public boolean actionNode(Node n)
    {
        if (canUse(n))
        {
            Fireable f = (Fireable)n;
            if(rnd.nextInt(100) < FIRE_NODE_PROBABILITY)
            {
                f.setFireIntensity(f.getFireIntensity() + intensity);
                return true;
            }
        }
        
        return false;
    }      

    @Override
    public boolean canUse(Node n)
    {
        return n instanceof Fireable && !((Fireable)n).isOnFire();
    }

    @Override
    public String toString()
    {
        return "Fire thrower";
    }
}
