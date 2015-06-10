package view.windows.settings;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.SurfaceType;
import view.windows.PopupWindow;

/**
 * Node editor view.
 */
public class NodeEditorWindow extends PopupWindow
{
    /**
     * Constructor.
     */
    public NodeEditorWindow()
    {
        super(1);
        
        this.setTitle("Node");
    }
    
    /**
     * JComboBox to select surface type for this node.
     */
    protected JComboBox<SurfaceType> typeList;
    
    /**
     * Text field for the fire intensity for this node.
     */
    protected JTextField fireIntensity;
    
    @Override
    public void initialize()
    {
        addLabel("FireIntensity", 0);
        
        fireIntensity = new JTextField("0.0");
        this.add(format(fireIntensity), 0, 1);
        
        this.pack();
    }
    
    /**
     * Get the fire intensity from the text field.
     * @return Fire intensity wanted.
     */
    public Double getFireIntensity()
    {
        try
        {
            return Double.parseDouble(fireIntensity.getText());
        }
        catch(NumberFormatException ex)
        {
            return 0.0;
        }
    }
}
