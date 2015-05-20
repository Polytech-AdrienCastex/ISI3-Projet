package view.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFrame;
import model.graph.Graph;

/**
 *
 */
public class Window extends JFrame implements Runnable
{
    private static final String bgImagePath = null;
    
    public Window(Graph graph)
    {
        NodeDrawer nd = new NodeDrawer();
        EdgeDrawer ed = new EdgeDrawer();
        
        this.graphDrawer = new GraphDrawer(graph, nd, ed);
        if(bgImagePath != null && !bgImagePath.isEmpty())
            this.graphDrawer.setBackgroundImage(new File(bgImagePath));
        
        
        getContentPane().setLayout(new BorderLayout(10,10));
        
        getContentPane().add(this.graphDrawer, "Center");
        this.graphDrawer.setBackground(Color.white);
        this.graphDrawer.setSize(new Dimension(600, 400));
        this.graphDrawer.setPreferredSize(new Dimension(600, 400));
    }
    
    private final GraphDrawer graphDrawer;

    @Override
    public void run()
    {
        this.setVisible(true);
    }
}
