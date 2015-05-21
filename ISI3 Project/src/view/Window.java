package view;

import controller.ButtonActionManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFrame;
import model.graph.Graph;
import view.map.EdgeDrawer;
import view.map.GraphDrawer;
import view.map.NodeDrawer;

/**
 *
 */
public class Window extends JFrame implements Runnable
{
    public Window(ButtonActionManager actionManager, Graph graph, String bgImagePath)
    {
        NodeDrawer nd = new NodeDrawer();
        EdgeDrawer ed = new EdgeDrawer();
        
        this.graphDrawer = new GraphDrawer(graph, nd, ed);
        if(bgImagePath != null && !bgImagePath.isEmpty())
            this.graphDrawer.setBackgroundImage(new File(bgImagePath));
        
        this.setSize(new Dimension(600, 600));
        
        getContentPane().setLayout(new BorderLayout(10,10));
        
        getContentPane().add(this.graphDrawer, "Center");
        this.graphDrawer.setBackground(Color.white);
        this.graphDrawer.setSize(this.getSize());
        this.graphDrawer.setPreferredSize(this.getSize());
        
        addWindowListener(actionManager);
    }
    
    private final GraphDrawer graphDrawer;

    @Override
    public void run()
    {
        this.setVisible(true);
    }
}
