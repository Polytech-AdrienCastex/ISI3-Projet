package model.item;

import model.elementary.Fireable;
import model.graph.Edge;
import model.graph.Node;

/**
 * FireHose
 * Firefighters will appreciate it
 */
public class FireHose implements IItem {
    private double intensity; //power force of the weapon
    
    @Override
    public void actionEdge(Edge e) {
        //none
    }

    /**
     * Reduce intensity on the fire in the node in parameter
     * @param n Node in fire
     */
    @Override
    public void actionNode(Node n) {
        if (n instanceof Fireable && ((Fireable)n).isOnFire())
        {
            Fireable f = (Fireable)n;
            f.setFireIntensity(f.getFireIntensity()-intensity);
        }  
        
        //Ajouter de l'eau sur les arretes pour les inondées
    }    
}
