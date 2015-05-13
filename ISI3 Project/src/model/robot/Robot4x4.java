package model.robot;

import model.graph.Edge;
import model.graph.Node;

/**
 * Robot tout terrain
 */
public class Robot4x4 extends Robot {

    public Robot4x4(Double vitesse, Node currentNode) {
        super(vitesse, currentNode);
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
