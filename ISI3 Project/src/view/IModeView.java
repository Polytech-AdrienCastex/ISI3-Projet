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
    
    public void setGraph(Graph graph);
    public void setGraph(Graph graph, String backgroundPath);
    public void setGraph(Graph graph, File backgroundFile);
    public void setGraph(Graph graph, InputStream backgroundStream);
    public void setGraph(Graph graph, Image backgroundImage);
}
