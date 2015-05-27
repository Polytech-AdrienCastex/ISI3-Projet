package view.robot;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import java.util.Observer;
import model.Observable;
import model.elementary.Point;
import model.elementary.Localisable;
import model.graph.Node;
import model.robot.Robot;
import view.ImageLoader;

/**
 * This class allow the view to draw robots.
 */
public class RobotDrawer extends Observable implements Observer
{
    /**
     * Constructor.
     * @param defaultRobot 
     */
    public RobotDrawer(String defaultRobot)
    {
        Robot.getRobotList().addObserver(this);
        
        this.defaultRobotImage = ImageLoader.loadImage(defaultRobot);
        this.robots = Robot.getRobotList();
    }
    
    private final Collection<Robot> robots;
    private final Image defaultRobotImage;
    
    public void draw(Graphics g, Robot robot)
    {
        Node currentNode = robot.getCurrentNode();
        
        if(currentNode instanceof Localisable)
        {
            Point location = ((Localisable)currentNode).getLocation();
            
            if(defaultRobotImage != null)
                g.drawImage(defaultRobotImage, location.x.intValue() - defaultRobotImage.getWidth(null) / 2, location.y.intValue() - defaultRobotImage.getHeight(null) / 2, null);
        }
    }
    
    public void draw(Graphics g)
    {
        robots.forEach(r -> draw(g, r));
    }

    @Override
    public void update(java.util.Observable o, Object arg)
    {
        notifyChanges();
    }
}
