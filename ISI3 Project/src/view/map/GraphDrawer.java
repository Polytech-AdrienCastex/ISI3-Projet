package view.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.graph.Graph;

/**
 *
 */
public class GraphDrawer
{
    public GraphDrawer(NodeDrawer nodeDrawer, EdgeDrawer edgeDrawer)
    {
        this.nodeDrawer = nodeDrawer;
        this.edgeDrawer = edgeDrawer;
        
        this.image = null;
    }
    
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
}
