package model.graph.factory;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.EdgeType;
import model.NodeListWrap;
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
    
    @Override
    public Graph load(File graphFile) throws ParserConfigurationException, IOException, SAXException
    {
        Graph graph = super.load(graphFile);
        
        
        graph.getNodes()
                .forEach(n -> 
                {
                    Point p = ((Localisable)n).getLocation();
                    p.x -= offsetX;
                    p.y -= offsetY;
                    ((FireableNode)n).location = p;
                });
        
        return graph;
    }
}
