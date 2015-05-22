package a_supprimer;

import controller.ButtonActionManager;
import model.graph.Graph;
import model.graph.factory.GraphFactory;
import model.graph.factory.GraphOffsetFactory;
import view.Window;

/**
 *
 */
public class AdrienLeMaitreDuMondeVaTesterUnTruc
{
    public static void main(String[] args) throws Exception
    {
        GraphFactory graphFactory = new GraphOffsetFactory(10, 10);
        Graph graph = graphFactory.load("S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme.xml");
        
        ButtonActionManager bam = new ButtonActionManager(graph);
        
        //graphFactory.save("S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme2.xml", graph);
        
        Window window = new Window(bam, "S:\\ISI3\\mapsixieme\\mapsixieme\\mapsixieme.jpg");
        window.setGraph(graph);
        window.run();
    }
}
