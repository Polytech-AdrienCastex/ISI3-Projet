package view.map;

import java.io.File;
import javax.swing.JFrame;

/**
 *
 */
public class Window extends JFrame implements Runnable
{
    private static final String bgImagePath = null;
    
    public Window()
    {
        NodeDrawer nd = new NodeDrawer();
        EdgeDrawer ed = new EdgeDrawer();
        
        this.graphDrawer = new GraphDrawer(nd, ed);
        if(bgImagePath != null && !bgImagePath.isEmpty())
            this.graphDrawer.setBackgroundImage(new File(bgImagePath));
    }
    
    private final GraphDrawer graphDrawer;

    @Override
    public void run()
    {
        this.setVisible(true);
    }
}
