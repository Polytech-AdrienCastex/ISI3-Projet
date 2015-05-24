package controller.actionmanagers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import model.elementary.Fireable;
import model.elementary.Localisable;
import model.elementary.Point;
import model.graph.Graph;
import model.graph.Node;
import view.windows.main.IMainView;
import view.windows.editor.EditorWindow;

/**
 *
 */
public class MainActionManager extends ActionManager<IMainView>
{
    public MainActionManager(Graph graph)
    {
        super(true);
        this.graph = graph;
        this.mode = "normal";
    }
    
    protected Graph graph;
    protected String mode;

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
        switch(command)
        {
            case "edit":
                EditorActionManager eam = new EditorActionManager(graph);
                EditorWindow editorWindow = new EditorWindow(eam);
                editorWindow.setGraph(graph, this.getView().getGraphBackground());
                editorWindow.initialize();
                eam.setView(editorWindow);
                editorWindow.showDialog();
                break;
                
            case "setfire":
                Node node = findNodeFromLocation(graph, clkLocation, 10);
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
                
            case "fire":
                if(mode.equals(command))
                {
                    this.getView().setMode("normal");
                    mode = "normal";
                }
                else
                {
                    this.getView().setMode(command);
                    mode = command;
                }
                break;
                
            case "...":
                //...
                break;
        }
    }
}
