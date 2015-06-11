/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.elementary.Fireable;
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
 *
 * @author Necrolight
 */
public class FireHoseTest {
    
    public FireHoseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * To test action on edge
     */
    public abstract class EdgeWaterable extends Edge implements Waterable
    {
        public EdgeWaterable(Node start, Node end) {
            super(start, end);
        }   
    }
    
    /**
     * Test of actionEdge method, of class FireHose.
     */
    @Test
    public void testActionEdge() {
        System.out.println("actionEdge");
        FireHose fh = new FireHose(0.0);
        
        //edge waterable
        //under water
        EdgeWaterable ew = Mockito.mock(EdgeWaterable.class);
        Mockito.when(ew.isUnderWater()).thenReturn(Boolean.TRUE);
        fh.actionEdge(ew);
        assertEquals(true, ew.isUnderWater());
        
        //not under water
        //Mockito.when(ew.isUnderWater()).thenReturn(Boolean.FALSE);
        // fh.actionEdge(ew);
        //assertEquals(false, ew.isUnderWater()); //TEST PROBABILITY

            
        //normal edge
        Edge e = Mockito.mock(Edge.class);
        fh.actionEdge(e);
    }

    /**
     * To test action on node
     */
    public abstract class NodeFireable extends Node implements Fireable
    {        
        public NodeFireable(Integer id, Graph graph) {
            super(id, graph);
        }

        public NodeFireable(Graph graph) {
            super(graph);
        }
    }
    
    /**
     * Test of actionNode method, of class FireHose.
     */
    @Test
    public void testActionNode() {
        System.out.println("actionNode");
      
        NodeFireable nf = Mockito.mock(NodeFireable.class);
        FireHose fh = new FireHose(0.0);
        
        //node on fire
        Mockito.when(nf.isOnFire()).thenReturn(Boolean.TRUE);
        assertEquals(true, fh.actionNode(nf));
        
        //node not on fire
        Mockito.when(nf.isOnFire()).thenReturn(Boolean.FALSE);
        assertEquals(false, fh.actionNode(nf));
    }

    /**
     * Test of canUse method, of class FireHose.
     */
    @Test
    public void testCanUse() {
        System.out.println("canUse");
        NodeFireable nf = Mockito.mock(NodeFireable.class);
        FireHose fh = new FireHose(0.0);
        
        //node on fire
        Mockito.when(nf.isOnFire()).thenReturn(Boolean.TRUE);
        assertEquals(true, fh.canUse(nf));
        
        //node not on fire
        Mockito.when(nf.isOnFire()).thenReturn(Boolean.FALSE);
        assertEquals(false, fh.canUse(nf));
        
        //node not fireable
        Node n = Mockito.mock(Node.class);
        assertEquals(false, fh.canUse(n));
    }

    /**
     * Test of toString method, of class FireHose.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        FireHose instance = new FireHose(0.0);
        String expResult = "Fire hose";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
