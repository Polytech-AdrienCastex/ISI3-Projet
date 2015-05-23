package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 */
public class ImageLoader
{
    public static String imageFolder;
    public static void setImageFolder(String imageFolder)
    {
        if(!imageFolder.endsWith("/") && !imageFolder.endsWith("\\"))
            imageFolder += "/";
        
        ImageLoader.imageFolder = imageFolder;
    }
    
    public static Image loadImage(String imageName)
    {
        return loadImage(new File(imageFolder + imageName));
    }
    public static Image loadImage(File imageFile)
    {
        try
        {
            return ImageIO.read(imageFile);
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
