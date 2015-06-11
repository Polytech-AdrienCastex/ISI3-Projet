package view.windows.settings;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.SurfaceType;
import view.windows.PopupWindow;

/**
 * Edge editor view.
 */
public class EdgeEditorWindow extends PopupWindow
{
    /**
     * Constructor.
     */
    public EdgeEditorWindow()
    {
        super(2);
        
        this.setTitle("Edge");
    }
    
    /**
     * JComboBox to select the type of the surface.
     */
    protected JComboBox<SurfaceType> typeList;
    
    /**
     * Text field for the selection of edge value.
     */
    protected JTextField value;
    
    @Override
    public void initialize()
    {
        addLabel("Type", 0);
        
        typeList = new JComboBox<>(SurfaceType.values());
        typeList.setSelectedIndex(0);
        this.add(format(typeList), 0, 1);
        
        addLabel("Value", 1);
        
        value = new JTextField("5.0");
        this.add(format(value), 1, 1);
        
        this.pack();
    }
    
    /**
     * Get the selected type for the new edge.
     * @return selected type of the new edge.
     */
    public SurfaceType getEdgeType()
    {
        return typeList.getItemAt(typeList.getSelectedIndex());
    }
    
    /**
     * Get the edge value from the text field.
     * @return value wanted for the new edge.
     */
    public Double getEdgeValue()
    {
        try
        {
            return Double.parseDouble(value.getText());
        }
        catch(NumberFormatException ex)
        {
            return 5.0;
        }
    }
}
