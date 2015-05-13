package model.graph;

import model.EdgeType;

/**
 *
 */
public class RobotEdge extends Edge implements Valued, Typed, Waterable
{
    public RobotEdge(Node start, Node end)
    {
        super(start, end);
        
        this.underWater = false;
    }
    
    private Boolean underWater;

    @Override
    public Double getValue()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EdgeType getType()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isUnderWater()
    {
        return underWater;
    }

    @Override
    public void setUnderWater(Boolean underWater)
    {
        this.underWater = underWater;
    }
}
