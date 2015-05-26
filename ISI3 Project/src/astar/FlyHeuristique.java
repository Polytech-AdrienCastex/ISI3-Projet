package astar;

import model.graph.project.FireableNode;
import model.graph.Node;


public interface FlyHeuristique extends Heuristique {
    
    public double getH(FireableNode dep, FireableNode inter, FireableNode arr);
}
