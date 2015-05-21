package view.map;

import java.awt.Color;
import java.awt.Graphics;
import model.elementary.Point;
import model.graph.Edge;
import model.elementary.Fireable;
import model.elementary.Localisable;
import model.elementary.Typed;
import model.elementary.Waterable;

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
