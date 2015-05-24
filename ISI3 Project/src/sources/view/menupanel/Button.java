package sources.view.menupanel;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import sources.view.ImageLoader;

/**
 *
 */
public class Button extends JPanel
{
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
    
    protected Image image;
    
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
