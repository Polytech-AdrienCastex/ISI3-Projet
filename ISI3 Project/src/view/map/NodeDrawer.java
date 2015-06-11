package view.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import model.elementary.Point;
import model.elementary.Fireable;
import model.elementary.Localisable;
import model.graph.Node;
import view.ImageLoader;

/**
 * This class allow the view to draw nodes from the model.
 */
public class NodeDrawer
{
    /**
     * Contructor.
     */
    public NodeDrawer()
    {
        fireImages = new Image[]
        {
            ImageLoader.loadImage("redfire.png"),
            ImageLoader.loadImage("redfire_2.png"),
            ImageLoader.loadImage("redfire_3.png")
        };
    }
    
    /**
     * <b>true</b> if the class has to draw the number of the node on the
     * screen. <b>false</b> otherwise.
     */
    private final static boolean DRAW_NODE_ID = false;
    
    /**
     * Images representing the fire.
     */
    private Image[] fireImages;
    
    /**
     * Index of the animated resources.
     */
    private int resourcesIndex = 0;
    
    /**
     * Update the index of the animated resources.
     */
    public void updateResources()
    {
        resourcesIndex++;
    }
    
    /**
     * Draw the <i>node</i> on the graphics <i>g</i>.
     * <p>
     * An nodes is represented by a circle centered on it's location. If the
     * node is not <i>Localisable</i>, then the node will not be drawn. If a
     * node is on fire, it will display, under the circle, the image
     * representing the fire.
     * @param g The graphics where to draw.
     * @param node The node to draw.
     */
    public void draw(Graphics g, Node node)
    {
        if(node instanceof Localisable)
        {
            Point location = ((Localisable)node).getLocation();
            
            if(node instanceof Fireable && ((Fireable)node).isOnFire())
            {
                g.setColor(Color.red);
                
                Image fire = fireImages[resourcesIndex % fireImages.length];
                g.drawImage(fire, location.x.intValue() - fire.getWidth(null) / 2, location.y.intValue() - fire.getHeight(null) / 2, null);
            }
            else
            {
                g.setColor(Color.blue);
            }
            
            final int radius = 4;
            g.fillOval(location.x.intValue() - radius, location.y.intValue() - radius, radius*2, radius*2);
            
            // Draw node ID
            if(DRAW_NODE_ID)
            {
                g.setColor(Color.black);
                g.drawString(String.valueOf(node.getId()), location.x.intValue() - 5, location.y.intValue());
            }
        }
    }
}
