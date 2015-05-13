package model.robot;

import model.graph.Edge;
import model.graph.Node;

/**
 * Robot à chenilles
 */
public class RobotSnapSnap extends Robot {

    public RobotSnapSnap(Double vitesse, Node currentNode) {
        super(vitesse, currentNode);
    }

    @Override
    public Boolean canUseEdge(Edge e) {
        //impossible chemin escarpé
        return false;
    }

    @Override
    public Boolean canUseNode(Node n) {
        return true;
    }
    
}
