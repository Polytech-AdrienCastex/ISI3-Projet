package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 */
public class ButtonPanel extends JPanel
{
    public ButtonPanel()
    {
        this(0);
    }
    public ButtonPanel(int xOffset)
    {
        super();
        
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
        
        currentX = xOffset;
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, this.getSize().width, 50);
        
        super.paint(g);
    }
    
    private int currentX;

    @Override
    public Component add(Component cmpnt)
    {
        cmpnt.setLocation(currentX, 0);
        currentX += cmpnt.getSize().width + 10;
        return super.add(cmpnt);
    }
}
