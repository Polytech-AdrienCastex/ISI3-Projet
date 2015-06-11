package view;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;
import model.graph.Graph;

/**
 * This interface represent all views with the ability to change from a mode to
 * another.
 */
public interface IModeView extends IView
{
    /**
     * Change the mode of the view.
     * @param mode New mode of the view.
     */
    public void setMode(String mode);
    
    /**
     * Change the graph of the view.
     * @param graph New graph of the view.
     */
    public void setGraph(Graph graph);
    
    /**
     * Change the graph of the view.
     * @param graph New graph of the view.
     * @param backgroundPath  New background from file (with path) for the view.
     */
    public void setGraph(Graph graph, String backgroundPath);
    
    /**
     * Change the graph of the view.
     * @param graph New graph of the view.
     * @param backgroundFile New background from a file for the view.
     */
    public void setGraph(Graph graph, File backgroundFile);
    
    /**
     * Change the graph of the view.
     * @param graph New graph of the view.
     * @param backgroundStream New background for the view.
     */
    public void setGraph(Graph graph, InputStream backgroundStream);
    
    /**
     * Change the graph of the view.
     * @param graph New graph of the view.
     * @param backgroundImage New image in background of the view.
     */
    public void setGraph(Graph graph, Image backgroundImage);
}
