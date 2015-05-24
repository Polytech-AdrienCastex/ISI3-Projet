package sources.model.robot;

import java.util.LinkedList;
import java.util.List;
import sources.model.graph.Edge;
import sources.model.elementary.Fireable;
import sources.model.graph.Node;
import sources.model.elementary.Valued;
import sources.model.pathfinding.Authorizer;
import sources.model.pathfinding.PathFinding;

/**
 * Robot general
 */
public abstract class Robot implements Authorizer, Runnable {
    private Double speed;
    private Node currentNode;
    private LinkedList<Edge> path;
    
    private PathFinding pathFinding;

    /**
     * Constructor
     * @param speed speed
     * @param currentNode current node where the robot is
     * @param pf Search path algo
     */
    public Robot(Double speed, Node currentNode, PathFinding pf) {
        this.speed = speed;
        this.currentNode = currentNode;
        this.pathFinding = pf;
    }

    /**
     * Getter current node
     * @return current Node
     */
    public Node getCurrentNode() {
        return currentNode;
    }

    /**
     * Setter current node
     * @param currentNode current node to set
     */
    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    /**
     * Getter speed
     * @return speed double
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * Setter speed
     * @param speed double speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * Getter algo path finding
     * @return PathFinding
     */
    public PathFinding getPathFinding() {
        return pathFinding;
    }

    /**
     * Setter algo path finding
     * @param pathFinding algo path finding
     */
    public void setPathFinding(PathFinding pathFinding) {
        this.pathFinding = pathFinding;
    }   
       
    /**
     * Return the value of the best path depending on the dest node
     * @param dest Dest node
     * @return value of the best path
     */
    public Double getPathValue(Node dest)
    {                                
        List<Edge> path = pathFinding.getShortestPath(currentNode, dest, this);
        Double value = 0.0;
        
        for (Edge e : path)
        {
            if (e instanceof Valued) 
            {
                Valued v = (Valued)e;
                value += (v.getValue() * speed);
            }
        }       
        
        return value;
    }
    
    /**
     * The robot is busy when he didnt reach his destination yet or  
     * he has a destination and is on a node on fire
     * @return true if the robot is busy
     */
    public Boolean isBusy() {
        if (path.size() > 0)
        {
            if (!path.get(path.size()-1).getStopNode().equals(currentNode))
                return true; //Le robot n'a pas atteint sa destination
                        
            if (currentNode instanceof Fireable)
            {
                Fireable fNode = (Fireable)currentNode;
                if (fNode.isOnFire())
                    return true; //Noeud toujours en feu
            }            
        }
        
        return  false;            
    }
    
    /**
     * Define the destination of the robot from the dest node
     * @param dest Destination node 
     */
    public void setDestination(Node dest)
    {
        this.path = new LinkedList<>(pathFinding.getShortestPath(currentNode, dest, this));
    }
    
    /**
     * Move forward to the next node in the path list
     */
    public void moveForward() {
        if (path.size() > 0)
        {
            Edge nextEdge = path.remove(0);
            
            if (canUseEdge(nextEdge) && canUseNode(nextEdge.getStopNode()))
            {
                currentNode = nextEdge.getStopNode();
            } else {
                //Vider la destination
                path.clear();
            }
        }
            
    }

    @Override
    public void run() {
        //Si robot sur noeud en feu .. eteindre le feu (baisser intensité)
        
        //sinon si destination avancer
    }
}