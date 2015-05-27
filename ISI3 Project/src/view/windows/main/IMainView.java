package view.windows.main;

import java.awt.Image;
import view.IModeView;

/**
 * This interface represent the view of the main view.
 */
public interface IMainView extends IModeView
{
    /**
     * Get the map background image used.
     * @deprecated Reserved for internal use and memory space optimization.
     * @return The image loaded representing the background image of the map.
     */
    public Image getGraphBackground();
}
