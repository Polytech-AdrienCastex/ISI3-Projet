package model.robot;

import model.graph.Edge;
import model.graph.Node;
import model.item.FireHose;
import model.pathfinding.PathFinding;

/**
 * Robot all ground
 */
public class Robot4x4 extends FireFighterRobot {

    public Robot4x4(Double speed, Node currentNode, PathFinding pf, FireHose fh) {
        super(speed, currentNode, pf, fh);
    }

    /**
     * Can go through everything
     * @param e
     * @return 
     */
    @Override
    public Boolean canUseEdge(Edge e) {
        return true;
    }

    /**
     * Can go through everything
     * @param n
     * @return 
     */
    @Override
    public Boolean canUseNode(Node n) {
        return true;
    }
}
