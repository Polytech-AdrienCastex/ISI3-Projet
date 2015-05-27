package view.menupanel;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import view.ImageLoader;

/**
 * This class is a button displayed on a <i>ButtonPanel</i>.
 */
public class Button extends JPanel
{
    /**
     * Constructor.
     * @param actionName Name of the action used in the controller.
     * @param imageName Name of the resource to use as icon.
     */
    public Button(String actionName, String imageName)
    {
        super();
        
        this.setSize(50, 50);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        image = ImageLoader.loadImage(imageName);
        if(image == null)
            System.err.println("Can't load : " + imageName);
        else
            this.setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        
        this.setName(actionName);
    }
    
    /**
     * Icon of the button.
     */
    protected final Image image;
    
    /**
     * Draw the button on the graphics <b>g</b>.
     * @param g Graphics where to draw the button.
     */
    public void draw(Graphics g)
    {
        if(image != null)
            g.drawImage(image, 0, 0, this.getSize().width, this.getSize().height, null);
    }

    @Override
    public void paint(Graphics g)
    {
        draw(g);
    }
}
