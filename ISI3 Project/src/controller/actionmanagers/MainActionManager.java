package controller.actionmanagers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.ParserConfigurationException;
import model.elementary.Fireable;
import model.elementary.Point;
import model.graph.Graph;
import model.graph.Node;
import model.graph.factory.GraphFactory;
import model.pathfinding.PathFinding;
import model.robot.Robot;
import model.robot.manager.Manager;
import model.robot.manager.RobotRuntime;
import org.xml.sax.SAXException;
import view.windows.main.IMainView;
import view.windows.editor.EditorWindow;
import view.windows.settings.RobotEditorWindow;

/**
 * This class represents the action manager (controller) of the main view.
 */
public class MainActionManager extends ActionManager<IMainView>
{
    /**
     * Constructor.
     * @param graph Graph associated to the view.
     * @param runtime Runtime.
     * @param pathFinding Path finding algorithm to use.
     * @param managers Array containing the different managers.
     */
    public MainActionManager(Graph graph, RobotRuntime runtime, PathFinding pathFinding, Manager[] managers)
    {
        super(true);
        this.graph = graph;
        this.runtime = runtime;
        this.mode = "normal";
        
        this.managers = managers;
        this.pathFinding = pathFinding;
    }
    
    /**
     * Graph associated to the view.
     */
    protected Graph graph;
    
    /**
     * PathFinding used to create new robot.
     */
    protected final PathFinding pathFinding;
    
    /**
     * Array containing the different managers.
     */
    protected Manager[] managers;
    
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
        Node node;
        JFileChooser fc;
        
        switch(command)
        {
            case "play":
                if(runtime != null)
                {
                    runtime.start(1000);
                    this.getView().setMode(command);
                }
                break;
                
            case "pause":
                if(runtime != null)
                {
                    runtime.pause();
                    this.getView().setMode(command);
                }
                break;
                
            case "step":
                if(runtime != null)
                    runtime.runtime();
                break;
                
            case "edit":
                EditorActionManager eam = new EditorActionManager(graph);
                EditorWindow editorWindow = new EditorWindow(eam);
                editorWindow.setGraph(graph, this.getView().getGraphBackground());
                editorWindow.initialize();
                eam.setView(editorWindow);
                
                boolean isRuntimeRunning = runtime.isRunning();
                
                if(isRuntimeRunning)
                    runtime.pause();
                
                getView().setVisible(false);
                editorWindow.showDialog();
                getView().setVisible(true);
                
                if(isRuntimeRunning)
                    runtime.resume();
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
                
            case "setadd_robot":
                node = findNodeFromLocation(graph, clkLocation, 10);
                if(node != null)
                {
                    RobotEditorWindow rew = new RobotEditorWindow(managers);
                    rew.initialize();
                    rew.showDialog();
                    
                    Robot r = new Robot(rew.getSpeed(), node, pathFinding, rew.getAuthorizer());
                    r.addItem(rew.getWeapon());
                    rew.getManager().addRobot(r);
                }
                break;
                
            case "add_robot":
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
                
                
            case "load":
                fc = new JFileChooser();
                fc.setDialogTitle("Choose a XML file");
                if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    File f = fc.getSelectedFile();
                    
                    File backgroundImage = null;
                    fc.setDialogTitle("Choose a background file");
                    if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                        backgroundImage = fc.getSelectedFile();
                    
                    GraphFactory gf = new GraphFactory();
                    try
                    {
                        getView().setGraph(gf.load(f), backgroundImage);
                        System.out.println("Graph loaded.");
                    }
                    catch (ParserConfigurationException | IOException | SAXException ex)
                    {
                        System.out.println("Couldn't load the graph.");
                    }
                }
                break;
                
            case "...":
                //...
                break;
        }
    }
}
