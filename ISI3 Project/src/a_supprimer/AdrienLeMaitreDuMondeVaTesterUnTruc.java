package a_supprimer;

import controller.actionmanagers.MainActionManager;
import java.io.FileNotFoundException;
import model.graph.Graph;
import model.graph.factory.GraphFactory;
import model.graph.factory.GraphOffsetFactory;
import model.robot.Manager;
import view.ImageLoader;
import view.windows.main.MainWindow;
import view.windows.Window;

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

        MainActionManager bam = new MainActionManager(graph);

        //graphFactory.save("S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme2.xml", graph);

        MainWindow window = new MainWindow(bam);
        window.setRobotManager(new Manager(graph));
        window.setGraph(graph, "S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme.jpg");
        //window.setGraph(graph, "D:\\Documents\\isi3\\mapsixieme\\mapsixieme.jpg");
        window.initialize();
        
        bam.setView(window);
        window.showWindow();
    }
}
