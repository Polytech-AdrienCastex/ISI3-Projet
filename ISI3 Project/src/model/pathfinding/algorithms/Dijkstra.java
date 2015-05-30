package model.pathfinding.algorithms;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import model.elementary.Valued;
import model.graph.Edge;
import model.graph.Node;
import model.authorizer.Authorizer;
import model.pathfinding.PathFinding;

/**
 * This class represents the Best First Search algorithm with a path finding
 * implementation.
 */
public class Dijkstra implements PathFinding
{
    @Override
    @SuppressWarnings("empty-statement")
    public Collection<Edge> getShortestPath(Node origin, Node dest, Authorizer auth)
    {
        try
        {
            Map<Node, Double> distances = new HashMap<>();

            Queue<Node> discoveredNodes = new LinkedList<>();

            Queue<Node> queue = new LinkedList<>();
            queue.add(origin);
            discoveredNodes.add(origin);

            origin.getGraph().getNodes().forEach(n -> distances.put(n, Double.POSITIVE_INFINITY));
            distances.put(origin, 0.0);

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
                            discoveredNodes.add(current);

                            distances.put(n, distances.get(current) + current.getEdges(n).stream()
                                    .filter(e -> auth.canUseEdge(e))
                                    .filter(e -> e instanceof Valued)
                                    .mapToDouble(e -> ((Valued)e).getValue())
                                    .min()
                                    .orElse(1.0));
                        });
            }

            return getPath(origin, dest, distances, auth);
        }
        catch(java.util.NoSuchElementException ex)
        {
            return Collections.emptyList();
        }
    }
    
    /**
     * Get the reconstructed path from the previously traversal graph.
     * @param origin Origin node.
     * @param dest Destionation node.
     * @param distances Map of the distances.
     * @param auth Authorizer to use to filter nodes and edges.
     * @return 
     */
    protected Collection<Edge> getPath(Node origin, Node dest, Map<Node, Double> distances, Authorizer auth)
    {
        Stack<Edge> edges = new Stack<>();
        Node lastNode = dest;
        Queue<Node> discoveredNodes = new LinkedList<>();

        do
        {
            discoveredNodes.add(lastNode);
            Node nextNode = getNextNode(lastNode, dest, distances, auth, discoveredNodes);

            if(nextNode == null)
            {
                if(edges.isEmpty())
                    return Collections.emptyList();
                Edge edg = edges.pop();
                nextNode = edg.getStartNode().equals(lastNode) ? edg.getStopNode(): edg.getStartNode();
            }
            else
                edges.add(lastNode.getEdges(nextNode)
                        .stream()
                        .filter(e -> !edges.contains(e))
                        .filter(e -> auth.canUseEdge(e))
                        .filter(e -> e instanceof Valued)
                        .min(Comparator.comparing(e -> ((Valued)e).getValue()))
                        .get());

            lastNode = nextNode;
        } while(!origin.equals(lastNode));

        Collections.reverse(edges);
        return edges;
    }
    
    /**
     * Get the next node in the path reconstruction.
     * @param current Current node.
     * @param dest Destionation node.
     * @param distances Map of the distances.
     * @param auth Authorizer to use to filter nodes and edges.
     * @param discoveredNodes Collection of already discovered nodes.
     * @return The new current node to use.
     */
    private Node getNextNode(Node current, Node dest, Map<Node, Double> distances, Authorizer auth, Queue<Node> discoveredNodes)
    {
        return current.getNextNodes()
                .stream()
                .filter(n -> auth.canUseNode(n) || n.equals(dest))
                .filter(n -> !discoveredNodes.contains(n))
                .filter(n -> n.getEdges(current).stream().anyMatch(e -> auth.canUseEdge(e)))
                .min(Comparator.comparingDouble(n -> distances.get(n)))
                .orElse(null);
    }
}
