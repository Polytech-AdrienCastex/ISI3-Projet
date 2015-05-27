package view.menupanel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * This class represent a separator in the button panel.
 */
public class Separator extends JPanel
{
    /**
     * Constructor.
     */
    public Separator()
    {
        super();
        
        this.setSize(1, 50);
    }
    
    /**
     * Draw the separator as a line.
     * @param g Graphics where to draw the separator.
     */
    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(0, 5, 1, 40);
    }

    @Override
    public void paint(Graphics g)
    {
        draw(g);
    }
}
