/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.robot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import model.ObservableCollection;
import model.authorizer.Authorizer;
import model.graph.Graph;
import model.graph.Node;
import model.item.IItem;
import model.pathfinding.PathFinding;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 * Test for class Robot.
 */
public class RobotTest {    
    /**
     * Test of getType method, of class Robot.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Authorizer a = Mockito.mock(Authorizer.class);
        Robot instance = new Robot(0.0, null, null, a);
        
        assertEquals(a, instance.getType());
    }

    /**
     * Test of getItems method, of class Robot.
     */
    @Test
    public void testGetItems() {
        System.out.println("getItems");
        Robot instance = new Robot(Double.NaN, null, null, null);
        
        //no item
        List<IItem> expResult = new LinkedList<>();
        assertEquals(expResult, instance.getItems());
       
        //multiple
        IItem i = Mockito.mock(IItem.class);
        expResult.add(i);
        instance.addItem(i);
        
        assertEquals(expResult, instance.getItems());
    }

    /**
     * Test of setItems method, of class Robot.
     */
    @Test
    public void testSetItems() {
        System.out.println("setItems");
        
        List<IItem> items = new LinkedList<>();
        
        Robot instance = new Robot(Double.NaN, null, null, null);
        instance.setItems(items);
        
        assertEquals(items, instance.getItems());
    }

    /**
     * Test of addItem method, of class Robot.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        IItem i = Mockito.mock(IItem.class);
        
        Robot instance = new Robot(Double.NaN, null, null, null);
        assertEquals(instance.getItems().contains(i), false);
        
        instance.addItem(i);
        assertEquals(instance.getItems().get(instance.getItems().size()-1), i);
    }

    /**
     * Test of removeItem method, of class Robot.
     */
    @Test
    public void testRemoveItem() {
        System.out.println("removeItem");
        IItem i = Mockito.mock(IItem.class);
        
        Robot instance = new Robot(Double.NaN, null, null, null);
        instance.addItem(i);
        
        assertEquals(true, instance.removeItem(i));
    }

    /**
     * Test of getCurrentNode method, of class Robot.
     */
    @Test
    public void testGetCurrentNode() {
        System.out.println("getCurrentNode");
        Node n = Mockito.mock(Node.class);
        Robot instance = new Robot(Double.NaN, n, null, null);
        Node expResult = n;
        Node result = instance.getCurrentNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurrentNode method, of class Robot.
     */
    @Test
    public void testSetCurrentNode() {
        System.out.println("setCurrentNode");
        Node currentNode = Mockito.mock(Node.class);
        Robot instance = new Robot(Double.NaN, null, null, null);
        
        instance.setCurrentNode(currentNode);
        
        assertEquals(currentNode, instance.getCurrentNode());
    }

    /**
     * Test of getSpeed method, of class Robot.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        Robot instance = new Robot(1.0, null, null, null);
        Double expResult = 1.0;
        Double result = instance.getSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSpeed method, of class Robot.
     */
    @Test
    public void testSetSpeed() {
        System.out.println("setSpeed");
        Double speed = 1.0;
        Robot instance = new Robot(0.0, null, null, null);
        instance.setSpeed(speed);
       
        assertEquals(speed, instance.getSpeed());
    }

    /**
     * Test of getPathFinding method, of class Robot.
     */
    @Test
    public void testGetPathFinding() {
        System.out.println("getPathFinding");
        PathFinding pf = Mockito.mock(PathFinding.class);
        
        Robot instance = new Robot(Double.NaN, null, pf, null);
        PathFinding expResult = pf;
        PathFinding result = instance.getPathFinding();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPathFinding method, of class Robot.
     */
    @Test
    public void testSetPathFinding() {
        System.out.println("setPathFinding");
        PathFinding pathFinding = Mockito.mock(PathFinding.class);
        Robot instance = new Robot(Double.NaN, null, null, null);
        instance.setPathFinding(pathFinding);
        
        assertEquals(pathFinding, instance.getPathFinding());
    }

    /**
     * Test of getPathValue method, of class Robot.
     */
    @Test
    public void testGetPathValue() {
        System.out.println("getPathValue");
        Node dest = null;
        Robot instance = new Robot(Double.NaN, dest, null, null);
        Double expResult = -1.0;
        Double result = instance.getPathValue(dest);
        assertEquals(expResult, result);
    }

    /**
     * Test of isBusy method, of class Robot.
     */
    @Test
    public void testIsBusy() {
        System.out.println("isBusy");
        
        Robot instance = new Robot(Double.NaN, null, null, null);
        
        assertEquals(true, instance.isBusy());
    }

    /**
     * Test of setDestination method, of class Robot.
     */
    @Test
    public void testSetDestination() {
        System.out.println("setDestination");
        
        Node dest = Mockito.mock(Node.class);
        
        Robot instance = new Robot(Double.NaN, null, null, null);
        instance.setDestination(dest);
        
        assertEquals(null, instance.getDestination());
    }

    /**
     * Test of getDestination method, of class Robot.
     */
    @Test
    public void testGetDestination() {
        System.out.println("getDestination");
        Robot instance = new Robot(Double.NaN, null, null, null);
       
        Node expResult = null;
        Node result = instance.getDestination();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDistanceLeft method, of class Robot.
     */
    @Test
    public void testGetDistanceLeft() {
        System.out.println("getDistanceLeft");
        Robot instance = new Robot(Double.NaN, null, null, null);
        double expResult = 1.0;
        double result = instance.getDistanceLeft();
        
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLastNode method, of class Robot.
     */
    @Test
    public void testGetLastNode() {
        System.out.println("getLastNode");
        Robot instance = new Robot(Double.NaN, null, null, null);
        
        assertEquals(null, instance.getLastNode());
    }

    /**
     * Test of moveForward method, of class Robot.
     */
    @Test
    public void testMoveForward() {
        System.out.println("moveForward");
        Robot instance = new Robot(Double.NaN, null, null, null);
        instance.moveForward();
    }

    /**
     * Test of run method, of class Robot.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        Robot instance = new Robot(Double.NaN, null, null, null);
        instance.run();
    }

    /**
     * Test of addNewRobot method, of class Robot.
     */
    @Test
    public void testAddNewRobot() {
        System.out.println("addNewRobot");
        Robot robot = new Robot(Double.NaN, null, null, null);
        Robot.addNewRobot(robot);
        
        assertEquals(Robot.getRobotList().contains(robot), true);
    }

    /**
     * Test of removeRobot method, of class Robot.
     */
    @Test
    public void testRemoveRobot() {
        System.out.println("removeRobot");
        Robot robot = new Robot(Double.NaN, null, null, null);
        Robot.addNewRobot(robot);
        Robot.removeRobot(robot);
        
        assertEquals(Robot.getRobotList().contains(robot), true);
    }

    /**
     * Test of clearRobots method, of class Robot.
     */
    @Test
    public void testClearRobots() {
        System.out.println("clearRobots");
        Robot.clearRobots();
        
        assertEquals(0, Robot.getRobotList().size());
    }
    
}
