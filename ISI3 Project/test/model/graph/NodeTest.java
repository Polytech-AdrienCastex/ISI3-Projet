package model.graph;

import java.util.Collection;
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
public class NodeTest
{
    
    public NodeTest()
    { }
    
    @BeforeClass
    public static void setUpClass()
    { }
    
    @AfterClass
    public static void tearDownClass()
    { }
    
    @Before
    public void setUp()
    {
        graph = new Graph();
    }
    
    @After
    public void tearDown()
    { }
    
    private Graph graph;

    /**
     * Test of addEdge method, of class Node.
     */
    @Test
    public void testAddEdge()
    {
        System.out.println("addEdge");
        
        Node n1 = new Node(graph);
        Node n2 = new Node(graph);
        
        Edge edge = new Edge(n1, n2);
        
        Collection<Edge> result = n1.getEdges();
        assertArrayEquals(new Edge[] { edge }, result.toArray());
    }

    /**
     * Test of getEdges method, of class Node.
     */
    @Test
    public void testGetEdges_0args()
    {
        System.out.println("getEdges");
        
        Collection<Edge> result;
        
        Node n1 = new Node(graph);
        Node n2 = new Node(graph);
        
        Edge edge = new Edge(n1, n2);
        
        result = n1.getEdges();
        assertArrayEquals(new Edge[] { edge }, result.toArray());
        
        result = n2.getEdges();
        assertArrayEquals(new Edge[] { edge }, result.toArray());
    }

    /**
     * Test of getId method, of class Node.
     */
    @Test
    public void testGetId()
    {
        System.out.println("getId");
        
        Node n1 = new Node(graph);
        Node n2 = new Node(graph);
        Node n3 = new Node(10, graph);
        
        assertEquals(1, (int)n1.getId());
        assertEquals(2, (int)n2.getId());
        assertEquals(10, (int)n3.getId());
    }

    /**
     * Test of getNextNodes method, of class Node.
     */
    @Test
    public void testGetNextNodes()
    {
        System.out.println("getNextNodes");
        
        List<Node> result;
        
        Node n1 = new Node(graph);
        Node n2 = new Node(graph);
        
        Edge edge = new Edge(n1, n2);
        
        result = n1.getNextNodes();
        assertArrayEquals(new Node[] { n2 }, result.toArray());
        
        result = n2.getNextNodes();
        assertArrayEquals(new Node[] { n1 }, result.toArray());
    }

    /**
     * Test of getEdges method, of class Node.
     */
    @Test
    public void testGetEdges_Node()
    {
        System.out.println("getEdges");
        
        List<Edge> result;
        
        Node n1 = new Node(graph);
        Node n2 = new Node(graph);
        Node n3 = new Node(graph);
        
        Edge edge1 = new Edge(n1, n2);
        Edge edge2 = new Edge(n1, n3);
        Edge edge3 = new Edge(n1, n3);
        
        result = n1.getEdges(n1);
        assertArrayEquals(new Edge[] { }, result.toArray());
        
        result = n1.getEdges(n2);
        assertArrayEquals(new Edge[] { edge1 }, result.toArray());
        
        result = n2.getEdges(n1);
        assertArrayEquals(new Edge[] { edge1 }, result.toArray());
        
        result = n1.getEdges(n3);
        assertArrayEquals(new Edge[] { edge2, edge3 }, result.toArray());
    }

    /**
     * Test of getGraph method, of class Node.
     */
    @Test
    public void testGetGraph()
    {
        System.out.println("getGraph");
        
        Node node = new Node(graph);
        
        assertEquals(graph, node.getGraph());
    }

    /**
     * Test of equals method, of class Node.
     */
    @Test
    public void testEquals()
    {
        System.out.println("equals");
        
        Node n1 = new Node(graph);
        Node n2 = new Node(graph);
        
        assertTrue(n1.equals(n1));
        assertTrue(n2.equals(n2));
        assertFalse(n1.equals(n2));
        assertFalse(n2.equals(n1));
    }

    /**
     * Test of hashCode method, of class Node.
     */
    @Test
    public void testHashCode()
    {
        System.out.println("hashCode");
        
        Node n1 = new Node(graph);
        Node n2 = new Node(graph);
        
        assertEquals(n1.hashCode(), n1.hashCode());
        assertNotSame(n2.hashCode(), n1.hashCode());
    }
    
}
