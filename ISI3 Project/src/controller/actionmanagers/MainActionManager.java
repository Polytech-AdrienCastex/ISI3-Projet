package controller.actionmanagers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import model.elementary.Fireable;
import model.elementary.Point;
import model.graph.Graph;
import model.graph.Node;
import model.robot.manager.RobotRuntime;
import view.windows.main.IMainView;
import view.windows.editor.EditorWindow;

/**
 * This class represents the action manager (controller) of the main view.
 */
public class MainActionManager extends ActionManager<IMainView>
{
    /**
     * Constructor.
     * @param graph Graph associated to the view.
     */
    public MainActionManager(Graph graph, RobotRuntime runtime)
    {
        super(true);
        this.graph = graph;
        this.runtime = runtime;
        this.mode = "normal";
    }
    
    /**
     * Graph associated to the view.
     */
    protected Graph graph;
    
    /**
     * Previous mode selected.
     * <p>
     * It is useful to make toggle systems (if the previous mode is the same as
     * the one we clicked on, then it means we want to disactivate this mode and
     * go back to the default mode).
     */
    protected String mode;
    
    /**
     * Runtime to play when the "game" has to start.
     */
    protected RobotRuntime runtime;

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
            case "play":
                System.out.println("AAAAAAAAAa");
                if(runtime != null)
                    if(!mode.equals(command))
                    {
                System.out.println("BBBBBBBBb");
                        runtime.start(1000);
                        mode = command;
                    }
                    else
                    {
                System.out.println("CCCCCCCCCCCCCC");
                        runtime.stop();
                        mode = "normal";
                    }
                break;
                
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
