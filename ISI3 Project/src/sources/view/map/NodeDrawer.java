package sources.view.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sources.model.elementary.Point;
import sources.model.elementary.Fireable;
import sources.model.elementary.Localisable;
import sources.model.graph.Node;
import sources.view.ImageLoader;

/**
 *
 */
public class NodeDrawer
{
    private static Image fireImage = null;
    
    public void draw(Graphics g, Node node)
    {
        if(node instanceof Localisable)
        {
            Point location = ((Localisable)node).getLocation();
            
            if(node instanceof Fireable && ((Fireable)node).isOnFire())
            {
                g.setColor(Color.red);
                
                if(fireImage == null)
                    fireImage = ImageLoader.loadImage("redfire.png");
                    
                if(fireImage != null)
                {
                    g.drawImage(fireImage, location.x.intValue() - fireImage.getWidth(null) / 2, location.y.intValue() - fireImage.getHeight(null) / 2, null);
                }
            }
            else
            {
                g.setColor(Color.blue);
            }
            
            final int radius = 4;
            g.fillOval(location.x.intValue() - radius, location.y.intValue() - radius, radius*2, radius*2);
        }
    }
}
