
package model.robot;

import model.elementary.Fireable;
import model.elementary.Waterable;
import model.graph.Edge;
import model.graph.Node;
import model.item.FireHose;
import model.pathfinding.PathFinding;


public class FlammableRobot extends FireFighterRobot {

    public FlammableRobot(Double speed, Node currentNode, PathFinding pf, FireHose fh) {
        super(speed, currentNode, pf, fh);
    }
    
    /**
     * Cannot go through edge in water
     * @param e Edge to test
     * @return true if the robot can go
     */
    @Override
    public Boolean canUseEdge(Edge e) {
        if (e instanceof Waterable)
            return !((Waterable)e).isUnderWater();
        
        return true;
    }

    /**
     * Cannot go through node in fire
     * @param n Node to test
     * @return true if the robot can go
     */
    @Override
    public Boolean canUseNode(Node n) {
        if (n instanceof Fireable)
            return !((Fireable)n).isOnFire();
        
        return true;
    }    
}
