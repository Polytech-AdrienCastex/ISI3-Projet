package view.windows.edge;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.SurfaceType;
import view.windows.PopupWindow;

/**
 *
 */
public class EdgeEditorWindow extends PopupWindow
{
    public EdgeEditorWindow()
    {
        super(2);
        
        this.setTitle("Edge");
    }
    
    protected JComboBox<SurfaceType> typeList;
    protected JTextField value;
    
    @Override
    public void initialize()
    {
        addLabel("Type", 0);
        
        typeList = new JComboBox<>(SurfaceType.values());
        typeList.setSelectedIndex(0);
        this.add(format(typeList), 0, 1);
        
        addLabel("Value", 1);
        
        value = new JTextField("0.0");
        this.add(format(value), 1, 1);
        
        this.pack();
    }
    
    public SurfaceType getEdgeType()
    {
        return typeList.getItemAt(typeList.getSelectedIndex());
    }
    public Double getEdgeValue()
    {
        return Double.parseDouble(value.getText());
    }
}
