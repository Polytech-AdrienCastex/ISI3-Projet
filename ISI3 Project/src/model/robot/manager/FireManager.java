package model.robot.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        return grap.getNodes().stream()
                .filter(n -> n instanceof Fireable)
                .map(n -> (Fireable)n)
                .filter(n -> n.isOnFire())
                .filter(n -> mustBeNotOccuped && robots.stream().noneMatch(ffr -> ffr.getCurrentNode().equals(n)))
                .map(n -> (Node)n)
                .collect(Collectors.toList());
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
                    if(value >= 0)
                    {
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
            }
            
            if(bestRobots.size() > 0)
            {
                Robot bestRobot = bestRobots.get(rand.nextInt(bestRobots.size()));
                bestRobot.setDestination(n); //Attribuer cette incendie à ce robot
            }
        }
    }    
    
}
