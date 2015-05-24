package sources.controller;

import sources.controller.actionmanagers.MainActionManager;
import sources.model.graph.Graph;
import sources.view.windows.main.MainWindow;

/**
 *
 */
public abstract class Main
{
    public static void main(String[] args) throws Exception
    {
        Graph graph = new Graph();
        
        MainActionManager bam = new MainActionManager(graph);
        
        MainWindow window = new MainWindow(bam);
        window.setGraph(graph, "S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme.jpg");
        
        bam.setView(window);
        
        window.initialize();
        window.run();
    }
}
