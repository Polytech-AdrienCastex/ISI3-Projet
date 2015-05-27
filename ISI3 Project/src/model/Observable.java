package model;

/**
 * This class use <b>java.util.Observable</b> and add the <i>notifyChanges()</i>
 * method to contract the mothods <i>setChanged()</i> and
 * <i>notifyObservers(...)</i>.
 * <p>
 * The aim of this class is to make easier the implementation of the class
 * <b>java.util.Observable</b>.
 */
public class Observable extends java.util.Observable
{
    /**
     * Concat the mothods <i>setChanged()</i> and <i>notifyObservers()</i> of
     * <b>java.util.Observable</b>.
     */
    public void notifyChanges()
    {
        setChanged();
        notifyObservers();
    }
    
    /**
     * Concat the mothods <i>setChanged()</i> and
     * <i>notifyObservers(Object obj)</i> of <b>java.util.Observable</b>.
     * @param obj Object given to the observer.
     */
    public void notifyChanges(Object obj)
    {
        setChanged();
        notifyObservers(obj);
    }
}
