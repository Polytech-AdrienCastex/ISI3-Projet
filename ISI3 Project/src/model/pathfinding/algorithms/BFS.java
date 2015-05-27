package model.pathfinding.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import model.elementary.Valued;
import model.graph.Edge;
import model.graph.Node;
import model.pathfinding.Authorizer;
import model.pathfinding.PathFinding;

/**
 * This class represents the Best First Search algorithm with a path finding
 * implementation.
 */
public class BFS implements PathFinding
{
    @Override
    @SuppressWarnings("empty-statement")
    public List<Edge> getShortestPath(Node origin, Node dest, Authorizer auth)
    {
        try
        {
            Map<Node, Double> distance = new HashMap<>();

            Queue<Node> discoveredNodes = new LinkedList<>();

            Queue<Node> queue = new LinkedList<>();
            queue.add(origin);
            discoveredNodes.add(origin);

            origin.getGraph().getNodes().forEach(n -> distance.put(n, Double.POSITIVE_INFINITY));
            distance.put(origin, 0.0);

            while(!queue.isEmpty())
            {
                Node current = queue.remove();

                current.getNextNodes()
                        .stream()
                        .filter(n -> auth.canUseNode(n) || n.equals(dest))
                        .filter(n -> !discoveredNodes.contains(n))
                        .forEach(n ->
                        {
                            queue.add(n);
                            discoveredNodes.add(n);

                            distance.put(n, distance.get(current) + current.getEdges(n).stream()
                                    .filter(e -> auth.canUseEdge(e))
                                    .filter(e -> e instanceof Valued)
                                    .mapToDouble(e -> ((Valued)e).getValue())
                                    .min()
                                    .orElse(1.0));
                        });
            }

            List<Edge> edges = new ArrayList<>();
            Node lastNode = dest;

            do
            {
                Node nextNode = lastNode.getNextNodes()
                        .stream()
                        .filter(n -> auth.canUseNode(n) || n.equals(dest))
                        .min(Comparator.comparingDouble(n -> distance.get(n)))
                        .orElse(null);
                
                if(nextNode == null)
                    return new ArrayList<>();

                edges.add(lastNode.getEdges(nextNode)
                        .stream()
                        .filter(e -> auth.canUseEdge(e))
                        .filter(e -> e instanceof Valued)
                        .min(Comparator.comparing(e -> ((Valued)e).getValue()))
                        .get());

                lastNode = nextNode;
            } while(!origin.equals(lastNode));

            Collections.reverse(edges);
            return edges;
        }
        catch(java.util.NoSuchElementException ex)
        {
            return new ArrayList<>();
        }
    }
}
