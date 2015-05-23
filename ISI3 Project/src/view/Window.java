package view;

import controller.actionmanagers.ActionManager;
import controller.actionmanagers.MainActionManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
