package model.graph;

/**
 *
 */
public interface Fireable
{
    public Boolean isOnFire();
    public void setOnFire();
    public void clearFire();
    public Double getFireIntesity();
    public void setFireIntesity(Double intensity);
}
