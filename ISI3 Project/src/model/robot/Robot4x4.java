package model.robot;

import model.graph.Node;
import model.pathfinding.PathFinding;

/**
 * Robot all ground
 */
public class Robot4x4 extends FlammableRobot {
    public Robot4x4(Double speed, Node currentNode, PathFinding pf) {
        super(speed, currentNode, pf);
    }
    
    /**
     * Can go through everything
     */
}
