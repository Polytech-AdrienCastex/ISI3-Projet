package model.robot;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Thread robot
 */
public class RobotRuntime extends TimerTask {
    private Manager m;
    private final Timer t;
    
    public RobotRuntime(Manager m)
    {
        this.m = m;
        this.t = new Timer();
    }
    
    @Override
    public void run() {
        for (Robot r : m.getRobots())
        {
            r.run();
        }
    }    
    
    public void start() {
        t.schedule(this, 0, 1000); //mise à jour toute les secondes
    }
}
