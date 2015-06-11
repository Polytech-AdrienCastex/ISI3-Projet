package view.windows.main;

import controller.actionmanagers.MainActionManager;
import java.awt.Image;
import java.util.Arrays;
import view.menupanel.Button;
import view.windows.GraphWindow;

/**
 * Main view.
 */
public class MainWindow extends GraphWindow implements IMainView
{
    /**
     * Constructor.
     * @param actionManager Main controller. 
     */
    public MainWindow(MainActionManager actionManager)
    {
        super(actionManager);
    }
    
    @Override
    public void initialize()
    {
        super.initialize();
        
        playButton = addButton("play", "buttons/play.png", "buttons/play_selected.png");
        pauseButton = addButton("pause", "buttons/pause.png", "buttons/pause_selected.png");
        addButton("step", "buttons/step.png");
        addSeparator();
        addButton("fire", "buttons/fire.png", "buttons/fire_selected.png");
        addButton("add_robot", "buttons/robot.png", "buttons/robot_selected.png");
        addSeparator();
        addButton("load", "buttons/load.png");
        addButton("edit", "buttons/edit.png");
        
        pauseButton.setSelected(true);
        oldPlayMode = "pause";
    }

    @Override
    public Image getGraphBackground()
    {
        return this.graphDrawer.getBackgroundImage();
    }
    
    private Button playButton;
    private Button pauseButton;
    private String oldPlayMode;
    
    @Override
    public void setMode(String mode)
    {
        super.setMode(mode);
        
        if(!mode.equals(oldPlayMode) && Arrays.asList(new String[] { "play", "pause" }).contains(mode))
            oldPlayMode = mode;
        
        playButton.setSelected(oldPlayMode.equals("play"));
        pauseButton.setSelected(oldPlayMode.equals("pause"));
    }
}
