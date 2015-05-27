package model.robot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.Observable;
import model.graph.Edge;
import model.graph.Node;
import model.elementary.Valued;
import model.item.IItem;
import model.pathfinding.Authorizer;
import model.pathfinding.PathFinding;

/**
 * Robot general
 */
public abstract class Robot<I extends IItem> extends Observable implements Authorizer, Runnable {
    protected Double speed;
    protected Node currentNode;
    protected List<I> items;
    
    private PathFinding pathFinding;
    private LinkedList<Edge> path = new LinkedList<>();

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
        this.items = new ArrayList<>();
    }

    /**
     * Get the robot items
     * @return 
     */
    public List<I> getItems() {
        return items;
    }

    /**
     * Set a list of items for the robot
     * @param items 
     */
    public void setItems(List<I> items) {
        this.items = items;
    }
    
    /**
     * Add an item to the robot
     * @param i Item to add
     */
    public void addItem(I i)
    {
        if (!items.contains(i))
            items.add(i);
    }
    
    /**
     * Remove the item in parameter from the robot
     * @param i Item to remove
     * @return true if item has be removed
     */
    public boolean removeItem(I i)
    {
        if (i != null)
            return items.remove(i);
        return false;
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
        notifyChanges();
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
     * The robot is busy when he didnt reach his destination yet 
     * @return true if the robot is busy
     */
    public Boolean isBusy() {
        if (path.size() > 0)
        {
            if (!path.get(path.size()-1).getStopNode().equals(currentNode))
                return true; //Le robot n'a pas atteint sa destination   
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
            
            Node nextNode = nextEdge.getStopNode().equals(currentNode) ? nextEdge.getStartNode(): nextEdge.getStopNode();
            if ((nextEdge.getStartNode().equals(currentNode) || nextEdge.getStopNode().equals(currentNode)) && canUseEdge(nextEdge) && canUseNode(nextNode))
            {
                currentNode = nextNode;
            } else {
                //Vider la destination
                path.clear();
            }
        }
    }

    /**
     * Run action
     */
    @Override
    public void run() {
        moveForward();    
        
        //Items action
        for (IItem i : items)
        {
            i.actionNode(currentNode);
            
            for (Edge e : currentNode.getEdges())
                i.actionEdge(e);
        }
                           
        notifyChanges();
    }
}
