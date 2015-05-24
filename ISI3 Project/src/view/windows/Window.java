package view.windows;

import controller.actionmanagers.ActionManager;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 */
public abstract class Window extends JDialog
{
    public Window()
    {
        this(null);
    }
    public Window(ActionManager actionManager)
    {
        super();
        
        this.actionManager = actionManager;
        
        this.setBackground(Color.white);
        this.setLayout(null);
        
        if(actionManager != null)
            addWindowListener(actionManager);
        
        this.setLocationByPlatform(true);
    }
    
    protected final ActionManager actionManager;
    
    
    public abstract void initialize();
    
    

    
    public void showWindow()
    {
        this.setModal(false);
        this.setVisible(true);
    }
    public void showDialog()
    {
        this.setModal(true);
        this.setVisible(true);
    }
}
