package model.robot.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import model.elementary.Fireable;
import model.graph.Graph;
import model.graph.Node;
import model.robot.FireFighterRobot;
import model.robot.Robot;

public class FireFighterManager extends Manager<FireFighterRobot>
{
    public FireFighterManager(Graph grap)
    {
        super(grap);
    }
    
    protected Collection<Node> searchNotAffectedNodes(boolean mustBeNotOccuped)
    {
        return grap.getNodes().stream()
                .filter(n -> n instanceof Fireable)
                .map(n -> (Fireable)n)
                .filter(n -> n.isOnFire())
                .filter(n -> !mustBeNotOccuped || robots.stream().noneMatch(ffr -> ffr.getCurrentNode().equals(n) || ffr.getDestination() != null && ffr.getDestination().equals(n)))
                .map(n -> (Node)n)
                .collect(Collectors.toList());
    }
    
    protected Collection<Node> sortNotAffectedNodes(Collection<Node> nodes)
    {
        return nodes.stream()
                .sorted(Comparator.comparing(n -> robots.stream().filter(r -> !r.isBusy()).mapToDouble(r -> r.getPathValue(n)).min().getAsDouble()))
                .collect(Collectors.toList());
    }
    
    /**
     * Check if node on fire and send Robot to them
     */
    @Override
    public void run()
    {
        Random rand = new Random();
        
        //Rechercher les incendies non affectés
        Collection<Node> notAffectedNodes = sortNotAffectedNodes(searchNotAffectedNodes(true));
        
        //Pour chaque robot non occupé regarder le chemin
        //Prendre le meilleur chemin 
        for (Node n : notAffectedNodes)
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
