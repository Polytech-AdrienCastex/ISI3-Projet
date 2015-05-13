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
        
        uid = 0;
    }
    
    private final List<Node> nodes;
    private Integer uid;
    public Integer getUID()
    {
        uid++;
        return uid;
    }
    
    public List<Node> getNodes()
    {
        return nodes;
    }
    
    public Boolean addNode(Node n)
    {
        Integer nid = n.getId();
        if(uid < nid)
            uid = nid;
        return nodes.add(n);
    }
    
    public Boolean removeNode(Node n)
    {
        return nodes.remove(n);
    }
}
