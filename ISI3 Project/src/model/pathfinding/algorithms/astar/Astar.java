package model.pathfinding.algorithms.astar;

import model.pathfinding.PathFinding;

public abstract class Astar implements PathFinding {

    protected Heuristique h;
    
    //public abstract List<Edge> getShortestPath(Node init, Node goal, Authorizer auth, FlyHeuristique h);
}
