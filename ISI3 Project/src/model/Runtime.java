package model;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class represents a cyclic runtime.
 */
public abstract class Runtime extends TimerTask
{
    /**
     * Internal timer.
     */
    private Timer t = null;
    
    /**
     * Determine if <i>runtime()</i> has to be executed on the next timer tick.
     */
    private boolean running = false;
    
    /**
     * Constructor.
     */
    public Runtime()
    { }
    
    @Override
    public void run()
    {
        if(!running)
            return;
        
        runtime();
    }
    
    /**
     * The action to be performed by this runtime.
     */
    public abstract void runtime();
    
    /**
     * Start timer with an interval time in milliseconds.
     * @param intervalTime Run every intervalTime milliseconds.
     */
    public void start(int intervalTime)
    {
        if(t == null)
        {
            t = new Timer();
            t.schedule(this, 0, intervalTime);
        }
        running = true;
    }
    
    /**
     * Resume the previously paused runtime.
     */
    public void resume()
    {
        running = true;
    }
    
    /**
     * Pause the previously started runtime.
     */
    public void pause()
    {
        running = false;
    }
    
    /**
     * Definitly stop the previously started runtime.
     * <p>
     * It will be impossible to restart/resume this runtime after the class of
     * <i>sotp()</i>.
     */
    public void stop()
    {
        running = false;
        this.cancel();
    }
}
