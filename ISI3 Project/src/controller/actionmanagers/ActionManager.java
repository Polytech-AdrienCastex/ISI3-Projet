package controller.actionmanagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.elementary.Point;

/**
 *
 */
public abstract class ActionManager<V> extends WindowAdapter implements ActionListener, MouseListener
{
    public ActionManager(boolean exitProcessusOnClosing)
    {
        super();
        
        this.exitProcessusOnClosing = exitProcessusOnClosing;
    }
    
    private V view;

    public V getView()
    {
        return view;
    }
    public void setView(V view)
    {
        this.view = view;
    }
    
    private final boolean exitProcessusOnClosing;
    
    protected abstract void action(String command, ActionEvent e);
    protected abstract void action(String command, MouseEvent e, Point clkLocation);

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        if(command == null)
            return;
        
        action(command.trim().toLowerCase(), e);
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        super.windowClosing(e);
        
        if(exitProcessusOnClosing)
            System.exit(0);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        String command = e.getComponent().getName();

        if(command == null)
            return;
        
        Point p = new Point((double)e.getX(), (double)e.getY());
        action(command.trim().toLowerCase(), e, p);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    { }

    @Override
    public void mouseEntered(MouseEvent e)
    { }

    @Override
    public void mouseExited(MouseEvent e)
    { }

    @Override
    public void mousePressed(MouseEvent e)
    { }
}
