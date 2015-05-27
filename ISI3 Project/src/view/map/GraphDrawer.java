package view.map;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
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
    
    public GraphDrawer(Graph graph, NodeDrawer nodeDrawer, EdgeDrawer edgeDrawer, RobotDrawer robotDrawer)
    {
        super();
        
        this.graph = graph;
        graph.addObserver(this);
        
        this.nodeDrawer = nodeDrawer;
        this.edgeDrawer = edgeDrawer;
        this.robotDrawer = robotDrawer;
        
        this.backgroundImage = null;
        
        this.selectedImage = ImageLoader.loadImage("fireline.png");
        
        if(robotDrawer != null)
            robotDrawer.addObserver(this);
    }
    
    private Runtime runtime = null;
    protected class Runtime extends TimerTask
    {
        /**
         * Internal timer.
         */
        private final Timer t;

        /**
         * Constructor.
         */
        public Runtime()
        {
            this.t = new Timer();
        }

        @Override
        public void run()
        {
            getParent().repaint();
            nodeDrawer.updateResources();
            robotDrawer.updateResources();
        }    

        /**
         * Start timer with an interval time in milliseconds
         * @param intervalTime Run every intervalTime milliseconds
         */
        public void start(int intervalTime)
        {
            t.schedule(this, 0, intervalTime); 
        }
    }
    
    protected final Graph graph;
    
    protected final NodeDrawer nodeDrawer;
    protected final EdgeDrawer edgeDrawer;
    protected final RobotDrawer robotDrawer;
    
    protected Image backgroundImage;
    
    public Boolean setBackgroundImage(File imageFile)
    {
        backgroundImage = ImageLoader.loadImage(imageFile);
        return backgroundImage != null;
    }
    public Boolean setBackgroundImage(Image image)
    {
        this.backgroundImage = image;
        return true;
    }
    
    public Point getBackgroundSize()
    {
        return new Point((double)backgroundImage.getWidth(null), (double)backgroundImage.getHeight(null));
    }
    
    private final Image selectedImage;
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
        
        if(selectedImage != null && this.getName() != null && this.getName().equals("setfire"))
        {
            final int h = 100;
            if(backgroundImage != null)
                g.drawImage(selectedImage, 0, backgroundImage.getHeight(null) - h, backgroundImage.getWidth(null), h, null);
            else
                g.drawImage(selectedImage, 0, this.getParent().getHeight() - h, this.getParent().getWidth(), h, null);
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

    public Image getBackgroundImage()
    {
        return this.backgroundImage;
    }
}
