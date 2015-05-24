package model.robot;

import model.EdgeType;
import model.graph.Edge;
import model.graph.Node;
import model.elementary.Typed;
import model.pathfinding.PathFinding;

/**
 * Robot à chenilles
 */
public class RobotSnapSnap extends FlammableRobot {

    public RobotSnapSnap(Double speed, Node currentNode, PathFinding pf) {
        super(speed, currentNode, pf);
    }

    @Override
    public Boolean canUseEdge(Edge e) {
        //impossible chemin escarpé
        if (e instanceof Typed)
            return ((Typed)e).getType() == EdgeType.Escarpe;
        
        return super.canUseEdge(e);
    }    
}
