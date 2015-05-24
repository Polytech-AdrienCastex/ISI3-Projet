package sources.view.map;

import java.awt.Color;
import java.awt.Graphics;
import sources.model.elementary.Point;
import sources.model.graph.Edge;
import sources.model.elementary.Fireable;
import sources.model.elementary.Localisable;
import sources.model.elementary.Typed;
import sources.model.elementary.Waterable;

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
                        color = Color.blue;
                        break;
                        
                    case Escarpe:
                        color = Color.red;
                        break;
                }
        
        g.setColor(color);
        
        Point start = ((Localisable)edge.getStartNode()).getLocation();
        Point end = ((Localisable)edge.getStopNode()).getLocation();
        
        int offset = 0;
        g.drawLine(start.x.intValue() + offset, start.y.intValue() + offset, end.x.intValue() + offset, end.y.intValue() + offset);/*
        offset = 1;
        g.drawLine(start.x.intValue() + offset, start.y.intValue() + offset, end.x.intValue() + offset, end.y.intValue() + offset);
        offset = -1;
        g.drawLine(start.x.intValue() + offset, start.y.intValue() + offset, end.x.intValue() + offset, end.y.intValue() + offset);*/
    }
}
