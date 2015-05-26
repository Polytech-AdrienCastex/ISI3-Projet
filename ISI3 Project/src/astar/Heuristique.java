/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package astar;

import model.graph.project.FireableNode;

/**
 *
 * @author p1309208
 */
public interface Heuristique {
    public double getH(FireableNode dep, FireableNode arr); 
    
}
