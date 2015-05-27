package view.menupanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * This class is the button panel which contains the buttons.
 */
public class ButtonPanel extends JPanel
{
    /**
     * Constructor.
     */
    public ButtonPanel()
    {
        this(0);
    }
    /**
     * Constructor.
     * @param xOffset Left offset of the first button displayed.
     */
    public ButtonPanel(int xOffset)
    {
        super();
        
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
        
        currentX = xOffset;
    }
    
    /**
     * Current offset of the buttons.
     * <p>
     * When a button is added, the currentX is increased to know here will the
     * added button be displayed.
     */
    private int currentX;

    @Override
    public void paint(Graphics g)
    {
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, this.getSize().width, 50);
        
        super.paint(g);
    }

    @Override
    public Component add(Component cmpnt)
    {
        cmpnt.setLocation(currentX, 0);
        currentX += cmpnt.getSize().width + 10;
        return super.add(cmpnt);
    }
}
