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
 *  Tests for class AuthTank.
 */
public class AuthTankTest {
    /**
     * Test of getAllowedSurfaceTypes method, of class AuthTank.
     */
    @Test
    public void testGetAllowedSurfaceTypes() {
        System.out.println("AuthTank - getAllowedSurfaceTypes");
        AuthTank instance = new AuthTank();
        SurfaceType[] expResult = new SurfaceType[] {  SurfaceType.Plat, SurfaceType.Escarpe };
        SurfaceType[] result = instance.getAllowedSurfaceTypes();
        
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of canGoThroughWater method, of class AuthTank.
     */
    @Test
    public void testCanGoThroughWater() {
        System.out.println("AuthTank - canGoThroughWater");
        AuthTank instance = new AuthTank();
        Boolean expResult = true;
        Boolean result = instance.canGoThroughWater();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of canGoThroughFire method, of class AuthTank.
     */
    @Test
    public void testCanGoThroughFire() {
        System.out.println("AuthTank - canGoThroughFire");
        AuthTank instance = new AuthTank();
        Boolean expResult = true;
        Boolean result = instance.canGoThroughFire();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class AuthTank.
     */
    @Test
    public void testToString() {
        System.out.println("AuthTank - toString");
        AuthTank instance = new AuthTank();
        String expResult = "Tank";
        String result = instance.toString();
        
        assertEquals(expResult, result);
    }
    
}
