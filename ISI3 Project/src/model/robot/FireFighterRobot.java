package model.robot;

import model.elementary.Fireable;
import model.graph.Node;
import model.item.FireHose;
import model.pathfinding.PathFinding;

public abstract class FireFighterRobot extends Robot<FireHose> {

    /**
     * Contructor : By default the FireFighter has 1 FireHose item
     * @param speed speed of the robot
     * @param currentNode currentNode
     * @param pf pathfinding algo
     * @param fh First FireHose to add
     */
    public FireFighterRobot(Double speed, Node currentNode, PathFinding pf, FireHose fh) {
        super(speed, currentNode, pf);
        
        this.addItem(fh); 
    }

    /**
     * Busy when he is on a node on fire
     * Or parent condition
     * @return 
     */
    @Override
    public Boolean isBusy() {
        if (currentNode instanceof Fireable)
        {
            Fireable fNode = (Fireable)currentNode;
            if (fNode.isOnFire())
                return true; //Noeud toujours en feu
        }  
        
        return super.isBusy(); 
    }
}
