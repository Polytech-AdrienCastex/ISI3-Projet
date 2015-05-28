package view;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * This class give tools to load images just with the name/path of the resource
 * to load.
 */
public class ImageLoader
{
    /**
     * Image folder to look in to find a file located on the file system.
     */
    private static String imageFolder;
    
    /**
     * Set the image folder.
     * @param imageFolder Image folder.
     */
    public static void setImageFolder(String imageFolder)
    {
        if(!imageFolder.endsWith("/") && !imageFolder.endsWith("\\"))
            imageFolder += "/";
        
        ImageLoader.imageFolder = imageFolder;
    }
    
    /**
     * ClassLoader used to load files from internal resources of the project.
     */
    private static final ClassLoader loader = new ImageLoader().getClass().getClassLoader();
    
    /**
     * Load the image based on <i>imageName</i>.
     * @param imageName Image name.
     * @return The image loaded. Returns null if no resource found anywhere.
     */
    public static Image loadImage(String imageName)
    {
        if(imageName == null)
            return null;
        
        File f = new File(imageFolder + imageName);
        if(f.exists())
            return loadImage(f);
        else
            return loadImage(loader.getResourceAsStream("view/resources/" + imageName));
    }
    /**
     * Load the image based on <i>imageFile</i>.
     * @param imageFile Image file.
     * @return The image loaded. Returns null if no resource found anywhere.
     */
    public static Image loadImage(File imageFile)
    {
        if(imageFile == null)
            return null;
        
        try
        {
            return loadImage(new FileInputStream(imageFile));
        }
        catch (FileNotFoundException ex)
        {
            return null;
        }
    }
    /**
     * Load the image based on <i>imageStream</i>.
     * @param imageStream Image stream.
     * @return The image loaded. Returns null if no resource found anywhere.
     */
    public static Image loadImage(InputStream imageStream)
    {
        if(imageStream == null)
            return null;
        
        try
        {
            return ImageIO.read(imageStream);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
