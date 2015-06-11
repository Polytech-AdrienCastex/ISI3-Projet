package model;

import java.util.Collection;
import java.util.Iterator;
import javafx.util.Pair;

/**
 * This class represents a collection which can be observed.
 * @param <T>
 */
public class ObservableCollection<T> extends Observable implements Collection<T>
{
    /**
     * Constructor
     * @param collection collection to convert in observable. 
     */
    public ObservableCollection(Collection<T> collection)
    {
        this.collection = collection;
    }
    /**
     * Collection 
     */
    protected final Collection<T> collection;

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return collection.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    @Override
    public boolean add(T e) {
        boolean result = collection.add(e);
        notifyChanges(new Pair<String, Object>("add", e));
        return result;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = collection.remove(o);
        notifyChanges(new Pair<String, Object>("remove", o));
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return collection.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return collection.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = collection.removeAll(c);
        notifyChanges();
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = collection.retainAll(c);
        notifyChanges();
        return result;
    }

    @Override
    public void clear() {
        collection.clear();
        notifyChanges();
    }
}
