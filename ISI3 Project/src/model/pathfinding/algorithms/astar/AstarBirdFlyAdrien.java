package model.pathfinding.algorithms.astar;

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

/**
 *
 */
public class AstarBirdFlyAdrien extends Astar
{
    public AstarBirdFlyAdrien(Heuristique heuristique)
    {
        this.heuristique = heuristique;
    }
    
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
        f_score.put(origin, g_score.get(origin) + heuristique.getH(origin, dest));
        
        while(!openset.isEmpty())
        {
            current = openset.stream()
                    .filter(n -> f_score.get(n) > -1)
                    .min(Comparator.comparing(n -> f_score.get(n)))
                    .orElse(null);
            if(current == null)
                return Collections.EMPTY_LIST;
            if(current.equals(dest))
                return getPath(came_from, dest, auth);
            
            openset.remove(current);
            closedset.add(current);
            for(Node neighbor : current.getNextNodes().stream()
                    .filter(n -> n.equals(dest) || auth.canUseNode(n))
                    .filter(n -> !closedset.contains(n))
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
                    f_score.put(neighbor, tentative_g_score + heuristique.getH(neighbor, dest));
                    if(!openset.contains(neighbor))
                        openset.add(neighbor);
                }
            }
        }
        
        return Collections.EMPTY_LIST;
    }
    
    protected Collection<Edge> getPath(Map<Node, Node> came_from, Node current, Authorizer auth)
    {
        try
        {
            Collection<Node> total_path = new ArrayList<>();

            total_path.add(current);

            while(came_from.containsKey(current))
            {
                current = came_from.get(current);
                total_path.add(current);
            }

            List<Edge> path = new ArrayList<>();

            Node lastNode = null;
            for(Node n : total_path)
            {
                if(lastNode != null)
                {
                    path.add(lastNode.getEdges(n).stream()
                            .filter(e -> auth.canUseEdge(e))
                            .min(Comparator.comparing(e -> (e instanceof Valued) ? ((Valued)e).getValue() : 1.0))
                            .get());
                }

                lastNode = n;
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
