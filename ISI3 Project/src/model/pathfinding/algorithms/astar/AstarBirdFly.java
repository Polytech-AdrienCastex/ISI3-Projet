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
        private Node currentNode;
        private Edge previousPath;
        public VisitedNode(Node current, double val, Edge path) {
            quality = val;
            currentNode = current;
            previousPath = path;
        }

        public Node getCurrentNode() {
            return currentNode;
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
        List<Edge> result = new ArrayList<>();
        Node current = init;
        //int i;
        boolean stop = false;
        double H = 0;
        while(!stop && !current.equals(goal))
        {//
            //i = 1;
            System.out.println("here " + current.getId());
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
                        if(auth.canUseEdge(ed))
                            System.out.println("INTO" + ((ProjectEdge)(ed)).getValue());
                            if(((ProjectEdge)(ed)).getValue() < tempVal)
                            {
                                tempVal = ((ProjectEdge)(ed)).getValue();
                                tempEd = ed;
                            }
                    }

                    System.out.println("next : " + next.getId() + " h : "+ H);
                    if(!closeList.contains(next))
                        if(!openList.contains(next))
                        {
                            openList.add(new VisitedNode(next, H, tempEd));
                            System.out.println("ADDED");
                        }
                }
                //i++;
                    /*else // on checke si on peut améliorer
                        if()*/
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
            }
        };
        /*
        if(closeList.size() == 0)
            return null;
        
        closeList.add()*/
        for(VisitedNode vn : closeList)
        {
            result.add(vn.getPreviousPath());
            System.out.println("CHOCOLAT!!!");
        }
        // + ajouter dernier noeud dans la liste
        return result;
    }
    
}
