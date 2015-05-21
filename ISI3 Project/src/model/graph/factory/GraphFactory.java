package model.graph.factory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import model.graph.project.ProjectEdge;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 */
public class GraphFactory
{
    public Graph load(String filePath) throws ParserConfigurationException, IOException, SAXException
    {
        return load(new File(filePath));
    }
    public Graph load(File graphFile) throws ParserConfigurationException, IOException, SAXException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(graphFile);
        
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
                            EdgeType.valueOf(type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase()),
                            1.0
                    );
                });
        
        return graph;
    }
    
    public void save(String filePath, Graph graph) throws ParserConfigurationException, IOException
    {
        save(new File(filePath), graph);
    }
    public void save(File graphFile, Graph graph) throws ParserConfigurationException, IOException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        
        Document document = documentBuilder.newDocument();
        document.appendChild(graph.toXML(document));
        
        Files.write(graphFile.toPath(), NodeListWrap.getBytes(document));
    }
}
