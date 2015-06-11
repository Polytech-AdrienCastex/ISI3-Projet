package model.robot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import model.Observable;
import model.ObservableCollection;
import model.graph.Edge;
import model.graph.Node;
import model.elementary.Valued;
import model.item.IItem;
import model.authorizer.Authorizer;
import model.pathfinding.PathFinding;

/**
 * Robot general
 */
public class Robot extends Observable implements Runnable
{
    /**
     * Speed 
     */
    protected Double speed;
    /**
     * Current node where this robot is.
     */
    protected Node currentNode;
    /**
     * List of items available on this robots.
     */
    protected List<IItem> items;
    
    private PathFinding pathFinding;
    private final Authorizer type;
    private LinkedList<Edge> path = new LinkedList<>();

    /**
     * Constructor
     * @param speed speed
     * @param currentNode current node where the robot is
     * @param pf Search path algo
     * @param typeRobot Authorizer type robot
     */
    public Robot(Double speed, Node currentNode, PathFinding pf, Authorizer typeRobot)
    {
        this.speed = speed;
        this.currentNode = currentNode;
        this.pathFinding = pf;
        this.items = new ArrayList<>();
        this.type = typeRobot;
        
        addNewRobot(this);
    }

    /**
     * Get the type of the robot (Authorizer)
     * @return Authorizer type of robot
     */
    public Authorizer getType() {
        return type;
    }
    

    /**
     * Get the robot items
     * @return 
     */
    public List<IItem> getItems()
    {
        return items;
    }

    /**
     * Set a list of items for the robot
     * @param items 
     */
    public void setItems(List<IItem> items)
    {
        this.items = items;
    }
    
    /**
     * Add an item to the robot
     * @param i Item to add
     */
    public void addItem(IItem i)
    {
        if (!items.contains(i))
            items.add(i);
    }
    
    /**
     * Remove the item in parameter from the robot
     * @param i Item to remove
     * @return true if item has be removed
     */
    public boolean removeItem(IItem i)
    {
        if (i != null)
            return items.remove(i);
        return false;
    }

    /**
     * Getter current node
     * @return current Node
     */
    public Node getCurrentNode()
    {
        return currentNode;
    }

    /**
     * Setter current node
     * @param currentNode current node to set
     */
    public void setCurrentNode(Node currentNode)
    {
        this.currentNode = currentNode;
        notifyChanges();
    }

    /**
     * Getter speed
     * @return speed double
     */
    public Double getSpeed()
    {
        return speed;
    }

    /**
     * Setter speed
     * @param speed double speed
     */
    public void setSpeed(Double speed)
    {
        this.speed = speed;
    }

    /**
     * Getter algo path finding
     * @return PathFinding
     */
    public PathFinding getPathFinding()
    {
        return pathFinding;
    }

    /**
     * Setter algo path finding
     * @param pathFinding algo path finding
     */
    public void setPathFinding(PathFinding pathFinding)
    {
        this.pathFinding = pathFinding;
    }   
       
    /**
     * Return the value of the best path depending on the dest node
     * @param dest Dest node
     * @return value of the best path
     */
    public Double getPathValue(Node dest)
    {                    
        Collection<Edge> path = (pathFinding != null ? pathFinding.getShortestPath(currentNode, dest, type) : new ArrayList<>());
        
        if(path.isEmpty())
            return -1.0;
        
        return path.stream()
                .filter(e -> e instanceof Valued)
                .mapToDouble(e -> ((Valued)e).getValue() / speed)
                .sum();
    }
    
    /**
     * The robot is busy when he didnt reach his destination yet 
     * @return true if the robot is busy
     */
    public Boolean isBusy()
    {
        return path.size() > 0 || getDistanceLeft() < 1.0 || getItems().stream().allMatch(i -> i.canUse(currentNode));
    }
    
    
    private Node dest;
    
    /**
     * Define the destination of the robot from the dest node
     * @param dest Destination node 
     */
    public void setDestination(Node dest)
    {
        this.dest = dest;
        
        Collection<Edge> ce = (pathFinding != null ? pathFinding.getShortestPath(currentNode, dest, type) : new ArrayList<>());
        this.path = new LinkedList<>(ce);
    }

    /**
     * Get the node distination of this robot.
     * @return Node destination.
     */
    public Node getDestination()
    {
        if(this.path.isEmpty())
            return null;
        
        return dest;
    }
    
    /**
     * inital distance from the initial node to the next node.
     */
    protected double distanceInitial = 1;
    /**
     * Distance left to the next node from the current node.
     */
    protected double distanceLeft = 0;
    /**
     * Get distance left to the next node from the current node.
     * @return distance left
     */
    public double getDistanceLeft()
    {
        return (distanceInitial - distanceLeft) / (double)distanceInitial;
    }
    
    /**
     * Last node in the current path.
     */
    protected Node lastNode = null;
    /**
     * Get the last node in the current path.
     * @return last node in the current path.
     */
    public Node getLastNode()
    {
        return lastNode;
    }
    
    /**
     * Move forward to the next node in the path list
     */
    public void moveForward()
    {
        if (path.size() > 0 && distanceLeft == 0)
        {
            Edge nextEdge = path.remove(0);
            Node nextNode = nextEdge.getStopNode().equals(currentNode) ? nextEdge.getStartNode(): nextEdge.getStopNode();
            
            if ((nextEdge.getStartNode().equals(currentNode) || nextEdge.getStopNode().equals(currentNode)) &&
                    type.canUseEdge(nextEdge) &&
                    (path.size() == 0 || type.canUseNode(nextNode)))
            {
                if(nextEdge instanceof Valued)
                {
                    distanceInitial = ((Valued)nextEdge).getValue();
                    distanceLeft = distanceInitial;
                }
                
                lastNode = currentNode;
                currentNode = nextNode;
            }
            else
            {
                // Vider la destination
                path.clear();
            }
        }
        
        if(distanceLeft > 0)
        {
            if(distanceLeft <= getSpeed())
                distanceLeft = 0;
            else
                distanceLeft -= getSpeed();
        }
    }

    /**
     * Run action
     */
    @Override
    public void run()
    {
        moveForward();    
        
        //Items action
        if(distanceLeft == 0)
            items.stream()
                    .filter(i -> i.actionNode(currentNode))
                    .forEach(i ->
                            currentNode
                            .getEdges()
                            .forEach(e -> i.actionEdge(e)));
                           
        notifyChanges();
    }
    
    
    private static ObservableCollection<Robot> observableRobots = null;
    /**
     * Get the observable collection of robots for the whole application.
     * @return Collection of robots used in the application.
     */
    public static ObservableCollection<Robot> getRobotList()
    {
        if(observableRobots == null)
            observableRobots = new ObservableCollection(new ArrayList<>());
        return observableRobots;
    }
    /**
     * Add existing robot to the application observable collection of robots.
     * @param robot Existing robot to add.
     */
    protected static void addNewRobot(Robot robot)
    {
        getRobotList().add(robot);
    }
    
    /**
     * Remove a robot from the application observable collection of robots. 
     * @param robot Robot to delete.
     */
    protected static void removeRobot(Robot robot)
    {
        getRobotList().remove(robot);
    }
    
    /**
     * Clear the collection of robots of the application.
     */
    protected static void clearRobots()
    {
        getRobotList().clear();
    }
}
