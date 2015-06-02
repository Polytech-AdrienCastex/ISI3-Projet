package model.graph.factory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.stream.Stream;
import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.SurfaceType;
import model.xml.NodeListWrap;
import model.elementary.Point;
import model.graph.project.FireableNode;
import model.graph.Graph;
import model.graph.project.ProjectEdge;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * This class represents a graph factory to manage graph files.
 */
public class GraphFactory
{
    /**
     * Load a graph file and convert it to a <i>Graph</i>.
     * @param filePath Path of the file of the graph to load.
     * @return The loaded graph.
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException 
     */
    public Graph load(String filePath) throws ParserConfigurationException, IOException, SAXException
    {
        File file = new File(filePath);
        if(file.exists())
            return load(file);
        else
            return load(getClass().getClassLoader().getResourceAsStream("controller/resources/" + filePath));
    }
    
    /**
     * Load a graph file and convert it to a <i>Graph</i>.
     * @param graphFile File of the graph to load.
     * @return The loaded graph.
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException 
     */
    public Graph load(File graphFile) throws ParserConfigurationException, IOException, SAXException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(graphFile);
        
        return load(document);
    }
    
    /**
     * Load a graph stream and convert it to a <i>Graph</i>.
     * @param graphStream Stream of the graph to load.
     * @return The loaded graph.
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException 
     */
    public Graph load(InputStream graphStream) throws ParserConfigurationException, IOException, SAXException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(graphStream);
        
        return load(document);
    }
    
    /**
     * Convert a XML document to a <i>Graph</i>.
     * @param document XML document to convert.
     * @return The loaded graph.
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException 
     */
    public Graph load(Document document) throws ParserConfigurationException, IOException, SAXException
    {
        Stream<org.w3c.dom.Node> nodeStream = NodeListWrap.getStream(document.getElementsByTagName("node"));
        Stream<org.w3c.dom.Node> edgeStream = NodeListWrap.getStream(document.getElementsByTagName("edge"));
        
        Graph graph = new Graph();
        
        nodeStream
                .map(dn -> (Element)dn)
                .map(elem -> new Pair<Element, FireableNode>(elem,
                        new FireableNode(
                                Integer.parseInt(elem.getAttribute("id")),
                                graph,
                                new Point(Double.parseDouble(elem.getAttribute("x")), Double.parseDouble(elem.getAttribute("y"))))))
                .filter(pair -> !pair.getKey().getAttribute("type").equals("NORMAL"))
                .forEach(pair -> 
                {
                    switch(pair.getKey().getAttribute("type").trim().toUpperCase())
                    {
                        default:
                        case "NORMAL":
                            break;
                            
                        case "INCENDIE":
                            pair.getValue().setFireIntensity(10.0);
                            break;
                    }
                });
        
        edgeStream
                .map(dn -> (Element)dn)
                .forEach(elem -> {
                    int node1 = Integer.parseInt(elem.getAttribute("nd1"));
                    int node2 = Integer.parseInt(elem.getAttribute("nd2"));
                    String type = elem.getAttribute("type");
                    
                    ProjectEdge edge = new ProjectEdge(
                            graph.getNodes().stream().filter(n -> n.getId() == node1).findFirst().get(),
                            graph.getNodes().stream().filter(n -> n.getId() == node2).findFirst().get(),
                            SurfaceType.valueOf(type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase()),
                            1.0
                    );
                });
        
        return graph;
    }
    
    
    /**
     * Save a <i>graph</i> to the file determined by <i>filePath</i>.
     * @param filePath File which will contains the graph.
     * @param graph Graph to save.
     * @throws ParserConfigurationException
     * @throws IOException 
     */
    public void save(String filePath, Graph graph) throws ParserConfigurationException, IOException
    {
        save(new File(filePath), graph);
    }
    /**
     * Save a <i>graph</i> to the file determined by <i>graphFile</i>.
     * @param graphFile File which will contains the graph.
     * @param graph Graph to save.
     * @throws ParserConfigurationException
     * @throws IOException 
     */
    public void save(File graphFile, Graph graph) throws ParserConfigurationException, IOException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        
        Document document = documentBuilder.newDocument();
        document.appendChild(graph.toXML(document));
        
        Files.write(graphFile.toPath(), NodeListWrap.getBytes(document));
    }
}
