package model.graph;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class GraphTest
{
    public GraphTest()
    { }
    
    @BeforeClass
    public static void setUpClass()
    { }
    
    @AfterClass
    public static void tearDownClass()
    { }
    
    @Before
    public void setUp()
    { }
    
    @After
    public void tearDown()
    { }

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
        
        List<Node> result = instance.getNodes();
        
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
        
        List<Node> ns = instance.getNodes();
        
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
        
        List<Node> ns = instance.getNodes();
        
        assertArrayEquals(new Node[] { n1, n3 }, ns.toArray());
    }
}
