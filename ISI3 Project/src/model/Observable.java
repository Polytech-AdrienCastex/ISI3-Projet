package model;

/**
 *
 */
public class Observable extends java.util.Observable
{
    public void notifyChanges()
    {
        setChanged();
        notifyObservers();
    }
}
