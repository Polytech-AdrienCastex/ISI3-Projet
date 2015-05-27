package model.robot.manager;

import java.util.Timer;
import java.util.TimerTask;
import model.item.IItem;
import model.robot.Robot;

/**
 * Thread robot
 */
public class RobotRuntime extends TimerTask {
    private final Manager<Robot<IItem>> m;
    private final Timer t;
    
    public RobotRuntime(Manager m)
    {
        this.m = m;
        this.t = new Timer();
    }
    
    @Override
    public void run() {
        m.run();
     
        for (Robot<IItem> r : m.getRobots())
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
