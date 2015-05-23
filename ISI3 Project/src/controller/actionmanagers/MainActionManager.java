package controller.actionmanagers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import model.elementary.Point;
import model.graph.Graph;
import view.main.IMainView;
import view.map.editor.EditorWindow;

/**
 *
 */
public class MainActionManager extends ActionManager<IMainView>
{
    public MainActionManager(Graph graph)
    {
        super(true);
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
                editorWindow.run();
                break;
                
            case "...":
                //...
                break;
        }
    }
}
