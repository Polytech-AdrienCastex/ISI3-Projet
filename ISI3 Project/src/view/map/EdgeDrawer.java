package view.map;

import java.awt.Color;
import java.awt.Graphics;
import model.elementary.Point;
import model.graph.Edge;
import model.elementary.Localisable;
import model.elementary.Typed;
import model.elementary.Waterable;

/**
 * This class allow the view to draw edges from the model.
 */
public class EdgeDrawer
{
    /**
     * Size of the line representing the edge.
     */
    private final static int LINE_SIZE = 1;
    
    /**
     * Draw the <i>edge</i> on the graphics <i>g</i>.
     * <p>
     * An edge is represented by a line (or many depending <i>LINE_SIZE</i>
     * value) from the first node to the other one.
     * @param g The graphics where to draw.
     * @param edge The edge to draw.
     */
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
        
        g.drawLine(start.x.intValue(), start.y.intValue(), end.x.intValue(), end.y.intValue());
        
        // Draw bigger lines than 1
        for(int offset = 1; offset < LINE_SIZE; offset++)
        {
            g.drawLine(start.x.intValue() + offset, start.y.intValue() + offset, end.x.intValue() + offset, end.y.intValue() + offset);
            g.drawLine(start.x.intValue() - offset, start.y.intValue() - offset, end.x.intValue() - offset, end.y.intValue() - offset);
        }
    }
}
