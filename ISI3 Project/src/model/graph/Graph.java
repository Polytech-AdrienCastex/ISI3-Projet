package model.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Observer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import model.Observable;
import model.xml.Serializable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 */
public class Graph extends Observable implements Serializable, Observer
{
    /**
     * Create an empty graph
     */
    public Graph()
    {
        nodes = new ConcurrentLinkedQueue<>();
        
        uid = 0;
    }
    
    /**
     * List of nodes owned by the current graph
     */
    private final Collection<Node> nodes;
    
    /**
     * Curretn unique ID for the current graph
     */
    private Integer uid;
    /**
     * Return a unique ID for the current graph
     * @return Integer
     */
    Integer getUID()
    {
        uid++;
        return uid;
    }
    
    /**
     * Return the list of the nodes in the graph
     * @return List of nodes
     */
    public Collection<Node> getNodes()
    {
        return nodes;
    }
    
    /**
     * Add a node in the current graph
     * @param n Node to add
     * @return If the adding successed
     */
    public Boolean addNode(Node n)
    {
        Integer nid = n.getId();
        if(uid < nid)
            uid = nid;
        
        n.addObserver(this);
        
        Boolean result = nodes.add(n);
        notifyChanges();
        return result;
    }
    
    /**
     * Remove a node in the current graph
     * @param n Node to remove
     * @return If the removing successed
     */
    public Boolean removeNode(Node n)
    {
        List<Edge> edges = new ArrayList<>(n.getEdges());
        edges.forEach(e -> e.getStopNode().removeEdge(e));
        edges.forEach(e -> e.getStartNode().removeEdge(e));
        n.deleteObserver(this);

        Boolean result = nodes.remove(n);
        notifyChanges();
        return result;
    }
    
    public List<Edge> getEdges()
    {
        return getNodes().stream()
                .flatMap(n -> n.getEdges().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Element toXML(Document elementBuilder)
    {
        Element element = elementBuilder.createElement("osm");
        
        getNodes().forEach(n -> element.appendChild(n.toXML(elementBuilder)));
        getEdges().forEach(e -> element.appendChild(e.toXML(elementBuilder)));
        
        return element;
    }

    @Override
    public void update(java.util.Observable o, Object o1)
    {
        notifyChanges();
    }
}
