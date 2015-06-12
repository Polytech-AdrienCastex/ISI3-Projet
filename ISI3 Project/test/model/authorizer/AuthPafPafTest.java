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
 * Tests for class AuthPafPaf.
 */
public class AuthPafPafTest {
    /**
     * Test of getAllowedSurfaceTypes method, of class AuthPafPaf.
     */
    @Test
    public void testGetAllowedSurfaceTypes() {
        System.out.println("getAllowedSurfaceTypes");
        AuthPafPaf instance = new AuthPafPaf();
        SurfaceType[] expResult = new SurfaceType[] { SurfaceType.Plat, SurfaceType.Escarpe };
        SurfaceType[] result = instance.getAllowedSurfaceTypes();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of canGoThroughWater method, of class AuthPafPaf.
     */
    @Test
    public void testCanGoThroughWater() {
        System.out.println("canGoThroughWater");
        AuthPafPaf instance = new AuthPafPaf();
        Boolean expResult = false;
        Boolean result = instance.canGoThroughWater();
        assertEquals(expResult, result);
    }

    /**
     * Test of canGoThroughFire method, of class AuthPafPaf.
     */
    @Test
    public void testCanGoThroughFire() {
        System.out.println("canGoThroughFire");
        AuthPafPaf instance = new AuthPafPaf();
        Boolean expResult = false;
        Boolean result = instance.canGoThroughFire();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class AuthPafPaf.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AuthPafPaf instance = new AuthPafPaf();
        String expResult = "Paf paf";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
