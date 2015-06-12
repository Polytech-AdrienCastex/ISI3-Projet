/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.authorizer;

import model.SurfaceType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for class AuthSnapSnap.
 */
public class AuthSnapSnapTest {
    /**
     * Test of getAllowedSurfaceTypes method, of class AuthSnapSnap.
     */
    @Test
    public void testGetAllowedSurfaceTypes() {
        System.out.println("getAllowedSurfaceTypes");
        AuthSnapSnap instance = new AuthSnapSnap();
        SurfaceType[] expResult = new SurfaceType[] { SurfaceType.Plat };
        SurfaceType[] result = instance.getAllowedSurfaceTypes();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of canGoThroughWater method, of class AuthSnapSnap.
     */
    @Test
    public void testCanGoThroughWater() {
        System.out.println("canGoThroughWater");
        AuthSnapSnap instance = new AuthSnapSnap();
        Boolean expResult = false;
        Boolean result = instance.canGoThroughWater();
        assertEquals(expResult, result);
    }

    /**
     * Test of canGoThroughFire method, of class AuthSnapSnap.
     */
    @Test
    public void testCanGoThroughFire() {
        System.out.println("canGoThroughFire");
        AuthSnapSnap instance = new AuthSnapSnap();
        Boolean expResult = false;
        Boolean result = instance.canGoThroughFire();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class AuthSnapSnap.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AuthSnapSnap instance = new AuthSnapSnap();
        String expResult = "Snap snap";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
