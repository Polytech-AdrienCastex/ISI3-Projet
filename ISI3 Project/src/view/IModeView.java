package view;

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
}
