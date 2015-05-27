package model.graph.factory;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import model.elementary.Point;
import model.graph.project.FireableNode;
import model.graph.Graph;
import model.elementary.Localisable;
import org.xml.sax.SAXException;

/**
 * This class represent a graph factory with offset. The offset change the
 * location of the nodes of the managed graph.
 */
public class GraphOffsetFactory extends GraphFactory
{
    /**
     * Constructor.
     * @param offsetX Offset X of the nodes of the graph.
     * @param offsetY Offset Y of the nodes of the graph.
     */
    public GraphOffsetFactory(int offsetX, int offsetY)
    {
        super();
        
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    
    /**
     * Offset X of the nodes of the graph.
     */
    protected final int offsetX;
    /**
     * Offset Y of the nodes of the graph.
     */
    protected final int offsetY;
    
    /**
     * Apply the offsets on the <i>graph</i>.
     * @param graph Graph to alter.
     */
    protected void applyOffset(Graph graph)
    {
        applyOffset(graph, 1);
    }
    /**
     * Apply the offsets on the <i>graph</i> with a <i>coef</i>.
     * <p>
     * This coefficient can be used to change the offsets or to revert the
     * changes.
     * <p>
     * A <i>coef</i> value of 1 will apply the offsets with no specific
     * alteration (similar as <i>applyOffset(Graph graph)</i>).
     * A <i>coef</i> value of -1 will revert the application of the offsets
     * previously made with a value of 1 or with the call of the method
     * <i>applyOffset(Graph graph)</i>.
     * @param graph Graph to alter.
     * @param coef Coefficient to apply on the offsets.
     */
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
