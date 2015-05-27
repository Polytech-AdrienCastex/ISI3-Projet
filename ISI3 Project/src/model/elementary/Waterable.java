package model.elementary;

/**
 * This interface represents an object which can under water.
 */
public interface Waterable
{
    /**
     * Get if the current object is under water.
     * @return <b>true</b> if the object is under water.
     */
    public Boolean isUnderWater();
    
    /**
     * Set if the object is under water.
     * @param underWater <b>true</b> if the object is under water. <b>false</b>
     * otherwise.
     */
    public void setUnderWater(Boolean underWater);
}
