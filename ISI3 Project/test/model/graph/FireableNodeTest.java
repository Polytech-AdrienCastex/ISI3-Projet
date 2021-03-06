package model.graph;

import model.graph.project.FireableNode;
import model.elementary.Point;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for class FireableNode.
 */
public class FireableNodeTest
{    
    /**
     * Set up
     */
    @Before
    public void setUp()
    {
        graph = new Graph();
    }

    private Graph graph;

    /**
     * Test of isOnFire method, of class FireableNode.
     */
    @Test
    public void testIsOnFire()
    {
        System.out.println("FireableNode - isOnFire");
        
        FireableNode fn = new FireableNode(graph, new Point(10.0, 5.3));
        
        assertFalse(fn.isOnFire());
        
        fn.setFireIntensity(9.7);
        
        assertTrue(fn.isOnFire());
        
        fn.setFireIntensity(-9.7);
        
        assertFalse(fn.isOnFire());
    }

    /**
     * Test of getFireIntensity method, of class FireableNode.
     */
    @Test
    public void testGetFireIntensity()
    {
        System.out.println("FireableNode - getFireIntensity");
        
        FireableNode fn = new FireableNode(graph, new Point(10.0, 5.3));
        
        assertEquals(0.0, fn.getFireIntensity(), 0.0001);
        
        fn.setFireIntensity(9.7);
        
        assertEquals(9.7, fn.getFireIntensity(), 0.0001);
    }

    /**
     * Test of setFireIntensity method, of class FireableNode.
     */
    @Test
    public void testSetFireIntensity()
    {
        System.out.println("FireableNode - setFireIntensity");
        
        FireableNode fn = new FireableNode(graph, new Point(10.0, 5.3));
        
        assertEquals(0.0, fn.getFireIntensity(), 0.0001);
        
        fn.setFireIntensity(9.7);
        
        assertEquals(9.7, fn.getFireIntensity(), 0.0001);
        
        fn.setFireIntensity(-9.7);
        
        assertEquals(0.0, fn.getFireIntensity(), 0.0001);
    }

    /**
     * Test of getLocation method, of class FireableNode.
     */
    @Test
    public void testGetLocation()
    {
        System.out.println("FireableNode - getLocation");
        
        FireableNode fn = new FireableNode(graph, new Point(10.0, 5.3));
        
        assertEquals(10.0, fn.getLocation().x, 0.0001);
        assertEquals(5.3, fn.getLocation().y, 0.0001);
    }
    
}
