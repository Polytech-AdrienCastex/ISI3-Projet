package controller;

import controller.actionmanagers.MainActionManager;
import model.graph.Graph;
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
        Graph graph = new Graph();
        
        MainActionManager bam = new MainActionManager(graph, null, null, null);
        
        MainWindow window = new MainWindow(bam);
        window.setGraph(graph, "S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme.jpg");
        
        bam.setView(window);
        
        window.initialize();
        window.showWindow();
    }
}
