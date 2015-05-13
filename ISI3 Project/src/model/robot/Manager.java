package model.robot;

import java.util.ArrayList;
import java.util.List;
import model.graph.Graph;

/**
 * Robots Manager
 */
public class Manager {
    private Graph grap;
    private final List<Robot> robots;

    public Manager(Graph grap) {
        this.grap = grap;
        this.robots = new ArrayList<>();
    }
    
    public void addRobot(Robot r)
    {
        robots.add(r);
    }
    
    public void removeRobot(Robot r)
    {
        robots.remove(r);
    }

    public Graph getGrap() {
        return grap;
    }

    public void setGrap(Graph grap) {
        this.grap = grap;
    }
    
    
}
