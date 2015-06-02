package model.robot.manager;

import model.robot.Robot;
import model.Runtime;

/**
 * This class represents a manager thread.
 */
public class RobotRuntime extends Runtime
{
    /**
     * Manager assigned to this thread.
     */
    private final Manager m;
    
    /**
     * Constructor.
     * @param m Manager to execute.
     */
    public RobotRuntime(Manager m)
    {
        super();
        
        this.m = m;
    }
    
    @Override
    public void runtime()
    {
        m.run();
        
        m.getRobots().forEach(r -> r.run());
    }
}
