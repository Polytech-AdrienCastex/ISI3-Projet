package model.pathfinding;

import model.authorizer.Authorizer;
import java.util.Collection;
import model.graph.Edge;
import model.graph.Node;

/**
 * This interface represents a path finding system.
 */
public interface PathFinding
{
    /**
     * Get the shortest path from <i>origin</i> to <i>dest</i> with the
     * authorized nodes and edges. If no path found, it returns an empty
     * collection.
     * @param origin Origin node
     * @param dest Destination node
     * @param auth Authorizer
     * @return List of edges which represent the shortest way
     */
    public Collection<Edge> getShortestPath(Node origin, Node dest, Authorizer auth);
}
