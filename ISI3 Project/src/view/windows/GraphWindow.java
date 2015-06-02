package view.windows;

import view.menupanel.Separator;
import view.menupanel.ButtonPanel;
import view.menupanel.Button;
import controller.actionmanagers.ActionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import model.ResourceLoader;
import model.elementary.Localisable;
import model.elementary.Point;
import model.graph.Graph;
import model.robot.manager.Manager;
import view.IModeView;
import view.ViewResourceLoader;
import view.map.EdgeDrawer;
import view.map.GraphDrawer;
import view.map.NodeDrawer;
import view.robot.RobotDrawer;

/**
 *
 */
public abstract class GraphWindow extends Window implements IModeView
{
    public GraphWindow(ActionManager actionManager)
    {
        super(actionManager);
        
        this.setResizable(false);
        this.displayRobots = true;
    }
    public GraphWindow(ActionManager actionManager, boolean displayRobots)
    {
        super(actionManager);
        
        this.setResizable(false);
        this.displayRobots = displayRobots;
    }
    
    protected final boolean displayRobots;
    protected GraphDrawer graphDrawer;
    protected ButtonPanel buttonPanel;
    
    protected static ResourceLoader resourceLoader = new ViewResourceLoader();
    
    protected Manager manager = null;
    public void setRobotManager(Manager manager)
    {
        this.manager = manager;
    }
    
    @Override
    public void setGraph(Graph graph)
    {
        setGraph(graph, (Image)null);
    }
    @Override
    public void setGraph(Graph graph, String backgroundPath)
    {
        if(backgroundPath == null)
        {
            setGraph(graph, (Image)null);
            return;
        }
        
        setGraph(graph, resourceLoader.loadStream(backgroundPath));
    }
    @Override
    public void setGraph(Graph graph, InputStream backgroundStream)
    {
        if(backgroundStream == null)
        {
            setGraph(graph, (Image)null);
            return;
        }
        
        try
        {
            setGraph(graph, ImageIO.read(backgroundStream));
        }
        catch(IOException ex)
        {
            setGraph(graph, (Image)null);
        }
    }
    @Override
    public void setGraph(Graph graph, File backgroundFile)
    {
        if(backgroundFile == null)
        {
            setGraph(graph, (Image)null);
            return;
        }
            
        try
        {
            Image image = ImageIO.read(backgroundFile);
            setGraph(graph, image);
        }
        catch (IOException e)
        { }
    }
    @Override
    public void setGraph(Graph graph, Image backgroundImage)
    {
        NodeDrawer nd = new NodeDrawer();
        EdgeDrawer ed = new EdgeDrawer();
        
        if(this.graphDrawer == null)
            this.graphDrawer = new GraphDrawer(graph, nd, ed, displayRobots ? new RobotDrawer("robots/bluerobot.png") : null);
        else
            this.graphDrawer.setGraph(graph);
        if(backgroundImage != null && this.graphDrawer.setBackgroundImage(backgroundImage))
        {
            Point size = this.graphDrawer.getBackgroundSize();
            this.setMinimumSize(new Dimension(size.x.intValue(), size.y.intValue()));
        }
        else
        {
            this.graphDrawer.setBackgroundImage((Image)null);
            
            this.setMinimumSize(new Dimension(
                    (int)graph.getNodes().stream()
                            .filter(n -> n instanceof Localisable)
                            .mapToDouble(n -> ((Localisable)n).getLocation().x)
                            .max()
                            .orElse(600.0) + 100
                    ,
                    (int)graph.getNodes().stream()
                            .filter(n -> n instanceof Localisable)
                            .mapToDouble(n -> ((Localisable)n).getLocation().y)
                            .max()
                            .orElse(600.0) + 100
            ));
        }
    }
    
    protected Button addButton(String action, String imageName)
    {
        return addButton(action, imageName, null);
    }
    protected Button addButton(String action, String imageName, String selectionImageName)
    {
        Button btn = new Button(action, imageName, selectionImageName);
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
        this.graphDrawer.setBackground(Color.white);
        this.graphDrawer.setSize(this.getSize());
        this.graphDrawer.setPreferredSize(this.getSize());
        this.graphDrawer.setLocation(0, 0);
        this.graphDrawer.addMouseListener(this.actionManager);
        
        this.buttonPanel = new ButtonPanel();
        this.buttonPanel.setLocation(0, 0);
        this.buttonPanel.setSize(1000, 1000);
        
        this.add(this.buttonPanel);
        this.add(this.graphDrawer);
        this.pack();
    }
    
    @Override
    public void setMode(String mode)
    {
        System.out.println("MODE : set" + mode);
        this.graphDrawer.setName("set" + mode);
        
        this.buttonPanel.setSelection(mode);
        
        this.repaint();
    }
}
