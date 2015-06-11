package view.map;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.elementary.Point;
import model.graph.Graph;
import view.ImageLoader;
import view.robot.RobotDrawer;

/**
 * Draw a graph.
 */
public class GraphDrawer extends JPanel implements Observer
{
    private final static int AUTO_DRAWER_PERIOD = 250; // ms
    
    /**
     * Constructor.
     * @param graph model graph to draw.
     * @param nodeDrawer node drawer used by this graph drawer.
     * @param edgeDrawer edge drawer used by this graph drawer.
     * @param robotDrawer robot drawer used by this graph drawer.
     */
    public GraphDrawer(Graph graph, NodeDrawer nodeDrawer, EdgeDrawer edgeDrawer, RobotDrawer robotDrawer)
    {
        super();
        
        this.graph = graph;
        graph.addObserver(this);
        
        this.nodeDrawer = nodeDrawer;
        this.edgeDrawer = edgeDrawer;
        this.robotDrawer = robotDrawer;
        
        this.backgroundImage = null;
        
        this.fileSelecteionImage = ImageLoader.loadImage("fireline.png");
        
        if(robotDrawer != null)
            robotDrawer.addObserver(this);
    }
    
    private Runtime runtime = null;

    /**
     * Set the graph model to draw.
     * @param graph graph model
     */
    public void setGraph(Graph graph)
    {
        if(this.graph != null)
            this.graph.deleteObserver(this);
        
        this.graph = graph;
        graph.addObserver(this);
    }
    
    /**
     * Runtime to update the view.
     */
    protected class Runtime extends model.Runtime
    {
        @Override
        public void runtime()
        {
            getParent().repaint();
            if(nodeDrawer != null)
                nodeDrawer.updateResources();
            
            if(robotDrawer != null)
                robotDrawer.updateResources();
        }
    }
    
    /**
     * Graph used by this drawer.
     */
    protected Graph graph = null;
    
    /**
     * Node drawer.
     */
    protected final NodeDrawer nodeDrawer;
    
    /**
     * Edge drawer.
     */
    protected final EdgeDrawer edgeDrawer;
    
    /**
     * Robot drawer.
     */
    protected final RobotDrawer robotDrawer;
    
    /**
     * Background image.
     */
    protected Image backgroundImage;
    
    /**
     * Set the image in the background.
     * @param imageFile file for the image.
     * @return true if loaded with success
     */
    public Boolean setBackgroundImage(File imageFile)
    {
        backgroundImage = ImageLoader.loadImage(imageFile);
        return backgroundImage != null;
    }
    
    /**
     * Set the image in the background. 
     * @param image image to set in background.
     * @return true
     */
    public Boolean setBackgroundImage(Image image)
    {
        this.backgroundImage = image;
        return true;
    }
    
    /**
     * Get the size of the background
     * @return coord x, y
     */
    public Point getBackgroundSize()
    {
        return new Point((double)backgroundImage.getWidth(null), (double)backgroundImage.getHeight(null));
    }
    
    private final Image fileSelecteionImage;
    
    /**
     * Draw the graph
     * @param g graphics
     */
    public void draw(Graphics g)
    {
        // Draw background image
        if(backgroundImage != null)
            g.drawImage(backgroundImage, 0, 0, null);
        
        // Draw edges
        if(edgeDrawer != null)
            graph.getNodes()
                    .forEach(n -> n.getEdges()
                            .forEach(e -> edgeDrawer.draw(g, e)));
        
        // Draw nodes
        if(nodeDrawer != null)
            graph.getNodes()
                    .forEach(n -> nodeDrawer.draw(g, n));
        
        if(fileSelecteionImage != null && this.getName() != null && this.getName().equals("setfire"))
        {
            final int h = 100;
            if(backgroundImage != null)
                g.drawImage(fileSelecteionImage, 0, backgroundImage.getHeight(null) - h, backgroundImage.getWidth(null), h, null);
            else
                g.drawImage(fileSelecteionImage, 0, this.getParent().getHeight() - h, this.getParent().getWidth(), h, null);
        }
        
        // Draw robots
        if(robotDrawer != null)
            robotDrawer.draw(g);
        
        // Start the auto-drawer if not already started
        if(runtime == null)
        {
            runtime = new Runtime();
            runtime.start(AUTO_DRAWER_PERIOD);
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        this.getParent().repaint();
    }

    /**
     * Get the background image.
     * @return background image.
     */
    public Image getBackgroundImage()
    {
        return this.backgroundImage;
    }
}
