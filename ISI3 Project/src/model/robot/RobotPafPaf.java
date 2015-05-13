package model.robot;

import model.graph.Edge;
import model.graph.Node;

/**
 * Robot à pattes
 */
public class RobotPafPaf extends Robot {

    public RobotPafPaf(Double vitesse, Node currentNode) {
        super(vitesse, currentNode);
    }

    @Override
    public Boolean canUseEdge(Edge e) {
       //impossible chemin inondé
        return false;
    }

    @Override
    public Boolean canUseNode(Node n) {
        return true;
    }
    
}
