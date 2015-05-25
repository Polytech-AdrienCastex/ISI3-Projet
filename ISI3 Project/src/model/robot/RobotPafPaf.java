package model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.elementary.Waterable;
import model.item.FireHose;
import model.pathfinding.PathFinding;

/**
 * Robot à pattes
 */
public class RobotPafPaf extends FlammableRobot {

    public RobotPafPaf(Double speed, Node currentNode, PathFinding pf, FireHose fh) {
        super(speed, currentNode, pf, fh);
    }

    

    @Override
    public Boolean canUseEdge(Edge e) {       
        //impossible chemin inondé
        if (e instanceof Waterable)
            return ((Waterable)e).isUnderWater();
        
        return super.canUseEdge(e);
    }        
}
