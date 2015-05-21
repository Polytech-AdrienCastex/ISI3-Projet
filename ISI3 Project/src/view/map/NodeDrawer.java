package view.map;

import java.awt.Color;
import java.awt.Graphics;
import model.elementary.Point;
import model.elementary.Fireable;
import model.elementary.Localisable;
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
                g.setColor(Color.blue);
            
            final int radius = 4;
            
            Point location = ((Localisable)node).getLocation();
            g.fillOval(location.x.intValue() - radius, location.y.intValue() - radius, radius*2, radius*2);
        }
    }
}
