package model.pathfinding.algorithms.astar;

import model.graph.project.FireableNode;


public interface FlyHeuristique extends Heuristique {
    
    public double getH(FireableNode dep, FireableNode inter, FireableNode arr);
}
