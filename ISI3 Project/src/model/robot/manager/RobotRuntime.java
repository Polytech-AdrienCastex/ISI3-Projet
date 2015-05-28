package model.robot.manager;

import java.util.Timer;
import java.util.TimerTask;
import model.robot.Robot;

/**
 * Thread robot
 */
public class RobotRuntime extends TimerTask {
    private final Manager<Robot> m;
    private Timer t;
    
    public RobotRuntime(Manager m)
    {
        this.m = m;
        this.t = new Timer();
    }
    
    @Override
    public void run() {
    }    
    
    /**
     * Start timer with an interval time in milliseconds
     * @param intervalTime Run every intervalTime milliseconds
     */
    public void start(int intervalTime) {
        t.purge();
        t.schedule(new TimerTask()
        {

            @Override
            public void run() {
        m.run();
     
        for (Robot r : m.getRobots())
        {
            r.run();
        }
            }
        }, 0, intervalTime); 
    }
    
    public void stop()
    {
        t.cancel();
    }
}
