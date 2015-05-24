package sources.model.robot;

import sources.model.EdgeType;
import sources.model.graph.Edge;
import sources.model.graph.Node;
import sources.model.elementary.Typed;
import sources.model.pathfinding.PathFinding;

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
