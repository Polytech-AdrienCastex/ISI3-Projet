package model.graph;

import model.Point;

/**
 *
 */
public class FireableNode extends Node implements Fireable, Localisable
{
    /**
     * Create a node which can be set on fire.
     * @param id ID of the node.
     * @param graph Graph in which the node is.
     * @param location Location of the node.
     */
    public FireableNode(Integer id, Graph graph, Point location)
    {
        super(id, graph);
        
        this.location = location;
    }
    /**
     * Create a node which can be set on fire.
     * @param graph Graph in which the node is.
     * @param location Location of the node.
     */
    public FireableNode(Graph graph, Point location)
    {
        super(graph);
        
        this.location = location;
    }
    
    /**
     * Intensity of the fire.
     */
    private Double fireIntensity = 0.0;
    /**
     * Location of the node on the map.
     */
    private final Point location;

    /**
     * Get if the node is on fire or not.
     * @return true if the node is on fire.
     */
    @Override
    public Boolean isOnFire()
    {
        return fireIntensity > 0.0;
    }

    /**
     * Get the intensity of the fire.
     * Returns 0.0 if their is no fire.
     * @return the intensity of the fire.
     */
    @Override
    public Double getFireIntensity()
    {
        return fireIntensity;
    }

    /**
     * Set the intensity of the fire.
     * @param intensity Intensity of the fire.
     */
    @Override
    public void setFireIntensity(Double intensity)
    {
        if(intensity < 0.0)
            this.fireIntensity = 0.0;
        else
            this.fireIntensity = intensity;
    }

    /**
     * Get the location of the node.
     * @return the location of the node.
     */
    @Override
    public Point getLocation()
    {
        return location;
    }
    
}
