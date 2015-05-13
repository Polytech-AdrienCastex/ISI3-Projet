package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author p1002239
 */
public class Node
{
    public Node(Integer id)
    {
        this.id = id;
    }
    
    private final List<Edge> edges = new ArrayList<>();
    private final Integer id;
    
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
}
