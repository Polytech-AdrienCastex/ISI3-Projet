/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.robot.manager;

import java.util.ArrayList;
import java.util.Collection;
import model.graph.Graph;
import model.graph.Node;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 * Tests for class FireFighterManager.
 */
public class FireFighterManagerTest {
    /**
     * Test of searchNotAffectedNodes method, of class FireFighterManager.
     */
    @Test
    public void testSearchNotAffectedNodes() {
        System.out.println("FireFighterManager - searchNotAffectedNodes");
        boolean mustBeNotOccuped = false;
        FireFighterManager instance = new FireFighterManager(Mockito.mock(Graph.class));
        
        Collection<Node> expResult = new ArrayList<>();
        Collection<Node> result = instance.searchNotAffectedNodes(mustBeNotOccuped);
        assertEquals(expResult, result);
    }

    /**
     * Test of run method, of class FireFighterManager.
     */
    @Test
    public void testRun() {
        System.out.println("FireFighterManager - run");
        FireFighterManager instance = new FireFighterManager(new Graph());
        instance.run();
    }

    /**
     * Test of toString method, of class FireFighterManager.
     */
    @Test
    public void testToString() {
        System.out.println("FireFighterManager - toString");
        FireFighterManager instance = new FireFighterManager(null);
        String expResult = "Fire fighter";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
