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

/**
 *
 */
public class GraphDrawer extends JPanel implements Observer
{
    public GraphDrawer(Graph graph, NodeDrawer nodeDrawer, EdgeDrawer edgeDrawer)
    {
        super();
        
        this.graph = graph;
        graph.addObserver(this);
        
        this.nodeDrawer = nodeDrawer;
        this.edgeDrawer = edgeDrawer;
        
        this.backgroundImage = null;
    }
    
    protected final Graph graph;
    
    protected final NodeDrawer nodeDrawer;
    protected final EdgeDrawer edgeDrawer;
    
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
    
    private static Image selectedImage = null;
    public void draw(Graphics g)
    {
        // Draw background image
        if(backgroundImage != null)
            g.drawImage(backgroundImage, 0, 0, null);
        
        // Draw edges
        graph.getNodes()
                .forEach(n -> n.getEdges()
                        .forEach(e -> edgeDrawer.draw(g, e)));
        
        // Draw nodes
        graph.getNodes()
                .forEach(n -> nodeDrawer.draw(g, n));
        
        if(selectedImage == null)
            selectedImage = ImageLoader.loadImage("fireline.png");
        
        if(selectedImage != null && this.getName() != null && this.getName().equals("setfire"))
        {
            final int h = 100;
            if(backgroundImage != null)
                g.drawImage(selectedImage, 0, backgroundImage.getHeight(null) - h, backgroundImage.getWidth(null), h, null);
            else
                g.drawImage(selectedImage, 0, this.getParent().getHeight() - h, this.getParent().getWidth(), h, null);
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
