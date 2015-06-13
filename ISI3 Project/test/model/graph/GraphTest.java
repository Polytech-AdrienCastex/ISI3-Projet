package model.graph;

import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for class Graph.
 */
public class GraphTest
{
    /**
     * Test of getUID method, of class Graph.
     */
    @Test
    public void testGetUID()
    {
        System.out.println("getUID");
        
        Graph instance = new Graph();
        
        Integer expResult = 1;
        Integer result = instance.getUID();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getNodes method, of class Graph.
     */
    @Test
    public void testGetNodes()
    {
        System.out.println("getNodes");
        
        Graph instance = new Graph();
        
        Node[] nodes = new Node[]
        {
            new Node(instance),
            new Node(instance),
            new Node(instance),
            new Node(instance),
            new Node(instance),
            new Node(instance),
            new Node(instance)
        };
        
        Collection<Node> result = instance.getNodes();
        
        assertArrayEquals(nodes, result.toArray());
    }

    /**
     * Test of addNode method, of class Graph.
     */
    @Test
    public void testAddNode()
    {
        System.out.println("addNode");
        
        Graph instance = new Graph();
        Node n = new Node(instance);
        
        Collection<Node> ns = instance.getNodes();
        
        assertArrayEquals(new Node[] { n }, ns.toArray());
    }

    /**
     * Test of removeNode method, of class Graph.
     */
    @Test
    public void testRemoveNode()
    {
        System.out.println("removeNode");
        
        Graph instance = new Graph();
        Node n1 = new Node(instance);
        Node n2 = new Node(instance);
        Node n3 = new Node(instance);
        
        instance.removeNode(n2);
        
        Collection<Node> ns = instance.getNodes();
        
        assertArrayEquals(new Node[] { n1, n3 }, ns.toArray());
    }
}
