package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author p1002239
 */
public class Node
{
    public Node(Integer id, Graph graph)
    {
        this.id = id;
        this.graph = graph;
        graph.addNode(this);
    }
    public Node(Graph graph)
    {
        this.id = graph.getUID();
        this.graph = graph;
        graph.addNode(this);
    }
    
    private final List<Edge> edges = new ArrayList<>();
    private final Integer id;
    private final Graph graph;
    
    void addEdge(Edge e)
    {
        edges.add(e);
    }
    
    public List<Edge> getEdges()
    {
        return edges;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public List<Node> getNextNodes()
    {
        return edges.stream()
                .filter(e -> this.equals(e.getStartNode()))
                .map(e -> e.getStopNode())
                .collect(Collectors.toList());
    }
    
    public List<Edge> getEdges(Node n)
    {
        return edges.stream()
                .filter(e -> this.equals(e.getStartNode()))
                .filter(e -> n.equals(e.getStopNode()))
                .collect(Collectors.toList());
    }
    
    public Graph getGraph()
    {
        return graph;
    }
}
