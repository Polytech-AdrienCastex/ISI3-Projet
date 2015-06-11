/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.authorizer;

import model.SurfaceType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Necrolight
 */
public class Auth4x4Test {
    
    public Auth4x4Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
