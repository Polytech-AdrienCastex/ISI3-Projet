package model.robot.manager;

import java.util.Arrays;
import java.util.Collection;
import model.Runtime;

/**
 * This class represents a thread of multiple managers.
 */
public class RobotRuntime extends Runtime
{
    /**
     * Managers assigned to this thread.
     */
    private final Collection<Manager> managers;
    
    /**
     * Constructor.
     * @param managers Managers to execute.
     */
    public RobotRuntime(Collection<Manager> managers)
    {
        super();
        
        this.managers = managers;
    }
    /**
     * Constructor.
     * @param managers Managers to execute.
     */
    public RobotRuntime(Manager... managers)
    {
        this(Arrays.asList(managers));
    }
    
    @Override
    public void runtime()
    {
        managers.forEach(m ->
        {
            m.run();
            m.getRobots().forEach(r -> r.run());
        });
    }
}
