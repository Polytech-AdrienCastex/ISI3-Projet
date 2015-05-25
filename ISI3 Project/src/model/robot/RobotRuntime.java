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
    
    /**
     * Start timer with an interval time in milliseconds
     * @param intervalTime Run every intervalTime milliseconds
     */
    public void start(int intervalTime) {
        t.schedule(this, 0, intervalTime); 
    }
}
