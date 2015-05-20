package view.map;

import java.awt.Color;
import java.awt.Graphics;
import model.Point;
import model.graph.Edge;
import model.graph.Fireable;
import model.graph.Localisable;
import model.graph.Typed;
import model.graph.Waterable;

/**
 *
 */
public class EdgeDrawer
{
    public void draw(Graphics g, Edge edge)
    {
        if(!(edge.getStartNode() instanceof Localisable) || !(edge.getStopNode() instanceof Localisable))
            return;
        
        Color color = Color.black;
        
        if(edge instanceof Waterable && ((Waterable)edge).isUnderWater())
            color = Color.blue;
        else
            if(edge instanceof Typed)
                switch(((Typed)edge).getType())
                {
                    case Plat:
                        color = Color.green;
                        break;
                        
                    case Escarpe:
                        color = Color.ORANGE;
                        break;
                }
        
        g.setColor(color);
        
        Point start = ((Localisable)edge.getStartNode()).getLocation();
        Point end = ((Localisable)edge.getStopNode()).getLocation();
        g.drawLine(start.x.intValue(), start.y.intValue(), end.x.intValue(), end.y.intValue());
    }
}
