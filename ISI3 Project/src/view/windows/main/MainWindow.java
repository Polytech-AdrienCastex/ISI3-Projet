package view.windows.main;

import controller.actionmanagers.MainActionManager;
import java.awt.Image;
import view.windows.GraphWindow;

/**
 *
 */
public class MainWindow extends GraphWindow implements IMainView
{
    public MainWindow(MainActionManager actionManager)
    {
        super(actionManager);
    }
    
    @Override
    public void initialize()
    {
        super.initialize();
        
        addButton("play", "play.png");
        addButton("pause", "pause.png");
        addButton("step", "step.png");
        addSeparator();
        addButton("fire", "fire.png");
        addButton("add_robot", "robots/robot.png");
        addSeparator();
        addButton("edit", "edit.png");
    }

    @Override
    public Image getGraphBackground()
    {
        return this.graphDrawer.getBackgroundImage();
    }
}
