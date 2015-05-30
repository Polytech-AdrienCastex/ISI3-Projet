package model.pathfinding.algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.authorizer.Authorizer;
import model.elementary.Valued;
import model.graph.Edge;
import model.graph.Node;
import model.pathfinding.PathFinding;
import model.pathfinding.algorithms.heuristics.Heuristic;

/**
 * This class represents the A* algorithm to find the best path in a graph.
 */
public class Astar implements PathFinding
{
    /**
     * Constructor.
     * @param heuristic Heuristic to use.
     */
    public Astar(Heuristic heuristic)
    {
        this.heuristic = heuristic;
    }
    
    /**
     * The heuristic to use.
     */
    protected final Heuristic heuristic;
    
    @Override
    public Collection<Edge> getShortestPath(Node origin, Node dest, Authorizer auth)
    {
        Collection<Node> closedset = new HashSet<>();
        Collection<Node> openset = new HashSet<>();
        Map<Node, Node> came_from = new HashMap<>();
        
        Map<Node, Double> g_score = new HashMap<>();
        Map<Node, Double> f_score = new HashMap<>();
        
        Node current;
        
        openset.add(origin);
        g_score.put(origin, 0.0);
        f_score.put(origin, g_score.get(origin) + heuristic.getValue(origin, dest));
        
        while(!openset.isEmpty())
        {
            current = openset.stream()
                    .filter(n -> f_score.get(n) > -1)
                    .min(Comparator.comparing(n -> f_score.get(n)))
                    .orElse(null);
            if(current == null) // No current node found
                return Collections.EMPTY_LIST;
            if(current.equals(dest)) // Current node is destination -> return the path.
                return getPath(came_from, dest, auth);
            
            openset.remove(current);
            closedset.add(current);
            for(Node neighbor : current.getNextNodes().stream()
                    .filter(n -> n.equals(dest) || auth.canUseNode(n)) // Destination or authorized
                    .filter(n -> !closedset.contains(n)) // Not explored yet
                    .collect(Collectors.toList()))
            {
                Double tentative_g_score = g_score.get(current) + current.getEdges(neighbor).stream()
                        .filter(e -> auth.canUseEdge(e))
                        .mapToDouble(e -> (e instanceof Valued) ? ((Valued)e).getValue() : 1.0)
                        .min()
                        .orElse(Double.POSITIVE_INFINITY);
                
                if(!openset.contains(neighbor) || tentative_g_score < g_score.get(neighbor))
                {
                    came_from.put(neighbor, current);
                    g_score.put(neighbor, tentative_g_score);
                    f_score.put(neighbor, tentative_g_score + heuristic.getValue(neighbor, dest));
                    if(!openset.contains(neighbor))
                        openset.add(neighbor);
                }
            }
        }
        
        return Collections.EMPTY_LIST;
    }
    
    /**
     * Reconstruct the path with edges from a map of predecessors.
     * <p>
     * Returns an empty collection if there is no path possible.
     * @param came_from Map of predecessors.
     * @param current Node where to start the path reconstruction.
     * @param auth Authorizer used to filter edges.
     * @return The path with edges.
     */
    protected Collection<Edge> getPath(Map<Node, Node> came_from, Node current, Authorizer auth)
    {
        try
        {
            List<Edge> path = new ArrayList<>();

            while(came_from.containsKey(current))
            {
                Node from = current;
                current = came_from.get(current);
                
                path.add(from.getEdges(current).stream()
                        .filter(e -> auth.canUseEdge(e))
                        .min(Comparator.comparing(e -> (e instanceof Valued) ? ((Valued)e).getValue() : 1.0))
                        .get());
            }
            
            Collections.reverse(path);
            return path;
        }
        catch(Exception ex)
        {
            return Collections.EMPTY_LIST;
        }
    }
}
