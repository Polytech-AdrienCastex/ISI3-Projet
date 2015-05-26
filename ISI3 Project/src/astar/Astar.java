package astar;

import java.util.List;
import model.graph.Edge;
import model.graph.Node;
import model.pathfinding.Authorizer;
import model.pathfinding.PathFinding;

public abstract class Astar implements PathFinding{

    protected Heuristique h;
    
    //public abstract List<Edge> getShortestPath(Node init, Node goal, Authorizer auth, FlyHeuristique h);
}
