package model.pathfinding.algorithms.OLD;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.elementary.Fireable;
import model.graph.Edge;
import model.graph.project.FireableNode;
import model.graph.Node;
import model.graph.project.ProjectEdge;
import model.authorizer.Authorizer;

/**
 *
 */

public class AstarBirdFlyOLD extends AstarOLD
{
    //private ArrayList<VisitedNode> openList;
    //private ArrayList<VisitedNode> closeList;
    
    public AstarBirdFlyOLD(FlyHeuristiqueOLD h)
    {
        //openList = new ArrayList<>();
        //closeList = new ArrayList<>();
        this.heuristic = h;
    }

    
    private class VisitedNode
    {
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
            baned = new ArrayList<>();
        }
        
        public void addBaned(Node nd)
        {
            if(!baned.contains(nd))
                baned.add(nd);
        }
        
        public List<Node> getBaned()
        {
            return baned;
        }
        
        public Node getCurrentNode()
        {
            return currentNode;
        }
        
        public double getCost()
        {
            return cost;
        }

        public double getQuality()
        {
            return quality;
        }

        public Edge getPreviousPath()
        {
            return previousPath;
        }
        
        public void update(double cost, double quality, Edge edge)
        {
            this.cost = cost;
            this.quality = quality;
            this.previousPath = edge;
        }
        
        @Override
        public boolean equals(Object o)
        {
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
        public int hashCode()
        {
            int hash = 3;
            hash = 67 * hash + Objects.hashCode(this.currentNode);
            return hash;
        }
        
    }
    
    @Override
    public Collection<Edge> getShortestPath(Node init, Node goal, Authorizer auth)
    {
        //O <- {s}; F <- Ø; g(s) <- 0; f(s) <- h(s);
          //Tant que O <> Ø faire
        List<VisitedNode> openList = new ArrayList<>();
        List<VisitedNode> closeList = new ArrayList<>();
        Node current = init;
        Node previous = null;
        boolean hasSuccessor = true;
        openList.add(new VisitedNode(init, 0, 0, null));
        openList.get(openList.size() - 1).addBaned(current);
        do
        {
            System.out.println("here");
            //Extraire de la liste ouverte l'élément x de meileeure qualité et l'insére dans la liste fermée
            if(hasSuccessor)
            {
                openList.sort(new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        return ((VisitedNode)o1).quality < ((VisitedNode)o2).quality ? -1:
                            ( ((VisitedNode)o1).quality == ((VisitedNode)o2).quality ? 0 : 1);
                    }
                });
                closeList.add(openList.remove(0));
                previous = current;
                current = closeList.get(closeList.size()-1).getCurrentNode();
            } 
            else if(!closeList.isEmpty())   // chemin sans issue, on remonte le chemin
            {
                previous = closeList.remove(closeList.size()-1).getCurrentNode();
                if(closeList.size() > 0)
                {
                    closeList.get(closeList.size()-1).addBaned(previous);
                    current = closeList.get(closeList.size()-1).getCurrentNode();
                }
            }
            //Si x appartient à la liste finie alors Fin : Solution trouvée
            if(!current.equals(goal))
            {
                hasSuccessor = false;
                //Pour tout y successeur de x...
                for(Node next : current.getNextNodes())
                {
                    if(auth.canUseNode(next))
                        if(!closeList.isEmpty() && !closeList.get(closeList.size()-1).getBaned().contains(next) && !openList.contains(next) && !next.equals(previous))
                        {
                            double H = ((FlyHeuristiqueOLD)heuristic).getH((FireableNode)current, (FireableNode)next, (FireableNode)goal);
                            Edge tempEd = null;
                            double tempVal = Double.MAX_VALUE;
                            if(!current.getEdges(next).isEmpty())
                            {
                                for(Edge ed : current.getEdges(next))
                                {
                                    if(auth.canUseEdge(ed))
                                    {
                                        if(((ProjectEdge)(ed)).getValue() < tempVal)
                                        {
                                            tempVal = ((ProjectEdge)(ed)).getValue();
                                            tempEd = ed;
                                        }
                                        hasSuccessor = true;
                                    }
                                }
                                //Si y n'appartient pas à F U O ou g(y) > g(x) + k(x,y) alors g(y) <- g(x) + k(x,y); f(y) <- g(y) + h(y); père(y) <- x; Insérer y dans O;

                                double c = closeList.get(closeList.size()-1).getCost();
                                c += tempVal;
                                if(! (closeList.contains(next) || openList.contains(next)))
                                    openList.add(new VisitedNode(next, c, c + H, tempEd));
                                else if(openList.contains(next) && openList.get(openList.indexOf(next)).cost > c + tempVal)
                                    openList.get(openList.indexOf(next)).update(c, c + H, tempEd);
                            }
                        }
                }
            }
        } while(!openList.isEmpty() && !closeList.isEmpty() && !current.equals(goal));
        
        return getEdgesFromPath(closeList);
    }
    
    protected Collection<Edge> getEdgesFromPath(Collection<VisitedNode> closeList)
    {
        Collection<Edge> result = new ArrayList<>();
        
        if(closeList != null)
            for(VisitedNode ed : closeList)
                if(ed.previousPath != null)
                    result.add(ed.previousPath);
        
        return result;
    }
}
