package astar;

import model.graph.project.FireableNode;

public interface Heuristique {
    public double getH(FireableNode dep, FireableNode arr); 
    
}
