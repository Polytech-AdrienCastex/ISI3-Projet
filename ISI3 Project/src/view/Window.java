package view;

import controller.ButtonActionManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFrame;
import model.elementary.Point;
import model.graph.Graph;
import view.map.EdgeDrawer;
import view.map.GraphDrawer;
import view.map.NodeDrawer;

/**
 *
 */
public class Window extends JFrame implements Runnable, IView
{
    public Window(ButtonActionManager actionManager, String bgImagePath)
    {
        this.setBackground(Color.white);
        
        getContentPane().setLayout(new BorderLayout(10,10));
        
        setGraph(new Graph());
        initialize();
        
        addWindowListener(actionManager);
    }
    
    private GraphDrawer graphDrawer;
    
    public void setGraph(Graph graph)
    {
        setGraph(graph, null);
    }
    public void setGraph(Graph graph, String backgroundPath)
    {
        NodeDrawer nd = new NodeDrawer();
        EdgeDrawer ed = new EdgeDrawer();
        
        this.graphDrawer = new GraphDrawer(new Graph(), nd, ed);
        if(backgroundPath != null && !backgroundPath.isEmpty())
        {
            this.graphDrawer.setBackgroundImage(new File(backgroundPath));
            Point size = this.graphDrawer.getBackgroundSize();
            this.setMinimumSize(new Dimension(size.x.intValue(), size.y.intValue()));
        }
        else
            this.setSize(new Dimension(600, 600));
    }
    
    
    protected void initialize()
    {
        getContentPane().add(this.graphDrawer, "Center");
        this.graphDrawer.setBackground(Color.white);
        this.graphDrawer.setSize(this.getSize());
        this.graphDrawer.setPreferredSize(this.getSize());
    }
    
    

    @Override
    public void run()
    {
        this.setVisible(true);
    }
}
