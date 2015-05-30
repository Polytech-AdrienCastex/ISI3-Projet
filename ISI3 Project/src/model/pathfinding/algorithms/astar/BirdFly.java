package model.pathfinding.algorithms.astar;

import model.elementary.Localisable;
import model.elementary.Point;
import model.graph.Node;
import model.graph.project.FireableNode;


public class BirdFly implements FlyHeuristique {
 
    @Override
    public double getH(Node n1, Node n2) {
        if(!(n1 instanceof Localisable && n2 instanceof Localisable))
            return -1.0;
        
        Point dep = ((Localisable)n1).getLocation();
        Point arr = ((Localisable)n2).getLocation();
        return distEuclide(dep, arr);
    }
    
    @Override
    public double getH(FireableNode n1, FireableNode n2, FireableNode n3) {
        double dist[] = new double[2];
        dist[0] = getH(n1, n2);
        Point dep = n2.getLocation();
        Point arr = n3.getLocation();
        dist[1] = distEuclide(dep, arr);
        return average(dist);
    }
    
    private double distEuclide(Point p1, Point p2){
        double dX = deltaX(p1, p2);
        double dY = deltaY(p1, p2);
        return Math.sqrt(dX*dX + dY*dY);
    }
    
    private double deltaX(Point p1, Point p2){
        return Math.abs(p1.x-p2.x);
    }
    
    private double deltaY(Point p1, Point p2){
        return Math.abs(p1.y-p2.y);
    }
    
    private double average(double[] v){
        int sum = 0;
        for(int i = 0; i < v.length; i++)
            sum += v[i];
        return sum/v.length;
    }

}
