package model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.pathfinding.PathFinding;

/**
 * Robot all ground
 */
public class Robot4x4 extends Robot {

    public Robot4x4(Double speed, Node currentNode, PathFinding pf) {
        super(speed, currentNode, pf);
    }
    

    @Override
    public Boolean canUseEdge(Edge e) {
        return true;
    }

    @Override
    public Boolean canUseNode(Node n) {
        return true;
    }
}
