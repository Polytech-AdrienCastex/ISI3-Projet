package model.graph;

/**
 *
 */
public interface Fireable
{
    public Boolean isOnFire();
    public Double getFireIntensity();
    public void setFireIntensity(Double intensity);
}
