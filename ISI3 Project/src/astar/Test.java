package astar;

import java.util.ArrayList;
import java.util.List;
import static model.EdgeType.Plat;
import model.elementary.Point;
import model.graph.Edge;
import model.graph.project.FireableNode;
import model.graph.Graph;
import model.graph.Node;
import model.graph.project.ProjectEdge;
import model.robot.Robot;
import model.robot.RobotPafPaf;


public class Test {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        FlyHeuristique fh = new BirdFly();
        AstarBirdFly as = new AstarBirdFly(fh);
        Graph g = new Graph();
        Node nA = new FireableNode(1, g, new Point(2.0, 2.0));
        Node nB = new FireableNode(2, g, new Point(6.0, 2.0));
        Node nC = new FireableNode(3, g, new Point(7.0, 7.0));
        Node nD = new FireableNode(4, g, new Point(4.0, 4.0));
        Node nE = new FireableNode(5, g, new Point(5.0, 8.0));
        Node nF = new FireableNode(6, g, new Point(1.0, 4.0));
        
        
        ProjectEdge gAE = new ProjectEdge(nA, nE, Plat, 40.0);
        gAE.setUnderWater(false);
        /*nA.addEdge(gAE);
        nE.addEdge(gAE);*/
        ProjectEdge gAF = new ProjectEdge(nA, nF, Plat, 3.0);
        gAF.setUnderWater(false);
        /*nA.addEdge(gAF);
        nF.addEdge(gAF);*/
        ProjectEdge gDB = new ProjectEdge(nD, nB, Plat,  1.0);
        gDB.setUnderWater(false);
        /*nB.addEdge(gBD);
        nD.addEdge(gBD);*/
        ProjectEdge gBE = new ProjectEdge(nB, nE, Plat,  5.0);
        gBE.setUnderWater(false);
        /*nB.addEdge(gBE);
        nE.addEdge(gBE);*/
        ProjectEdge gFD = new ProjectEdge(nF, nD, Plat,  2.0);
        gFD.setUnderWater(false);
        /*nF.addEdge(gFD);
        nD.addEdge(gFD);*/
        ProjectEdge gEC = new ProjectEdge(nE, nC, Plat,  3.0);
        gEC.setUnderWater(false);
        /*nC.addEdge(gCE);
        nE.addEdge(gCE);*//*
        RobotEdge gDF = new RobotEdge(nD, nF, 3);
        nD.addEdge(gDF);
        nF.addEdge(gDF);
        */
        //AFDB
        
        System.out.println("*** DEBUT GRAPH ***");
        
        for(Node nd : g.getNodes())
            for(Edge ed : nd.getEdges())
                System.out.println(nd.getId());
        
        
        System.out.println("*** FIN GRAPH ***");
        
                    
        Robot robert = new RobotPafPaf(5.0, nA, as, null);
        List<Edge> list = as.getShortestPath(nA , nC, robert);
        if(list == null || list.isEmpty())
            System.out.println("outch!");
        for(Edge ed : list)
            if(ed != null)
                System.out.println(ed.getStartNode().getId() + " TO " + ed.getStopNode().getId());
    }
}
