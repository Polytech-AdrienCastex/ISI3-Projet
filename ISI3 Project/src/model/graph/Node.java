package model.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 */
public class Node implements Serializable
{
    /**
     * Create an node with a specific <i>id</i> in <i>graph</i>.
     * @param id Unique ID of the node
     * @param graph Graph which own the node
     */
    public Node(Integer id, Graph graph)
    {
        this.id = id;
        this.graph = graph;
        graph.addNode(this);
    }
    /**
     * Create a new node in <i>graph</i>.
     * @param graph Graph which own the node
     */
    public Node(Graph graph)
    {
        this.id = graph.getUID();
        this.graph = graph;
        graph.addNode(this);
    }
    
    /**
     * List of edges.
     */
    private final List<Edge> edges = new ArrayList<>();
    /**
     * ID of the node.
     */
    private final Integer id;
    /**
     * Graph which own the node.
     */
    private final Graph graph;
    
    /**
     * Add a new edge
     * @param e Edge to add
     */
    Boolean addEdge(Edge e)
    {
        if(!this.equals(e.getStartNode()) && !this.equals(e.getStopNode()))
            return false;
        
        edges.add(e);
        return true;
    }
    
    /**
     * Get the edges linked to the current node.
     * @return List of Edge
     */
    public List<Edge> getEdges()
    {
        return edges;
    }
    
    /**
     * Get the ID of the current node.
     * @return Integer
     */
    public Integer getId()
    {
        return id;
    }
    
    /**
     * Get the list of following nodes.
     * @return List of Node
     */
    public List<Node> getNextNodes()
    {
        return edges.stream()
                .filter(e -> this.equals(e.getStartNode()))
                .map(e -> e.getStopNode())
                .collect(Collectors.toList());
    }
    
    /**
     * Return the edges linked to the node <i>n</i>. <i>n</i> has to be a
     * following node of the current one. If <i>n</i> is not a following node,
     * the method will return an empty list.
     * @param n Following Node
     * @return List of Edge
     */
    public List<Edge> getEdges(Node n)
    {
        return edges.stream()
                .filter(e -> this.equals(e.getStartNode()))
                .filter(e -> n.equals(e.getStopNode()))
                .collect(Collectors.toList());
    }
    
    /**
     * Return the graph which own the current node.
     * @return Graph
     */
    public Graph getGraph()
    {
        return graph;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this.getClass().equals(obj.getClass()))
            return obj.hashCode() == this.hashCode();
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }
    

    @Override
    public Element toXML(Document elementBuilder)
    {
        Element element = elementBuilder.createElement("node");
        element.setAttribute("id", getId().toString());
        return element;
    }
}
