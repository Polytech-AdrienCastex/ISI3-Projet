/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * This class represents the resource loader able to load resources inside the
 * project or outside the project.
 */
public class ResourceLoader
{
    /**
     * Constructor.
     * @param resourceFolder Path inside the project of the internal resource
     * folder.
     */
    public ResourceLoader(String resourceFolder)
    {
        if(!resourceFolder.endsWith("/") && !resourceFolder.endsWith("\\"))
            resourceFolder += "/";
        this.resourceFolder = resourceFolder;
    }
    /**
     * Internal resource folder.
     */
    protected final String resourceFolder;
    
    /**
     * Load an external/internal resource from its name.
     * <p>
     * First, it will try to load the resource as external resource. If no
     * external resource found, it will load the resource as internal resource.
     * If no internal and external resource found, then it returns null.
     * @param name Name/Path of the resource.
     * @return The stream representing the resource.
     */
    public InputStream loadStream(String name)
    {
        try
        {
            File file = new File(name);
            if(file.exists())
                return Files.newInputStream(file.toPath(), StandardOpenOption.READ);
            else
                return getClass().getClassLoader().getResourceAsStream(resourceFolder + name);
        }
        catch (IOException ex)
        {
            return null;
        }
    }
}
