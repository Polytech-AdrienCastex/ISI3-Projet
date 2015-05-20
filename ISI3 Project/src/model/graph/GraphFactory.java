package model.graph;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.EdgeType;
import model.NodeListWrap;
import model.Point;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 */
public class GraphFactory
{
    public Graph load(File graphFile) throws ParserConfigurationException, IOException, SAXException
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(graphFile);
        String usr = document.getElementsByTagName("user").item(0).getTextContent();
        String pwd = document.getElementsByTagName("password").item(0).getTextContent();
        
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
                    
                    ProjectEdge edge = new ProjectEdge(
                            graph.getNodes().stream().filter(n -> n.getId() == node1).findFirst().get(),
                            graph.getNodes().stream().filter(n -> n.getId() == node2).findFirst().get(),
                            EdgeType.valueOf(elem.getAttribute("type")),
                            1.0
                    );
                });
        
        return graph;
    }
}
