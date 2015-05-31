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
    
    protected Collection<Node> searchNodesNotAffected(boolean mustBeNotOccuped)
    {
        return grap.getNodes().stream()
                .filter(n -> n instanceof Fireable)
                .map(n -> (Fireable)n)
                .filter(n -> n.isOnFire())
                .filter(n -> !mustBeNotOccuped || robots.stream().noneMatch(ffr -> ffr.getCurrentNode().equals(n) || ffr.getDestination() != null && ffr.getDestination().equals(n)))
                .map(n -> (Node)n)
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
        Collection<Node> nNotAffected = searchNodesNotAffected(true);
        
        //Pour chaque robot non occupé regarder le chemin
        //Prendre le meilleur chemin 
        for (Node n : nNotAffected)
        {
            List<FireFighterRobot> bestRobots = new ArrayList<>();
            double bestValue = Double.MAX_VALUE;
            System.out.println("************************************");
            for (FireFighterRobot r : robots)
            {
                if (!r.isBusy())
                {
                    double value = r.getPathValue(n);
                    System.out.println(value);
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
            System.out.println("************************************");
            
            if(bestRobots.size() > 0)
            {
                Robot bestRobot = bestRobots.get(rand.nextInt(bestRobots.size()));
                bestRobot.setDestination(n); //Attribuer cette incendie à ce robot
            }
        }
    }    
    
}
