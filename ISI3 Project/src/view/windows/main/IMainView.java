package view.windows.main;

import java.awt.Image;
import view.IView;

/**
 *
 */
public interface IMainView extends IView
{
    public Image getGraphBackground();

    public void setMode(String mode);
}
