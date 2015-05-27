package controller.actionmanagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import model.elementary.Localisable;
import model.elementary.Point;
import model.graph.Graph;
import model.graph.Node;
import view.IView;

/**
 * This class represents an action manager to control the events occured on the
 * view.
 * @param <V> View interface to allow the controller and the view to
 * communicate.
 */
public abstract class ActionManager<V extends IView> extends WindowAdapter implements ActionListener, MouseListener
{
    /**
     * Constructor.
     * @param exitProcessusOnClosing <b>true</b> if the program has to stop if
     * a close request is received.
     */
    public ActionManager(boolean exitProcessusOnClosing)
    {
        super();
        
        this.exitProcessusOnClosing = exitProcessusOnClosing;
    }
    
    /**
     * View interface which allows the controller and the view to communicate.
     */
    private V view;

    /**
     * Get the view interface.
     * @return The view interface.
     */
    public V getView()
    {
        return view;
    }
    /**
     * Set the view interface to use.
     * @param view The view interface to use.
     */
    public void setView(V view)
    {
        this.view = view;
    }
    
    /**
     * Determines if the program has to stop if a close request is received.
     */
    private final boolean exitProcessusOnClosing;
    
    /**
     * Action to perform when an ActionEvent is received.
     * @param command Command received.
     * @param e ActionEvent received.
     */
    protected abstract void action(String command, ActionEvent e);
    /**
     * Action to perform when an MouseEvent is received.
     * @param command Command received.
     * @param e MouseEvent received.
     * @param clkLocation Location of the click formated to a <i>Point</i>.
     */
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
    
    
    /**
     * Find a node in the <i>graph</i> on the specified <i>location</i> with a
     * certain <i>radius</i>.
     * <p>
     * The <i>radius</i> specified is used as X and Y radius (not <b>cos</b> and
     * <b>sin</b>), which means the search will be performed in a rectangle
     * instead of a circle.
     * @param graph Graph to serach in to find the node on the specified
     * location.
     * @param location Location where to search the node.
     * @param radius Radius to use as allowed error to find the node in a wider
     * rectangle.
     * @return The node found on the specified location in the specified radius.
     */
    protected static Node findNodeFromLocation(Graph graph, Point location, int radius)
    {
        return graph.getNodes().stream()
                .filter(n -> n instanceof Localisable)
                .filter(n ->
                {
                    Point p = ((Localisable)n).getLocation();
                    return
                            p.x >= location.x - radius &&
                            p.x <= location.x + radius &&
                            p.y >= location.y - radius &&
                            p.y <= location.y + radius;
                })
                .findFirst()
                .orElse(null);
    }
}
