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
        addButton("step", "step.png");
        addButton("fire", "fire.png");
        addSeparator();
        addButton("edit", "edit.png");
    }

    @Override
    public Image getGraphBackground()
    {
        return this.graphDrawer.getBackgroundImage();
    }
}
