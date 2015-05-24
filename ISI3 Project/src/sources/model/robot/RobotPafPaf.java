package sources.model.robot;

import sources.model.graph.Edge;
import sources.model.graph.Node;
import sources.model.elementary.Waterable;
import sources.model.pathfinding.PathFinding;

/**
 * Robot à pattes
 */
public class RobotPafPaf extends Robot {

    public RobotPafPaf(Double speed, Node currentNode, PathFinding pf) {
        super(speed, currentNode, pf);
    }

    @Override
    public Boolean canUseEdge(Edge e) {
        //impossible chemin inondé
        if (e instanceof Waterable)
            return ((Waterable)e).isUnderWater();
        
        return true;
    }

    @Override
    public Boolean canUseNode(Node n) {
        return true;
    }
    
}
