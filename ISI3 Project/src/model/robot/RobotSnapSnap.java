package model.robot;

import model.EdgeType;
import model.graph.Edge;
import model.graph.Node;
import model.graph.Typed;
import model.pathfinding.PathFinding;

/**
 * Robot à chenilles
 */
public class RobotSnapSnap extends Robot {

    public RobotSnapSnap(Double speed, Node currentNode, PathFinding pf) {
        super(speed, currentNode, pf);
    }

    @Override
    public Boolean canUseEdge(Edge e) {
        //impossible chemin escarpé
        if (e instanceof Typed)
            return ((Typed)e).getType() == EdgeType.Escarpe;
        
        return true;
    }

    @Override
    public Boolean canUseNode(Node n) {
        return true;
    }
    
}
