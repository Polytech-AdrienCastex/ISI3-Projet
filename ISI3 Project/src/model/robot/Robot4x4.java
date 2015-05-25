package model.robot;

import model.graph.Node;
import model.item.FireHose;
import model.pathfinding.PathFinding;

/**
 * Robot all ground
 */
public class Robot4x4 extends FlammableRobot {

    public Robot4x4(Double speed, Node currentNode, PathFinding pf, FireHose fh) {
        super(speed, currentNode, pf, fh);
    }
    
    
    /**
     * Can go through everything
     */
}
