package model.pathfinding.algorithms.astar;

import model.graph.Node;

public interface Heuristique
{
    public double getH(Node dep, Node arr);
}
