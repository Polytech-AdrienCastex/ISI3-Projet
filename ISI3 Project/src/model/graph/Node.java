package model.graph;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Observable;
import model.xml.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents a node in a graph.
 */
public class Node extends Observable implements Serializable
{
    /**
     * Create an node with a specific <i>id</i> in <i>graph</i>.
     * @param id Unique ID of the node.
     * @param graph Graph which own the node.
     */
    public Node(Integer id, Graph graph)
    {
        this.id = id;
        this.graph = graph;
        graph.addNode(this);
    }
    /**
     * Create a new node in <i>graph</i>.
     * @param graph Graph which own the node.
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
    private final Collection<Edge> edges = new ConcurrentLinkedQueue<>();
    /**
     * ID of the node.
     */
    private final Integer id;
    /**
     * Graph which own the node.
     */
    private final Graph graph;
    
    /**
     * Add the new edge <b>e</b>.
     * @deprecated Reserved for internal use.
     * @param e Edge to add.
     * @return true if the edge was successfully added.
     */
    Boolean addEdge(Edge e)
    {
        if(!this.equals(e.getStartNode()) && !this.equals(e.getStopNode()))
            return false;

        edges.add(e);

        notifyChanges();
        return true;
    }
    
    /**
     * Remove the edge <b>e</b> from this node.
     * <p>
     * It doesn't remove the edge from the other node linked through the edge
     * <b>e</b>.
     * @deprecated Reserved for internal use.
     * @param e Edge to remove.
     * @return true if the edge was successfully removed.
     */
    Boolean removeEdge(Edge e)
    {
        if(!edges.contains(e))
            return false;
        edges.remove(e);

        notifyChanges();
        return true;
    }
    
    /**
     * Get the edges linked to the current node.
     * @return Collection of Edge.
     */
    public Collection<Edge> getEdges()
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
     * Get the collection of following nodes.
     * @return Collection of Node.
     */
    public List<Node> getNextNodes()
    {
        return edges.stream()
                .map(e -> this.equals(e.getStartNode()) ? e.getStopNode() : e.getStartNode())
                .collect(Collectors.toList());
    }
    
    /**
     * Return the edges linked to the node <i>n</i>. <i>n</i> has to be a
     * following node of the current one. If <i>n</i> is not a following node,
     * the method will return an empty list.
     * @param n Following Node.
     * @return Collection of Edge.
     */
    public List<Edge> getEdges(Node n)
    {
        return Stream.concat(
                edges.stream()
                .filter(e -> this.equals(e.getStartNode()))
                .filter(e -> n.equals(e.getStopNode())),
                edges.stream()
                .filter(e -> this.equals(e.getStopNode()))
                .filter(e -> n.equals(e.getStartNode())))
                .collect(Collectors.toList());
    }
    
    /**
     * Return the graph which own the current node.
     * @return Graph associated to the node.
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

    @Override
    public String toString()
    {
        return "{" + this.getId() + "}";
    }
}
