/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.robot.manager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Necrolight
 */
public class RobotRuntimeTest {
    
    public RobotRuntimeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of runtime method, of class RobotRuntime.
     */
    @Test
    public void testRuntime() {
        System.out.println("runtime");
        RobotRuntime instance = new RobotRuntime(Mockito.mock(Manager.class));
        instance.runtime();
    }
    
}
