package view.robot;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import java.util.Observer;
import javafx.util.Pair;
import model.Observable;
import model.ObservableCollection;
import model.elementary.Fireable;
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
     * @param wateringImage 
     */
    public RobotDrawer(String defaultRobot)
    {
        Robot.getRobotList().addObserver(this);
        
        this.defaultRobotImage = ImageLoader.loadImage(defaultRobot);
        this.wateringImages = new Image[]
        {
            ImageLoader.loadImage("cloud.png"),
            ImageLoader.loadImage("cloud_2.png"),
            ImageLoader.loadImage("cloud.png"),
            ImageLoader.loadImage("cloud_3.png")
        };
                
        this.robots = Robot.getRobotList();
        this.robots.forEach(r -> r.addObserver(this));
    }
    
    private final Collection<Robot> robots;
    private final Image defaultRobotImage;
    private final Image[] wateringImages;
    
    /**
     * Index of the animated resources.
     */
    private int resourcesIndex = 0;
    
    /**
     * Update the index of the animated resources.
     */
    public void updateResources()
    {
        resourcesIndex++;
    }
    
    public void draw(Graphics g, Robot robot)
    {
        Node currentNode = robot.getCurrentNode();
        
        if(currentNode instanceof Localisable)
        {
            Point location = ((Localisable)currentNode).getLocation();
            
            if(defaultRobotImage != null)
                g.drawImage(defaultRobotImage, location.x.intValue() - defaultRobotImage.getWidth(null) / 2, location.y.intValue() - defaultRobotImage.getHeight(null) / 2, null);
            
            if(currentNode instanceof Fireable && ((Fireable)currentNode).isOnFire())
            {
                Image wateringImage = wateringImages[resourcesIndex % wateringImages.length];
                g.drawImage(wateringImage, location.x.intValue() - wateringImage.getWidth(null) / 2, location.y.intValue() - wateringImage.getHeight(null) / 2, null);
            }
        }
    }
    
    public void draw(Graphics g)
    {
        robots.forEach(r -> draw(g, r));
    }

    @Override
    public void update(java.util.Observable o, Object arg)
    {
        if(arg instanceof Pair)
        {
            Pair<String, Object> param = (Pair<String, Object>)arg;
            if(param.getValue() instanceof Observable)
            {
                Observable obs = (Observable)param.getValue();
                switch(param.getKey())
                {
                    case "add":
                        obs.addObserver(this);
                        break;
                        
                    case "remove":
                        obs.deleteObserver(this);
                        break;
                }
            }
        }
        
        notifyChanges();
    }
}
