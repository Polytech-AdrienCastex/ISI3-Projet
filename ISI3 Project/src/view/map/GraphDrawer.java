package view.map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.elementary.Point;
import model.graph.Graph;
import view.ImageLoader;

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
    
    private Image image;
    
    public Boolean setBackgroundImage(File imageFile)
    {
        image = ImageLoader.loadImage(imageFile);
        return image != null;
    }
    public Boolean setBackgroundImage(Image image)
    {
        this.image = image;
        return true;
    }
    
    public Point getBackgroundSize()
    {
        return new Point((double)image.getWidth(null), (double)image.getHeight(null));
    }
    
    public void draw(Graphics g)
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
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        this.repaint();
    }

    public Image getBackgroundImage()
    {
        return this.image;
    }
}
