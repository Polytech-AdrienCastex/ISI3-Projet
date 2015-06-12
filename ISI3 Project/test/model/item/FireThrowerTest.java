/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import model.elementary.Fireable;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 * Tests for class FireThrower.
 */
public class FireThrowerTest {

    /**
     * Test of actionEdge method, of class FireThrower.
     */
    @Test
    public void testActionEdge() {
        System.out.println("actionEdge");
        FireThrower ft = new FireThrower(0.0);
        Edge e = Mockito.mock(Edge.class);
        
        ft.actionEdge(e);
    }

    /**
     * To test action on node
     */
    private abstract class NodeFireable extends Node implements Fireable
    {        
        public NodeFireable(Integer id, Graph graph) {
            super(id, graph);
        }

        public NodeFireable(Graph graph) {
            super(graph);
        }
    }
    
    /**
     * Test of actionNode method, of class FireThrower.
     */
    @Test
    public void testActionNode() {
        System.out.println("actionNode");
      
        NodeFireable nf = Mockito.mock(NodeFireable.class);
        FireThrower ft = new FireThrower(0.0);
        
        //node on fire
        Mockito.when(nf.isOnFire()).thenReturn(Boolean.TRUE);
        assertEquals(false, ft.actionNode(nf));
        
        //node not on fire
        // Mockito.when(nf.isOnFire()).thenReturn(Boolean.FALSE);
        // assertEquals(false, ft.actionNode(nf));
    }

    /**
     * Test of canUse method, of class FireThrower.
     */
    @Test
    public void testCanUse() {
        System.out.println("canUse");
        NodeFireable nf = Mockito.mock(NodeFireable.class);
        FireThrower fh = new FireThrower(0.0);
        
        //node on fire
        Mockito.when(nf.isOnFire()).thenReturn(Boolean.TRUE);
        assertEquals(false, fh.canUse(nf));
        
        //node not on fire
        Mockito.when(nf.isOnFire()).thenReturn(Boolean.FALSE);
        assertEquals(true, fh.canUse(nf));
        
        //node not fireable
        Node n = Mockito.mock(Node.class);
        assertEquals(false, fh.canUse(n));
    }

    /**
     * Test of toString method, of class FireThrower.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        FireThrower instance = new FireThrower(0.0);
        String expResult = "Fire thrower";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
