package controller.actionmanagers;

import static controller.actionmanagers.ActionManager.findNodeFromLocation;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import model.elementary.Fireable;
import model.elementary.Point;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.factory.GraphFactory;
import model.graph.project.FireableNode;
import model.graph.project.ProjectEdge;
import view.windows.settings.EdgeEditorWindow;
import view.windows.settings.NodeEditorWindow;
import view.windows.editor.IEditorView;

/**
 * This class represents the action manager (controller) associated to the
 * editor view.
 */
public class EditorActionManager extends ActionManager<IEditorView>
{
    /**
     * Constructor.
     * @param graph Graph associated to the view. 
     */
    public EditorActionManager(Graph graph)
    {
        super(false);
        this.graph = graph;
    }
    
    /**
     * Graph associated to the view.
     */
    protected Graph graph;
    
    /**
     * Selected node in the previous actions occured on the view.
     * <p>
     * When a mode is selected (for example : link two nodes), on the second
     * node selection, the previous node will be linked with the new one.
     */
    protected Node selectedNode;

    @Override
    protected void action(String command, ActionEvent e)
    {
        switch(command)
        {
            case "...":
                //...
                break;
        }
    }

    @Override
    protected void action(String command, MouseEvent e, Point clkLocation)
    {
        Node node;
        Edge edge;
        JFileChooser fc;
        
        switch(command)
        {
            case "setadd_node":
                FireableNode fn = new FireableNode(graph, clkLocation);
                
                NodeEditorWindow newin = new NodeEditorWindow();
                newin.initialize();
                newin.showDialog();
                
                fn.setFireIntensity(newin.getFireIntensity());
                break;
                
            case "setadd_edge":
                selectedNode = findNodeFromLocation(graph, clkLocation, 10);
                if(selectedNode != null)
                    this.getView().setMode("add_edge_2");
                break;
                
            case "setadd_edge_2":
                node = findNodeFromLocation(graph, clkLocation, 10);
                if(node != null)
                {
                    EdgeEditorWindow eew = new EdgeEditorWindow();
                    eew.initialize();
                    eew.showDialog();
                    
                    edge = new ProjectEdge(selectedNode, node, eew.getEdgeType(), eew.getEdgeValue());
                    this.getView().setMode("add_edge");
                }
                break;
                
            case "setremove_node":
                node = findNodeFromLocation(graph, clkLocation, 10);
                if(node != null)
                    graph.removeNode(node);
                break;
                
            case "setremove_edge":
                selectedNode = findNodeFromLocation(graph, clkLocation, 10);
                if(selectedNode != null)
                    this.getView().setMode("remove_edge_2");
                break;
                
            case "setremove_edge_2":
                node = findNodeFromLocation(graph, clkLocation, 10);
                if(node != null)
                {
                    selectedNode.getEdges(node).forEach(ed -> ed.unlink());
                    
                    this.getView().setMode("remove_edge");
                }
                break;
                
            case "setfire":
                node = findNodeFromLocation(graph, clkLocation, 10);
                if(node != null)
                {
                    if(node instanceof Fireable)
                    {
                        Fireable fireNode = (Fireable)node;
                        if(fireNode.isOnFire())
                            fireNode.setFireIntensity(0.0);
                        else
                            fireNode.setFireIntensity(10.0);
                    }
                }
                break;
                
            case "save":
                fc = new JFileChooser();
                if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    File f = fc.getSelectedFile();
                    GraphFactory gf = new GraphFactory();
                    try
                    {
                        gf.save(f, graph);
                        System.out.println("Graph saved.");
                    }
                    catch (ParserConfigurationException | IOException ex)
                    {
                        System.out.println("Couldn't save the graph.");
                    }
                }
                break;
                
            case "fire":
            case "remove_node":
            case "remove_edge":
            case "add_node":
            case "add_edge":
                this.getView().setMode(command);
                break;
        }
    }
}
