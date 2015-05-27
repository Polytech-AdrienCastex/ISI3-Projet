package model.pathfinding.algorithms.astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import model.graph.Edge;
import model.graph.project.FireableNode;
import model.graph.Node;
import model.graph.project.ProjectEdge;
import model.pathfinding.Authorizer;

/**
 *
 * @author p1309208
 */
public class AstarBirdFly extends Astar{

    private ArrayList<VisitedNode> openList;
    private ArrayList<VisitedNode> closeList;
    private ArrayList<Edge> eds;
    
    public AstarBirdFly(FlyHeuristique h){
        openList = new ArrayList<VisitedNode>();
        closeList = new ArrayList<VisitedNode>();
        this.h = h;
    }

    
    private class VisitedNode{//extends Node
        private double quality;
        private double cost;
        private Node currentNode;
        private Edge previousPath;
        public VisitedNode(Node current, double cost, double qlty, Edge path) {
            quality = qlty;
            this.cost = cost;
            currentNode = current;
            previousPath = path;
        }

        public Node getCurrentNode() {
            return currentNode;
        }
        
        public double getCost() {
            return cost;
        }

        public double getQuality() {
            return quality;
        }

        public Edge getPreviousPath() {
            return previousPath;
        }
        /*
        public boolean isCurrent(Node cur){
            return currentnNode.(Node)
        }
        */
        @Override
        public boolean equals(Object o){
            if(o instanceof Node)
            {
                Node ptr = (Node)o;
                return this.currentNode.equals(ptr);
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 67 * hash + Objects.hashCode(this.currentNode);
            return hash;
        }
        
    }
    
    //public getShortestPath(Graph g, Node init, Node goal, Heuristique h){
    
    @Override
    public List<Edge> getShortestPath(Node init, Node goal, Authorizer auth){
        //O <- {s}; F <- Ø; g(s) <- 0; f(s) <- h(s);
          //Tant que O <> Ø faire
        Node current = init;
        double road = 0.0;
        openList.add(new VisitedNode(current, 0, 0, null));
        while(!openList.isEmpty())
        {
            
            openList.sort(new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    //if(o1 instanceof VisitedNode && o2 instanceof VisitedNode)
                        return ((VisitedNode)o1).quality < ((VisitedNode)o2).quality ? -1:
                            ( ((VisitedNode)o1).quality == ((VisitedNode)o2).quality ? 0 : 1);
                }
            });
            //Extraire de O l'élément x tq f(x) est minimale;    Insérer x dans F;
            closeList.add(openList.remove(0));
            current = closeList.get(closeList.size()-1).getCurrentNode();
            //Si x appartient à T alors Fini : Solution trouvée
            if(current.equals(goal))
                break;
            else
            {
                //Pour tout y successeur de x faire
                for(Node next : current.getNextNodes())
                {
                    double H = ((FlyHeuristique)h).getH((FireableNode)current, (FireableNode)next, (FireableNode)goal);
                    // plus courte arrete
                    Edge tempEd = null;
                    double tempVal = Double.MAX_VALUE;
                    if(!current.getEdges(next).isEmpty())
                    {
                        for(Edge ed : current.getEdges(next))
                        {
                            //if(auth.canUseEdge(ed))
                                System.out.println("INTO" + ((ProjectEdge)(ed)).getValue());
                                if(((ProjectEdge)(ed)).getValue() < tempVal)
                                {
                                    tempVal = ((ProjectEdge)(ed)).getValue();
                                    tempEd = ed;
                                }
                        }
                    }
                    //Si y n'appartient pas à F U O ou g(y) > g(x) + k(x,y) alors g(y) <- g(x) + k(x,y); f(y) <- g(y) + h(y); père(y) <- x; Insérer y dans O;
                    if(!closeList.contains(next)) //|| road )
                    {
                        double c = closeList.get(closeList.size()-1).getCost();
                        c += tempVal;
                        openList.add(new VisitedNode(next, c, c + H, tempEd));
                    }
                }
            }
        }
        
        List<Edge> result = new ArrayList<>();
        for(VisitedNode vn : closeList)
        {
            if(vn.getPreviousPath() != null)
                result.add(vn.getPreviousPath());
            System.out.println("CHOCOLAT!!!");
        }
        // + ajouter dernier noeud dans la liste
        return result;
    }
    
    /*
    public List<Edge> getShortestPath1(Node init, Node goal, Authorizer auth){
        List<Edge> result = new ArrayList<>();
        Node current = init;
        //int i;
        boolean stop = false;
        double H = 0;
        while(!stop && !current.equals(goal))
        {//
            //System.out.println("here " + current.getId());
            for(Node next : current.getNextNodes())
            {
                System.out.println("next : " + next.getId());
                if(!current.getEdges(next).isEmpty())
                {
                    double tempVal = 50.0; //Double.MAX_VALUE;
                    Edge tempEd = null;
                    H = ((FlyHeuristique)h).getH((FireableNode)current, (FireableNode)next, (FireableNode)goal);
                    for(Edge ed : current.getEdges(next))
                    {
                        //if(auth.canUseEdge(ed))
                            System.out.println("INTO" + ((RobotEdge)(ed)).getValue());
                            if(((RobotEdge)(ed)).getValue() < tempVal)
                            {
                                tempVal = ((RobotEdge)(ed)).getValue();
                                tempEd = ed;
                            }
                    }

                    System.out.println("next : " + next.getId() + " h : "+ H);
                    if(!closeList.contains(next))
                        if(!openList.contains(next))
                        {
                            openList.add(new VisitedNode(next, H, tempEd));
                            //System.out.println("ADDED");
                        }
                }
                //i++;
                    //else // on checke si on peut améliorer
                        //if()
            }
            openList.sort(new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    //if(o1 instanceof VisitedNode && o2 instanceof VisitedNode)
                        return ((VisitedNode)o1).quality < ((VisitedNode)o2).quality ? -1:
                            ( ((VisitedNode)o1).quality == ((VisitedNode)o2).quality ? 0 : 1);
                }
            });
            stop = openList.isEmpty();
            if(openList.size() > 0)
            {
                closeList.add(openList.get(0));
                current = openList.remove(0).getCurrentNode();
                System.out.println();
                System.out.println("ADDED " + current.getId());
            }
        };
        for(VisitedNode vn : closeList)
        {
            result.add(vn.getPreviousPath());
            System.out.println("CHOCOLAT!!!");
        }
        // + ajouter dernier noeud dans la liste
        return result;
    }*/
    
}
