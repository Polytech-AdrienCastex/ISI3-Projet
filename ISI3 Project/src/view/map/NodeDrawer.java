package view.map;

import java.awt.Color;
import java.awt.Graphics;
import model.Point;
import model.graph.Fireable;
import model.graph.Localisable;
import model.graph.Node;

/**
 *
 */
public class NodeDrawer
{
    public void draw(Graphics g, Node node)
    {
        if(node instanceof Localisable)
        {
            if(node instanceof Fireable && ((Fireable)node).isOnFire())
                g.setColor(Color.red);
            else
                g.setColor(Color.green);
            
            Point location = ((Localisable)node).getLocation();
            g.drawOval(location.x.intValue(), location.y.intValue(), 5, 5);
        }
    }
}
