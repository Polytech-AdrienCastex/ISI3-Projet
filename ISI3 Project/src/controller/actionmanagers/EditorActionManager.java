package controller.actionmanagers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import model.elementary.Point;
import model.graph.Graph;

/**
 *
 */
public class EditorActionManager extends ActionManager
{
    public EditorActionManager(Graph graph)
    {
        super(false);
        this.graph = graph;
    }
    
    protected Graph graph;

    @Override
    protected void action(String command, ActionEvent e)
    {
        switch(command)
        {
            case "...":
                //...
                break;
        }
    }

    @Override
    protected void action(String command, MouseEvent e, Point clkLocation)
    {
        switch(command)
        {
            case "...":
                //...
                break;
        }
    }
}
