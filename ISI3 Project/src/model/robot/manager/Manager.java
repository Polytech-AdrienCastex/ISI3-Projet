package model.robot.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import model.Observable;
import model.graph.Graph;
import model.robot.Robot;

/**
 * Robots Manager
 * @param <R> : Type of robot
 */
public abstract class Manager<R extends Robot> extends Observable implements Observer, Runnable {
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
        r.addObserver(this);
    }
    
    /**
     * Remove a robot from the manager
     * @param r Robot to remove
     */
    public void removeRobot(R r)
    {
        robots.remove(r);
        r.deleteObserver(this);
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
    
    @Override
    public void update(java.util.Observable o, Object arg)
    {
        notifyChanges();
    }
}