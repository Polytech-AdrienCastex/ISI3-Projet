package view;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 */
public class ImageLoader
{
    private static String imageFolder;
    public static void setImageFolder(String imageFolder)
    {
        if(!imageFolder.endsWith("/") && !imageFolder.endsWith("\\"))
            imageFolder += "/";
        
        ImageLoader.imageFolder = imageFolder;
    }
    
    private static final ClassLoader loader = new ImageLoader().getClass().getClassLoader();
    
    public static Image loadImage(String imageName)
    {
        File f = new File(imageFolder + imageName);
        if(f.exists())
            return loadImage(f);
        else
            return loadImage(loader.getResourceAsStream("view/resources/" + imageName));
    }
    public static Image loadImage(File imageFile)
    {
        try
        {
            return loadImage(new FileInputStream(imageFile));
        }
        catch (FileNotFoundException ex)
        {
            return null;
        }
    }
    public static Image loadImage(InputStream imageStream)
    {
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
