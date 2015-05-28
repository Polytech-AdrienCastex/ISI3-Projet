package controller.actionmanagers;

import static controller.actionmanagers.ActionManager.findNodeFromLocation;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import model.elementary.Point;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.project.FireableNode;
import model.graph.project.ProjectEdge;
import model.robot.manager.RobotRuntime;
import view.windows.edge.EdgeEditorWindow;
import view.windows.edge.NodeEditorWindow;
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
