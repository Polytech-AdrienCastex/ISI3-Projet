package sources.view.windows;

import sources.controller.actionmanagers.ActionManager;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 */
public abstract class Window extends JFrame implements Runnable
{
    public Window(ActionManager actionManager)
    {
        super();
        
        this.actionManager = actionManager;
        
        this.setBackground(Color.red);
        this.setLayout(null);
        
        addWindowListener(actionManager);
    }
    
    protected final ActionManager actionManager;
    
    
    public abstract void initialize();
    
    

    @Override
    public void run()
    {
        this.setVisible(true);
    }
}
