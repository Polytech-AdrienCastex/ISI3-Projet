package view;

import controller.actionmanagers.ActionManager;
import view.main.*;
import controller.actionmanagers.MainActionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.elementary.Point;
import model.graph.Graph;
import view.map.EdgeDrawer;
import view.map.GraphDrawer;
import view.map.NodeDrawer;

/**
 *
 */
public abstract class GraphWindow extends Window
{
    public GraphWindow(ActionManager actionManager)
    {
        super(actionManager);
    }
    
    protected GraphDrawer graphDrawer;
    protected ButtonPanel buttonPanel;
    
    public void setGraph(Graph graph)
    {
        setGraph(graph, (Image)null);
    }
    public void setGraph(Graph graph, String backgroundPath)
    {
        if(backgroundPath != null)
            try
            {
                Image image = ImageIO.read(new File(backgroundPath));
                setGraph(graph, image);
                return;
            }
            catch (IOException e)
            { }
        
        setGraph(graph, (Image)null);
    }
    public void setGraph(Graph graph, Image backgroundImage)
    {
        NodeDrawer nd = new NodeDrawer();
        EdgeDrawer ed = new EdgeDrawer();
        
        this.graphDrawer = new GraphDrawer(graph, nd, ed);
        if(backgroundImage != null && this.graphDrawer.setBackgroundImage(backgroundImage))
        {
            Point size = this.graphDrawer.getBackgroundSize();
            this.setMinimumSize(new Dimension(size.x.intValue(), size.y.intValue()));
        }
        else
            this.setSize(new Dimension(600, 600));
    }
    
    protected Button addButton(String action, String imageName)
    {
        Button btn = new Button(action, imageName);
        btn.addMouseListener(this.actionManager);
        this.buttonPanel.add(btn);
        return btn;
    }
    protected Separator addSeparator()
    {
        Separator sep = new Separator();
        this.buttonPanel.add(sep);
        return sep;
    }
    
    @Override
    public void initialize()
    {
        this.graphDrawer.setBackground(Color.green);
        this.graphDrawer.setSize(this.getSize());
        this.graphDrawer.setPreferredSize(this.getSize());
        this.graphDrawer.setLocation(0, 0);
        
        this.buttonPanel = new ButtonPanel();
        this.buttonPanel.setLocation(0, 0);
        this.buttonPanel.setSize(1000, 1000);
        
        this.add(this.buttonPanel);
        this.add(this.graphDrawer);
        this.pack();
    }
}
