package model.robot;

import java.util.List;
import java.util.Random;
import model.graph.Edge;
import model.graph.Node;
import model.graph.Valued;
import model.pathfinding.Authorizer;
import model.pathfinding.PathFinding;

/**
 * Robot general
 */
public abstract class Robot implements Authorizer {
    private Double speed;
    private Node currentNode;
    private List<Node> destination;
    
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
     * @return value of the best path, null if busy
     */
    public Double getPathValue(Node dest)
    {        
        if (isBusy()) return null;
                        
        List<Node> path = pathFinding.getShortestPath(currentNode, dest, this);
        Double value = 0.0;
        
        for (int i=0; i < path.size()-1; ++i)
        {
            //Rechercher chaque arrête du noeud par rapport au prochain noeud
            List<Edge> vEdges = path.get(i).getEdges(path.get(i+1));
            
            //Prendre la meilleure arrête
            double bestV = Double.MAX_VALUE;
            for (Edge e : vEdges)
            {
                if (e instanceof Valued) 
                { //Arrête valuée
                    Valued v = (Valued)e;
                    if (v.getValue() < bestV)
                        bestV = v.getValue();
                }
            }
            
            value += bestV;
        }
        
        return value;
    }
    
    /**
     * Robot busy or not
     * Random value
     * @return true if the robot is busy
     */
    public Boolean isBusy() {
        return new Random().nextBoolean();
    }
    
    /**
     * Define the destination of the robot from the dest node
     * @param dest Destination node 
     */
    public void setDestination(Node dest)
    {
        this.destination = pathFinding.getShortestPath(currentNode, dest, this);
    }
}
