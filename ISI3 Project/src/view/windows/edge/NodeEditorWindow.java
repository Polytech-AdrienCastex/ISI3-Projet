package view.windows.edge;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.SurfaceType;
import view.windows.PopupWindow;

/**
 *
 */
public class NodeEditorWindow extends PopupWindow
{
    public NodeEditorWindow()
    {
        super(1);
        
        this.setTitle("Node");
    }
    
    protected JComboBox<SurfaceType> typeList;
    protected JTextField fireIntensity;
    
    @Override
    public void initialize()
    {
        addLabel("FireIntensity", 0);
        
        fireIntensity = new JTextField("0.0");
        this.add(format(fireIntensity), 0, 1);
        
        this.pack();
    }
    
    public Double getFireIntensity()
    {
        return Double.parseDouble(fireIntensity.getText());
    }
}
