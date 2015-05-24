package sources.model.pathfinding;

import java.util.List;
import sources.model.graph.Edge;
import sources.model.graph.Node;

public interface PathFinding {
    /**
     * Get the shortest path from origin to dest with the authorize Nodes & Edges
     * @param origin Origin node
     * @param dest Destination node
     * @param auth Authorizer
     * @return List of edges which represent the shortest way
     */
    public List<Edge> getShortestPath(Node origin, Node dest, Authorizer auth);
}
