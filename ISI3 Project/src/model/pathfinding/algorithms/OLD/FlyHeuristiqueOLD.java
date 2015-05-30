package model.pathfinding.algorithms.OLD;

import model.pathfinding.algorithms.heuristics.Heuristic;
import model.graph.project.FireableNode;


public interface FlyHeuristiqueOLD extends Heuristic
{
    public double getH(FireableNode dep, FireableNode inter, FireableNode arr);
}
