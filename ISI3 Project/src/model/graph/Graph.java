package model.graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Graph
{
    /**
     * Create an empty graph
     */
    public Graph()
    {
        nodes = new ArrayList<>();
        
        uid = 0;
    }
    
    /**
     * List of nodes owned by the current graph
     */
    private final List<Node> nodes;
    
    /**
     * Curretn unique ID for the current graph
     */
    private Integer uid;
    /**
     * Return a unique ID for the current graph
     * @return Integer
     */
    public Integer getUID()
    {
        uid++;
        return uid;
    }
    
    /**
     * Return the list of the nodes in the graph
     * @return List of nodes
     */
    public List<Node> getNodes()
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
        return nodes.add(n);
    }
    
    /**
     * Remove a node in the current graph
     * @param n Node to remove
     * @return If the removing successed
     */
    public Boolean removeNode(Node n)
    {
        return nodes.remove(n);
    }
}
