package model.graph;

import model.graph.project.FireableNode;
import model.elementary.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class FireableNodeTest
{
    
    public FireableNodeTest()
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
     * Test of isOnFire method, of class FireableNode.
     */
    @Test
    public void testIsOnFire()
    {
        System.out.println("isOnFire");
        
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
        System.out.println("getFireIntensity");
        
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
        System.out.println("setFireIntensity");
        
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
        System.out.println("getLocation");
        
        FireableNode fn = new FireableNode(graph, new Point(10.0, 5.3));
        
        assertEquals(10.0, fn.getLocation().x, 0.0001);
        assertEquals(5.3, fn.getLocation().y, 0.0001);
    }
    
}
