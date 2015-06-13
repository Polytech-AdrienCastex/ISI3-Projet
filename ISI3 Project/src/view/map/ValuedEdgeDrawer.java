package view.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import model.elementary.Point;
import model.graph.Edge;
import model.elementary.Localisable;
import model.elementary.Valued;

/**
 * This class allow the view to draw edges from the model.
 */
public class ValuedEdgeDrawer extends EdgeDrawer
{
    /**
     * Margin of the value box.
     */
    protected final static int VALUE_MARGIN = 2;
    
    @Override
    public void draw(Graphics g, Edge edge)
    {
        super.draw(g, edge);
        
        if(!(edge instanceof Valued))
            return;
        
        if(!(edge.getStartNode() instanceof Localisable) || !(edge.getStopNode() instanceof Localisable))
            return;
        
        g.setFont(new Font(g.getFont().getFontName(), g.getFont().getStyle(), 9));
        
        String value = String.valueOf(((Valued)edge).getValue());
        
        Point start = ((Localisable)edge.getStartNode()).getLocation();
        Point end = ((Localisable)edge.getStopNode()).getLocation();
        
        Rectangle2D valueBound = g.getFontMetrics().getStringBounds(value, g);
        Point textLocation = new Point((start.x + end.x) / 2 - valueBound.getCenterX(), (start.y + end.y) / 2 - valueBound.getCenterY());
        
        g.setColor(new Color(50, 50, 50, 150));
        g.fillRect(textLocation.x.intValue() - VALUE_MARGIN, textLocation.y.intValue() - (int)valueBound.getHeight(), (int)valueBound.getWidth() + VALUE_MARGIN * 2, (int)valueBound.getHeight() + VALUE_MARGIN * 2);
        g.setColor(Color.WHITE);
        g.drawString(value, textLocation.x.intValue(), textLocation.y.intValue());
    }
}
