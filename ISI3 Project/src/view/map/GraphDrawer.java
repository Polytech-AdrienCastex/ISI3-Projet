package view.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.elementary.Point;
import model.graph.Graph;

/**
 *
 */
public class GraphDrawer extends JPanel implements Observer
{
    public GraphDrawer(Graph graph, NodeDrawer nodeDrawer, EdgeDrawer edgeDrawer)
    {
        super();
        
        this.graph = graph;
        
        this.nodeDrawer = nodeDrawer;
        this.edgeDrawer = edgeDrawer;
        
        this.image = null;
    }
    
    protected final Graph graph;
    
    private final NodeDrawer nodeDrawer;
    private final EdgeDrawer edgeDrawer;
    
    private BufferedImage image;
    
    public Boolean setBackgroundImage(File imageFile)
    {
        try
        {
            image = ImageIO.read(imageFile);
            return true;
        }
        catch (IOException e)
        {
            return false;
        }
    }
    
    public Point getBackgroundSize()
    {
        return new Point((double)image.getWidth(), (double)image.getHeight());
    }
    
    public void draw(Graphics g, Graph graph)
    {
        // Draw background image
        if(image != null)
            g.drawImage(image, 0, 0, null);
        
        // Draw edges
        graph.getNodes()
                .forEach(n -> n.getEdges()
                        .forEach(e -> edgeDrawer.draw(g, e)));
        
        // Draw nodes
        graph.getNodes()
                .forEach(n -> nodeDrawer.draw(g, n));
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        draw(g, graph);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        this.repaint();
    }
}
