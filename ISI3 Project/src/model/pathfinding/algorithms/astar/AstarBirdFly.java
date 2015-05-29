package model.pathfinding.algorithms.astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import model.graph.Edge;
import model.graph.project.FireableNode;
import model.graph.Node;
import model.graph.project.ProjectEdge;
import model.authorizer.Authorizer;

/**
 *
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
        private List<Node> baned;
        public VisitedNode(Node current, double cost, double qlty, Edge path) {
            quality = qlty;
            this.cost = cost;
            currentNode = current;
            previousPath = path;
            baned = new ArrayList<Node>();
        }
        
        public void addBaned(Node nd){
            if(!baned.contains(nd))
                baned.add(nd);
        }
        
        public List<Node> getBaned(){
            return baned;
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
        
        public void update(double cost, double quality, Edge edge){
            this.cost = cost;
            this.quality = quality;
            this.previousPath = edge;
            
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
            if(o instanceof Integer)
            {
                int id = (Integer)o;
                return this.currentNode.getId() == id;
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
        Node baned = null;
        boolean hasSuccessor = true;
        double road = 0.0;
        openList.add(new VisitedNode(init, 0, 0, null));
        while(!openList.isEmpty())
        {
            
            if(hasSuccessor){
                //Extraire de O l'élément x tq f(x) est minimale;    Insérer x dans F;
                openList.sort(new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        //if(o1 instanceof VisitedNode && o2 instanceof VisitedNode)
                            return ((VisitedNode)o1).quality < ((VisitedNode)o2).quality ? -1:
                                ( ((VisitedNode)o1).quality == ((VisitedNode)o2).quality ? 0 : 1);
                    }
                });/*
                VisitedNode vn = openList.remove(0);
                if(closeList.contains(vn))
                {
                   closeList.
                }*/
                closeList.add(openList.remove(0));
                current = closeList.get(closeList.size()-1).getCurrentNode();
            }
            else // chemin sans issue
            {
                System.out.println("nosucc");
                if(!closeList.isEmpty())
                {
                    baned = closeList.remove(closeList.size()-1).getCurrentNode();
                    closeList.get(closeList.size()-1).addBaned(baned);
                    current = closeList.get(closeList.size()-1).getCurrentNode();
                }
                //return null;
            }
            //Si x appartient à T alors Fini : Solution trouvée
            if(current.equals(goal))
                break;
            else
            {
                hasSuccessor = false;
                //Pour tout y successeur de x faire
                for(Node next : current.getNextNodes())
                {
                    if(!closeList.get(closeList.size()-1).getBaned().contains(next) && !openList.contains(next))
                    {
                        hasSuccessor = true;
                        double H = ((FlyHeuristique)h).getH((FireableNode)current, (FireableNode)next, (FireableNode)goal);
                        System.out.println("H:" + H);
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
                            }//Si y n'appartient pas à F U O ou g(y) > g(x) + k(x,y) alors g(y) <- g(x) + k(x,y); f(y) <- g(y) + h(y); père(y) <- x; Insérer y dans O;
                           
                            double c = closeList.get(closeList.size()-1).getCost();
                            c += tempVal;
                            if(! (closeList.contains(next) || openList.contains(next))) //|| road )
                            {
                                openList.add(new VisitedNode(next, c, c + H, tempEd));
                            }
                            else if(openList.contains(next))
                            {
                                if(openList.get(openList.indexOf(next)).cost > c + tempVal)
                                    openList.get(openList.indexOf(next)).update(c, c + H, tempEd);
                            }
                        }
                    }
                        else
                            System.out.println("catched");
            
                    
                }
            }
        }
        List<Edge> result = new ArrayList<>();
        /*
        for(VisitedNode vn : closeList)
        {
            if(vn.getPreviousPath() != null)
                result.add(vn.getPreviousPath());
            System.out.println("CHOCOLAT!!! " + vn.cost);
        }
        */
        Node fin = closeList.get(closeList.size()-1).previousPath.getStartNode();
        Edge link = closeList.get(closeList.size()-1).previousPath;
        boolean ok = false;
        while(true){ //link != null && link.getStartNode() != null
            result.add(link);
                        
            ok = true;
            for(VisitedNode vn : closeList)
            {
                if(link == null)
                {
                    //List<Edge> reverse = new ArrayList<Edge>();
                    
                    return result;
                }
                    
                if(vn.currentNode.getId()==link.getStartNode().getId())
                    if(ok)
                    {
                        link = vn.getPreviousPath();
                        ok = false;
                    }
            }
        }
        // + ajouter dernier noeud dans la liste
        //return result;
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
