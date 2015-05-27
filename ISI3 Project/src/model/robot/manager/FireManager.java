package model.robot.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.elementary.Fireable;
import model.graph.Graph;
import model.graph.Node;
import model.robot.FireFighterRobot;
import model.robot.Robot;

public class FireManager extends Manager<FireFighterRobot> {

    public FireManager(Graph grap) {
        super(grap);
    }
    
    private List<Node> searchNodesNotAffected(boolean mustBeNotOccuped)
    {
        List<Node> nodesNotAffected = new ArrayList<>();
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
                        for (FireFighterRobot r : robots)
                        { //Regarde si le robot est présent sur le noeud
                            if (r.getCurrentNode().equals(fNode))
                            {
                                robotOnIt = true;
                                break;
                            }
                        }

                        if (!robotOnIt)
                        {
                            nodesNotAffected.add(n);
                        }
                    } else {
                        nodesNotAffected.add(n);
                    }
                }
            }
        }
        
        return nodesNotAffected;
    }
    
    /**
     * Check if node on fire and send Robot to them
     */
    @Override
    public void run() {
        Random rand = new Random();
        
        //Rechercher les incendies non affectés
        List<Node> nNotAffected = searchNodesNotAffected(true);
        
        //Pour chaque robot non occupé regarder le chemin
        //Prendre le meilleur chemin 
        for (Node n : nNotAffected)
        {
            List<FireFighterRobot> bestRobots = new ArrayList<>();
            double bestValue = Double.MAX_VALUE;
            
            for (FireFighterRobot r : robots)
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
            
            if(bestRobots.size() > 0)
            {
                Robot bestRobot = bestRobots.get(rand.nextInt(bestRobots.size()));
                bestRobot.setDestination(n); //Attribuer cette incendie à ce robot
            }
        }
    }    
    
}
