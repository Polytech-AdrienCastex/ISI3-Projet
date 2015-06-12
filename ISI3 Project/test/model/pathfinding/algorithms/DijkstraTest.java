/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pathfinding.algorithms;

import model.pathfinding.PathFinding;

/**
 *
 * @author Adrien
 */
public class DijkstraTest extends ShortestPathTest
{
    @Override
    protected PathFinding getPathFinding()
    {
        return new Dijkstra();
    }
}
