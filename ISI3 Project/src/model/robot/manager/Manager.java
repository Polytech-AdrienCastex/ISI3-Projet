package model.robot.manager;

import java.util.ArrayList;
import java.util.List;
import model.graph.Graph;
import model.robot.Robot;

/**
 * Robots Manager
 * @param <R> : Type of robot
 */
public abstract class Manager<R extends Robot> implements Runnable {
    protected Graph grap;
    protected final List<R> robots;
           
    /**
     * Constructor
     * @param grap Graph used by the manager
     */
    public Manager(Graph grap) {
        this.grap = grap;
        this.robots = new ArrayList<>();
    }
    
    /**
     * Add a robot to the manager
     * @param r Robot to add
     */
    public void addRobot(R r)
    {
        robots.add(r);
    }
    
    /**
     * Remove a robot from the manager
     * @param r Robot to remove
     */
    public void removeRobot(R r)
    {
        robots.remove(r);
    }

    /**
     * Getter list robots
     * @return list robots
     */
    public List<R> getRobots() {
        return robots;
    }    
    
    /**
     * Get the graph associated with the manager
     * @return graph
     */
    public Graph getGrap() {
        return grap;
    }

    /**
     * Set the graph
     * @param grap Graph to add to the manager
     */
    public void setGrap(Graph grap) {
        this.grap = grap;
    }
}