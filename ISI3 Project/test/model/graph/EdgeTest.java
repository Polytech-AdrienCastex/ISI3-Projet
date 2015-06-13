package model.graph;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for class Edge.
 */
public class EdgeTest
{
    /**
     * set up
     */
    @Before
    public void setUp()
    {
        Graph graph = new Graph();
        startNode = new Node(graph);
        endNode = new Node(graph);
        edge = new Edge(startNode, endNode);
    }
     
    private Edge edge;
    private Node startNode;
    private Node endNode;
    
    /**
     * test contructor.
     */
    @Test
    public void testConstructor()
    {
        System.out.println("constructor");
        
        assertArrayEquals(new Edge[] { edge }, startNode.getEdges().toArray());
        assertArrayEquals(new Edge[] { edge }, endNode.getEdges().toArray());
    }

    /**
     * Test of getStartNode method, of class Edge.
     */
    @Test
    public void testGetStartNode()
    {
        System.out.println("getStartNode");
        
        Node result = edge.getStartNode();
        assertEquals(startNode, result);
    }

    /**
     * Test of getStopNode method, of class Edge.
     */
    @Test
    public void testGetStopNode()
    {
        System.out.println("getStopNode");
        
        Node result = edge.getStopNode();
        assertEquals(endNode, result);
    }
    
}
