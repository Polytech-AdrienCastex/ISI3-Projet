package controller.actionmanagers;

import static controller.actionmanagers.ActionManager.findNodeFromLocation;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JWindow;
import model.EdgeType;
import model.elementary.Point;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.project.FireableNode;
import model.graph.project.ProjectEdge;
import view.windows.edge.EdgeEditorWindow;
import view.windows.edge.NodeEditorWindow;
import view.windows.editor.EditorWindow;
import view.windows.editor.IEditorView;

/**
 *
 */
public class EditorActionManager extends ActionManager<IEditorView>
{
    public EditorActionManager(Graph graph)
    {
        super(false);
        this.graph = graph;
    }
    
    protected Graph graph;

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
    
    protected Node selectedNode;

    @Override
    protected void action(String command, MouseEvent e, Point clkLocation)
    {
        Node node;
        Edge edge;
        
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
                
            case "remove_node":
            case "remove_edge":
            case "add_node":
            case "add_edge":
                this.getView().setMode(command);
                break;
        }
    }
}
