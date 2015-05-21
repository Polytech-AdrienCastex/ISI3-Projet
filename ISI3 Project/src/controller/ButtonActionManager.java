package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.elementary.Point;
import model.graph.Graph;

/**
 *
 */
public class ButtonActionManager extends WindowAdapter implements ActionListener, MouseListener
{
    public ButtonActionManager(Graph graph)
    {
        super();
        this.graph = graph;
    }
    
    protected Graph graph;
    
    

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String c = e.getActionCommand();

        if(c == null)
            return;
        
        switch(c.toLowerCase())
        {
            case "...":
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        super.windowClosing(e);
        System.exit(0);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        Point p = new Point((double)e.getX(), (double)e.getY());
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
