package model.graph.factory;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.EdgeType;
import model.xml.NodeListWrap;
import model.elementary.Point;
import model.graph.project.FireableNode;
import model.graph.Graph;
import model.elementary.Localisable;
import model.graph.project.ProjectEdge;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 */
public class GraphOffsetFactory extends GraphFactory
{
    public GraphOffsetFactory(int offsetX, int offsetY)
    {
        super();
        
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    
    protected final int offsetX;
    protected final int offsetY;
    
    protected void applyOffset(Graph graph)
    {
        applyOffset(graph, 1);
    }
    protected void applyOffset(Graph graph, int coef)
    {
        graph.getNodes()
                .forEach(n -> 
                {
                    Point p = ((Localisable)n).getLocation();
                    p.x -= offsetX * coef;
                    p.y -= offsetY * coef;
                    ((FireableNode)n).location = p;
                });
    }
    
    @Override
    public Graph load(File graphFile) throws ParserConfigurationException, IOException, SAXException
    {
        Graph graph = super.load(graphFile);
        
        applyOffset(graph, 1);
        
        return graph;
    }
    
    @Override
    public void save(File graphFile, Graph graph) throws ParserConfigurationException, IOException
    {
        applyOffset(graph, -1);
        
        super.save(graphFile, graph);
        
        applyOffset(graph, 1);
    }
}
