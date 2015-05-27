package model.elementary;

/**
 * This interface represent an object which can be put on fire with a specific
 * intensity.
 */
public interface Fireable
{
    /**
     * Get if the object is on fire.
     * @return <b>true</b> if the fire intensity is superior of 0.
     */
    public Boolean isOnFire();
    
    /**
     * Get the fire intensity.
     * @return The fire intensity.
     */
    public Double getFireIntensity();
    
    /**
     * Set the fire <i>intensity</i>.
     * <p>
     * If the <i>intensity</i> is inferior of 0, the fire intensity will be set
     * to 0.
     * @param intensity Fire intensity.
     */
    public void setFireIntensity(Double intensity);
}
