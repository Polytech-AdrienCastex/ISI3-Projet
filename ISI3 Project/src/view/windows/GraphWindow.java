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
import view.IModeView;
import view.ViewResourceLoader;
import view.map.EdgeDrawer;
import view.map.GraphDrawer;
import view.map.NodeDrawer;
import view.robot.RobotDrawer;

/**
 * Abstract class view to manage the graph.
 */
public abstract class GraphWindow extends Window implements IModeView
{

    /**
     * Contructor with controller.
     * @param actionManager controller for this view.
     */
    public GraphWindow(ActionManager actionManager)
    {
        super(actionManager);
        
        this.setResizable(false);
        this.displayRobots = true;
    }

    /**
     * Contructor with controller and boolean to display robots.
     * @param actionManager controller for this view.
     * @param displayRobots boolean to display robots or not
     */
    public GraphWindow(ActionManager actionManager, boolean displayRobots)
    {
        super(actionManager);
        
        this.setResizable(false);
        this.displayRobots = displayRobots;
    }
    
    /**
     * If true display robots else hide them.
     */
    protected final boolean displayRobots;

    /**
     * View to draw the graph.
     */
    protected GraphDrawer graphDrawer;

    /**
     * View for the panel of button.
     */
    protected ButtonPanel buttonPanel;
    
    /**
     * Ressources loader
     */
    protected static ResourceLoader resourceLoader = new ViewResourceLoader();
    
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
        NodeDrawer nd = getNodeDrawer();
        EdgeDrawer ed = getEdgeDrawer();
        
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
    
    /**
     * Get a new Edge Drawer
     * @return new Edge drawer created
     */
    protected EdgeDrawer getEdgeDrawer()
    {
        return new EdgeDrawer();
    }
    
    /**
     * Get a new node drawer
     * @return new node drawer created
     */
    protected NodeDrawer getNodeDrawer()
    {
        return new NodeDrawer();
    }
    
    /**
     * Add a new button to the button panel of this view.
     * @param action text action command.
     * @param imageName text name of the image.
     * @return new button added to the button panel.
     */
    protected Button addButton(String action, String imageName)
    {
        return addButton(action, imageName, null);
    }

    /**
     * Add a new button to the button panel of this view.
     * @param action text action command.
     * @param imageName text name of the image.
     * @param selectionImageName text name of the image when selected. 
     * @return new button added to the button panel.
     */
    protected Button addButton(String action, String imageName, String selectionImageName)
    {
        Button btn = new Button(action, imageName, selectionImageName);
        btn.addMouseListener(this.actionManager);
        this.buttonPanel.add(btn);
        return btn;
    }

    /**
     * Add a separator to the button panel.
     * @return New separatora added.
     */
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
