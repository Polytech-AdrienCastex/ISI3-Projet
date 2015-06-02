package view;

import model.ResourceLoader;

/**
 * This class represents the resource loader of the view.
 */
public class ViewResourceLoader extends ResourceLoader
{
    /**
     * Constructor.
     */
    public ViewResourceLoader()
    {
        super("view/resources/");
    }
}
