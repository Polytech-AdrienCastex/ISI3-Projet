package view.windows;

import controller.actionmanagers.ActionManager;
import java.awt.Color;
import javax.swing.JDialog;
import view.IView;

/**
 * This class represents a window.
 */
public abstract class Window extends JDialog implements IView
{
    /**
     * Constructor.
     */
    public Window()
    {
        this(null);
    }
    /**
     * Constructor.
     * @param actionManager Action manager (controller class) linked to this
     * window.
     */
    public Window(ActionManager actionManager)
    {
        super((JDialog)null);
        
        this.actionManager = actionManager;
        
        this.setBackground(Color.white);
        this.setLayout(null);
        
        if(actionManager != null)
            addWindowListener(actionManager);
        
        this.setLocationByPlatform(true);
    }
    
    /**
     * Action manager linked to this window.
     */
    protected final ActionManager actionManager;
    
    /**
     * Initialize the components of the window.
     */
    public abstract void initialize();
    
    /**
     * Show the window as a separated window.
     */
    public void showWindow()
    {
        this.setModal(false);
        this.setVisible(true);
    }
    /**
     * Show the window as a dependent window (dialog).
     */
    public void showDialog()
    {
        this.setModal(true);
        this.setVisible(true);
    }
}
