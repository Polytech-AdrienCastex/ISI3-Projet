package model.robot;

import model.SurfaceType;
import model.graph.Edge;
import model.graph.Node;
import model.elementary.Typed;
import model.item.FireHose;
import model.pathfinding.PathFinding;

/**
 * Robot à chenilles
 */
public class RobotSnapSnap extends FlammableRobot {

    public RobotSnapSnap(Double speed, Node currentNode, PathFinding pf, FireHose fh) {
        super(speed, currentNode, pf, fh);
    }

    

    @Override
    public Boolean canUseEdge(Edge e) {
        //impossible chemin escarpé
        if (e instanceof Typed)
            return ((Typed)e).getType() == SurfaceType.Escarpe;
        
        return super.canUseEdge(e);
    }    
}
