package view.map;

import java.awt.Color;
import java.awt.Graphics;
import model.elementary.Point;
import model.graph.Edge;
import model.elementary.Localisable;
import model.elementary.Typed;
import model.elementary.Valued;
import model.elementary.Waterable;

/**
 * This class allow the view to draw edges from the model.
 */
public class EdgeDrawer
{
    private final static Color DEFAULT_COLOR = Color.BLACK;
    private final static Color UNDER_WATER_COLOR = Color.cyan;
    private final static Color PLAT_COLOR = Color.BLACK;
    private final static Color ESCARPE_COLOR = Color.MAGENTA;
    
    /**
     * Size of the line representing the edge.
     */
    private final static int LINE_SIZE = 2;
    
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
        
        Color color = DEFAULT_COLOR;
        
        if(edge instanceof Waterable && ((Waterable)edge).isUnderWater())
            color = UNDER_WATER_COLOR;
        else
            if(edge instanceof Typed)
                switch(((Typed)edge).getType())
                {
                    case Plat:
                        color = PLAT_COLOR;
                        break;
                        
                    case Escarpe:
                        color = ESCARPE_COLOR;
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
        
        /*
        if(edge instanceof Valued)
            g.drawString(String.valueOf(((Valued)edge).getValue()), Math.abs(start.x.intValue() - end.x.intValue()) / 2 + Math.min(start.x.intValue(), end.x.intValue()), Math.abs(start.y.intValue() - end.y.intValue()) / 2 + Math.min(start.y.intValue(), end.y.intValue()));
        */
    }
}
