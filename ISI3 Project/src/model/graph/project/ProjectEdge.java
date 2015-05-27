package model.graph.project;

import model.elementary.Valued;
import model.elementary.Typed;
import model.elementary.Waterable;
import model.SurfaceType;
import model.graph.Edge;
import model.graph.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents an edge which group some interfaces like <i>Valued</i>,
 * <i>Typed</i> and <i>Waterable</i> to make an edge fully representing the
 * usable edges of the project.
 */
public class ProjectEdge extends Edge implements Valued, Typed, Waterable
{
    /**
     * Create an edge which can be valued, typed and under water.
     * @param start Start node.
     * @param end End node.
     * @param type Type of the edge.
     * @param value Value of the edge.
     */
    public ProjectEdge(Node start, Node end, SurfaceType type, Double value)
    {
        super(start, end);
        
        this.underWater = false;
        this.type = type;
        this.value = value;
    }
    
    /**
     * Define if the edge is under water.
     */
    private Boolean underWater;
    /**
     * Type of the edge.
     */
    private final SurfaceType type;
    /**
     * Value of the edge.
     */
    private final Double value;

    /**
     * Get the value of the edge.
     * @return value of the edge.
     */
    @Override
    public Double getValue()
    {
        return value;
    }

    /**
     * Get the type of the edge.
     * @return the type of the edge.
     */
    @Override
    public SurfaceType getType()
    {
        return type;
    }

    /**
     * Get if the edge is under water.
     * @return true if the edge is under water.
     */
    @Override
    public Boolean isUnderWater()
    {
        return underWater;
    }

    /**
     * Set if the edge is under water or not.
     * @param underWater true if the edge is under water.
     */
    @Override
    public void setUnderWater(Boolean underWater)
    {
        this.underWater = underWater;
        notifyChanges();
    }
    
    @Override
    public Element toXML(Document elementBuilder)
    {
        Element element = super.toXML(elementBuilder);
        element.setAttribute("type", getType().toString().toUpperCase());
        return element;
    }
}
