package model.robot.manager;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.util.Pair;
import model.elementary.Fireable;
import model.elementary.Localisable;
import model.graph.Graph;
import model.graph.Node;
import model.robot.FireFighterRobot;

public class FireFighterManager extends Manager<FireFighterRobot> {

    public FireFighterManager(Graph grap) {
        super(grap);
    }
    
    private List<Node> searchNodesNotAffected(boolean mustBeNotOccuped)
    {
        return grap.getNodes().stream()
                .filter(n -> n instanceof Fireable)
                .map(n -> (Fireable)n)
                .filter(n -> n.isOnFire())
                .filter(n -> !mustBeNotOccuped || robots.stream().noneMatch(ffr -> ffr.getCurrentNode().equals(n) || ffr.getDestination() != null && ffr.getDestination().equals(n)))
                .map(n -> (Node)n)
                .collect(Collectors.toList());
    }
    
    /**
     * Check if node on fire and send Robot to them
     */
    @Override
    public void run() {
        //Rechercher les incendies non affectés
        List<Node> nNotAffected = searchNodesNotAffected(true);
             
        System.out.println("========== Start FireFighter Manager ============");
        
        if (nNotAffected.size() > 0)
        {
            //Calcul chaque valeur de chaque noeud pour chaque robot
            LinkedHashMap<Pair<Node, FireFighterRobot>, Double> values = new LinkedHashMap<>();
            for (Node n : nNotAffected)
                for (FireFighterRobot r : robots)
                    if (!r.isBusy())
                    {
                        Double v = r.getPathValue(n);
                        if (v >= 0)
                            values.put(new Pair(n, r), v);
                    }

            if (values.isEmpty())
                System.out.println("No robot left");
            
            while(!values.isEmpty())
            {                            
                //Prendre le noeud/robot minimum
                Pair<Node, FireFighterRobot> pMin = null;
                double min = Double.MAX_VALUE;
                for(Entry<Pair<Node, FireFighterRobot>, Double> e : values.entrySet())
                {
                    System.out.println(e.getKey().getKey()+" / "+e.getKey().getValue()+" / "+e.getValue());
                    if (e.getValue() < min)
                    {
                        pMin = e.getKey();
                        min = e.getValue();
                    }
                } 

                if (pMin != null)
                {
                    //Ajouter la destination au robot
                    pMin.getValue().setDestination(pMin.getKey()); 
                    
                    System.out.println("Minimum = ["+min+" / "+pMin.getKey()+" / "+pMin.getValue()+"]");

                    //Supprimer toutes les valeurs relier au robot et au noeud
                    Set keys = new HashSet(values.keySet()); //recuperer les cles
                    Iterator<Pair<Node, FireFighterRobot>> it = keys.iterator(); 
                    while (it.hasNext())
                    {
                        Pair<Node, FireFighterRobot> p = it.next();
                        if (p.getKey().equals(pMin.getKey()) || p.getValue().equals(pMin.getValue()))
                            values.remove(p); //suppr si même noeud ou même robot
                    }
                }       
            }
        } else {
            System.out.println("No fire found");
        }
        
        System.out.println("========== End FireFighter Manager ============");
    }   
}