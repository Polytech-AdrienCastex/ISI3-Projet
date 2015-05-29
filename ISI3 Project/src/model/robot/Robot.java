package model.robot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import model.Observable;
import model.ObservableCollection;
import model.elementary.Fireable;
import model.graph.Edge;
import model.graph.Node;
import model.elementary.Valued;
import model.item.IItem;
import model.authorizer.Authorizer;
import model.pathfinding.PathFinding;

/**
 * Robot general
 */
public abstract class Robot<I extends IItem> extends Observable implements Runnable
{
    protected Double speed;
    protected Node currentNode;
    protected List<I> items;
    
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
    public List<I> getItems()
    {
        return items;
    }

    /**
     * Set a list of items for the robot
     * @param items 
     */
    public void setItems(List<I> items)
    {
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
        Collection<Edge> path = pathFinding.getShortestPath(currentNode, dest, type);
        
        if(path.isEmpty())
            return -1.0;
        
        return path.stream()
                .filter(e -> e instanceof Valued)
                .mapToDouble(e -> ((Valued)e).getValue() * speed)
                .sum();
    }
    
    /**
     * The robot is busy when he didnt reach his destination yet 
     * @return true if the robot is busy
     */
    public Boolean isBusy()
    {
        return path.size() > 0 || (currentNode instanceof Fireable && ((Fireable)currentNode).isOnFire());            
    }
    
    
    private Node dest;
    
    /**
     * Define the destination of the robot from the dest node
     * @param dest Destination node 
     */
    public void setDestination(Node dest)
    {
        this.dest = dest;
        this.path = new LinkedList<>(pathFinding.getShortestPath(currentNode, dest, type));
    }

    public Node getDestination()
    {
        if(this.path.isEmpty())
            return null;
        
        return dest;
    }
    
    /**
     * Move forward to the next node in the path list
     */
    public void moveForward()
    {
        if (path.size() > 0)
        {
            Edge nextEdge = path.remove(0);
            Node nextNode = nextEdge.getStopNode().equals(currentNode) ? nextEdge.getStartNode(): nextEdge.getStopNode();
            
            if ((nextEdge.getStartNode().equals(currentNode) || nextEdge.getStopNode().equals(currentNode)) && type.canUseEdge(nextEdge) && (path.size() == 0 || type.canUseNode(nextNode)))
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
    public void run()
    {
        moveForward();    
        
        //Items action
        items.stream()
                .filter(i -> i.actionNode(currentNode))
                .forEach(i ->
                        currentNode
                        .getEdges()
                        .forEach(e -> i.actionEdge(e)));
                           
        notifyChanges();
    }
    
    
    private static ObservableCollection<Robot> observableRobots = null;
    public static ObservableCollection<Robot> getRobotList()
    {
        if(observableRobots == null)
            observableRobots = new ObservableCollection(new ArrayList<>());
        return observableRobots;
    }
    protected static void addNewRobot(Robot robot)
    {
        getRobotList().add(robot);
    }
    protected static void removeRobot(Robot robot)
    {
        getRobotList().remove(robot);
    }
    protected static void clearRobots()
    {
        getRobotList().clear();
    }
}
