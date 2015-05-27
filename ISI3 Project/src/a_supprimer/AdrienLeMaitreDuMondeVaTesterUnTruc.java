package a_supprimer;

import controller.actionmanagers.MainActionManager;
import java.io.FileNotFoundException;
import javafx.collections.ObservableList;
import model.graph.Graph;
import model.graph.Node;
import model.graph.factory.GraphFactory;
import model.graph.factory.GraphOffsetFactory;
import model.item.FireHose;
import model.pathfinding.PathFinding;
import model.pathfinding.algorithms.BFS;
import model.pathfinding.algorithms.astar.AstarBirdFly;
import model.pathfinding.algorithms.astar.BirdFly;
import model.robot.Robot4x4;
import model.robot.manager.FireManager;
import model.robot.manager.Manager;
import model.robot.manager.RobotRuntime;
import view.ImageLoader;
import view.windows.main.MainWindow;

/**
 *
 */
public class AdrienLeMaitreDuMondeVaTesterUnTruc
{
    public static void main(String[] args) throws Exception
    {
        Graph graph;
        
        ImageLoader.setImageFolder("D:\\Images");
        
        try
        {
            GraphFactory graphFactory = new GraphOffsetFactory(10, 10);
            graph = graphFactory.load("S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme.xml");
            //graph = graphFactory.load("D:\\Documents\\isi3\\mapsixieme\\mapsixieme.xml");
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Can't load the Graph");
            graph = new Graph();
        }
        
        Manager manager = new FireManager(graph);
        Node firstNode = graph.getNodes().iterator().next();
        PathFinding pf = new AstarBirdFly(new BirdFly());
        pf = new BFS();
        
        manager.addRobot(new Robot4x4(10.0, firstNode, pf, new FireHose(1.0)));
        manager.addRobot(new Robot4x4(10.0, firstNode, pf, new FireHose(1.0)));
        manager.addRobot(new Robot4x4(10.0, firstNode, pf, new FireHose(1.0)));
        
        
        
        System.out.println("manager " + manager.getRobots().size());
        
        

        MainActionManager bam = new MainActionManager(graph);

        //graphFactory.save("S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme2.xml", graph);

        MainWindow window = new MainWindow(bam);
        window.setRobotManager(manager);
        window.setGraph(graph, "S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme.jpg");
        //window.setGraph(graph, "D:\\Documents\\isi3\\mapsixieme\\mapsixieme.jpg");
        window.initialize();
        
        bam.setView(window);
        window.showWindow();
        
        System.out.println("Manager run");
        RobotRuntime rr = new RobotRuntime(manager);
        rr.start(1000);
    }
}
