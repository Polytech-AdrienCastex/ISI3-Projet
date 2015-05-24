package view.menupanel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 */
public class Separator extends JPanel
{
    public Separator()
    {
        super();
        
        this.setSize(1, 50);
    }
    
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
