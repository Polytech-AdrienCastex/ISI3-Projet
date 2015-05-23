package view.main;

import controller.actionmanagers.MainActionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import model.elementary.Point;
import model.graph.Graph;
import view.Button;
import view.ButtonPanel;
import view.GraphWindow;
import view.Window;
import view.map.EdgeDrawer;
import view.map.GraphDrawer;
import view.map.NodeDrawer;

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
        
        addButton("edit", "play.png");
        addButton("edit", "step.png");
        addButton("edit", "fire.png");
        addSeparator();
        addButton("edit", "edit.png");
    }

    @Override
    public Image getGraphBackground()
    {
        return this.graphDrawer.getBackgroundImage();
    }
}
