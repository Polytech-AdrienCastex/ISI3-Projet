package controller;

import controller.actionmanagers.MainActionManager;
import java.io.FileNotFoundException;
import model.ResourceLoader;
import model.authorizer.AuthPafPaf;
import model.authorizer.Authorizer;
import model.graph.Graph;
import model.graph.Node;
import model.graph.factory.GraphFactory;
import model.graph.factory.GraphOffsetFactory;
import model.item.FireHose;
import model.pathfinding.PathFinding;
import model.pathfinding.algorithms.Astar;
import model.pathfinding.algorithms.heuristics.BirdFly;
import model.robot.FireFighterRobot;
import model.robot.manager.FireFighterManager;
import model.robot.manager.Manager;
import model.robot.manager.RobotRuntime;
import view.ImageLoader;
import view.windows.main.MainWindow;

/**
 * This class represents the entry point of the program.
 */
public abstract class Main
{
    /**
     * Entry point of the program.
     * @param args Parameters passed by the Operating System and by the user.
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
        Graph graph;
        
        ImageLoader.setImageFolder("D:\\Images");
        ResourceLoader resourceLoader = new ResourceLoader("controller/resources/");
        
        // Load model
        try
        {
            GraphFactory graphFactory = new GraphOffsetFactory(10, 10);
            graph = graphFactory.load(resourceLoader.loadStream("mapsixieme.xml"));
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Can't load the Graph!");
            graph = new Graph();
        }
        
        Manager manager = new FireFighterManager(graph);
        PathFinding pf = new Astar(new BirdFly());
        
        RobotRuntime rr = new RobotRuntime(manager);
        
        // Load controller
        MainActionManager bam = new MainActionManager(graph, rr, pf, new Manager[] { manager });
        
        // Load view
        MainWindow window = new MainWindow(bam);
        window.setRobotManager(manager);
        window.setGraph(graph, resourceLoader.loadStream("mapsixieme.jpg"));
        window.initialize();
        
        bam.setView(window);
        window.showWindow();
    }
}
