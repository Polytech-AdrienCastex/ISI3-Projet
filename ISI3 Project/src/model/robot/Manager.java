package model.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.elementary.Fireable;
import model.graph.Graph;
import model.graph.Node;

/**
 * Robots Manager
 */
public class Manager implements Runnable {
    private Graph grap;
    private final List<Robot> robots;
           
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
    public void addRobot(Robot r)
    {
        robots.add(r);
    }
    
    /**
     * Remove a robot from the manager
     * @param r Robot to remove
     */
    public void removeRobot(Robot r)
    {
        robots.remove(r);
    }

    /**
     * Getter list robots
     * @return list robots
     */
    public List<Robot> getRobots() {
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
    
    private List<Node> searchNodesNotInFire(boolean mustBeNotOccuped)
    {
        List<Node> nodesNotInFire = new ArrayList<>();
        for (Node n : grap.getNodes())
        {
            if (n instanceof Fireable)
            { 
                Fireable fNode = (Fireable)n;
                if (fNode.isOnFire())
                { //Pour chaque noeud en feu
                    if (mustBeNotOccuped)
                    {
                        boolean robotOnIt = false;
                        for (Robot r : robots)
                        { //Regarde si le robot est présent sur le noeud
                            if (r.getCurrentNode().equals(fNode))
                            {
                                robotOnIt = true;
                                break;
                            }
                        }

                        if (!robotOnIt)
                        {
                            nodesNotInFire.add(n);
                        }
                    }
                }
            }
        }
        
        return nodesNotInFire;
    }

    /**
     * Check if node on fire and send Robot to them
     */
    @Override
    public void run() {
        Random rand = new Random();
        
        //Rechercher les incendies non affectés
        List<Node> nNotInFire = searchNodesNotInFire(true);
        
        //Pour chaque robot non occupé regarder le chemin
        //Prendre le meilleur chemin 
        for (Node n : nNotInFire)
        {
            List<Robot> bestRobots = new ArrayList<>();
            double bestValue = Double.MAX_VALUE;
            
            for (Robot r : robots)
            {
                if (!r.isBusy())
                {
                    double value = r.getPathValue(n);
                    if (value < bestValue)
                    {
                        bestRobots.clear();
                        bestValue = value;
                    }
                    
                    if (value == bestValue)
                    {
                        bestRobots.add(r);
                    }
                }
            }
            
            Robot bestRobot = robots.get(rand.nextInt(bestRobots.size()));
            bestRobot.setDestination(n); //Attribuer cette incendie à ce robot
        }
    }    
}