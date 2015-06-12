/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.authorizer;

import model.SurfaceType;
import model.elementary.Fireable;
import model.elementary.Typed;
import model.elementary.Waterable;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 * Test class for Auth4x4.
 */
public class Auth4x4Test {
    private abstract class  EdgeWaterable extends Edge implements Waterable
    {
        public EdgeWaterable(Node start, Node end) {
            super(start, end);
        }   
    }
    
    private abstract class  EdgeTyped extends Edge implements Typed
    {  
        public EdgeTyped(Node start, Node end) {
            super(start, end);
        }
    }
    
    /**
     * Test of canUseEdge method, of class Auth4x4.
     */
    @Test
    public void testCanUseEdge() {
        System.out.println("canUseEdge");
        Auth4x4 instance = new Auth4x4();
        
        //Edge not waterable not type = true
        Edge e = Mockito.mock(Edge.class);
        assertEquals(true, instance.canUseEdge(e));
        
        //Edge waterable
        EdgeWaterable ew = Mockito.mock(EdgeWaterable.class);
        Mockito.when(ew.isUnderWater()).thenReturn(Boolean.TRUE);
        assertEquals(true, instance.canUseEdge(ew));
        
        Mockito.when(ew.isUnderWater()).thenReturn(Boolean.FALSE);
        assertEquals(true, instance.canUseEdge(ew)); 
        
        //Edge valued
        EdgeTyped et = Mockito.mock(EdgeTyped.class);
        Mockito.when(et.getType()).thenReturn(SurfaceType.Plat);
        assertEquals(true, instance.canUseEdge(et));
    }
    
    private abstract class NodeFireable extends Node implements Fireable
    {
        public NodeFireable(Integer id, Graph graph) {
            super(id, graph);
        }
        
    }

    /**
     * Test of canUseNode method, of class Auth4x4.
     */
    @Test
    public void testCanUseNode() {
        System.out.println("canUseEdge");
        Auth4x4 instance = new Auth4x4();
        
        //Normal node
        Node n = Mockito.mock(Node.class);
        assertEquals(true, instance.canUseNode(n));
        
        //fireable node
        NodeFireable nf = Mockito.mock(NodeFireable.class);
        assertEquals(true, instance.canUseNode(nf));
        
        Mockito.when(nf.isOnFire()).thenReturn(Boolean.TRUE);
        assertEquals(false, instance.canUseNode(nf));
    }
    
    /**
     * Test of getAllowedSurfaceTypes method, of class Auth4x4.
     */
    @Test
    public void testGetAllowedSurfaceTypes() {
        System.out.println("getAllowedSurfaceTypes");
        Auth4x4 instance = new Auth4x4();
        SurfaceType[] expResult = new SurfaceType[] { SurfaceType.Plat, SurfaceType.Escarpe };
        SurfaceType[] result = instance.getAllowedSurfaceTypes();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of canGoThroughWater method, of class Auth4x4.
     */
    @Test
    public void testCanGoThroughWater() {
        System.out.println("canGoThroughWater");
        Auth4x4 instance = new Auth4x4();
        Boolean expResult = true;
        Boolean result = instance.canGoThroughWater();
        assertEquals(expResult, result);
    }

    /**
     * Test of canGoThroughFire method, of class Auth4x4.
     */
    @Test
    public void testCanGoThroughFire() {
        System.out.println("canGoThroughFire");
        Auth4x4 instance = new Auth4x4();
        Boolean expResult = false;
        Boolean result = instance.canGoThroughFire();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Auth4x4.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Auth4x4 instance = new Auth4x4();
        String expResult = "4x4";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
