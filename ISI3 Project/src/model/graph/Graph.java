package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author p1002239
 */
public class Graph
{
    public Graph()
    {
        nodes = new ArrayList<>();
    }
    
    private final List<Node> nodes;
    
    public List<Node> getNodes()
    {
        return nodes;
    }
    
    public Boolean addNode(Node n)
    {
        return nodes.add(n);
    }
    
    public Boolean removeNode(Node n)
    {
        return nodes.remove(n);
    }
}
