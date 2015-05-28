package view.windows.settings;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.SurfaceType;
import model.item.FireHose;
import model.item.FireThrower;
import model.item.IItem;
import view.windows.PopupWindow;

/**
 *
 */
public class RobotEditorWindow extends PopupWindow
{
    public RobotEditorWindow()
    {
        super(3);
        
        this.setTitle("Robot");
    }
    
    protected JComboBox<String> weaponList;
    protected JTextField weaponIntensity;
    protected JTextField speed;
    
    @Override
    public void initialize()
    {
        addLabel("Weapon intensity", 1);
        
        weaponIntensity = new JTextField("1.0");
        this.add(format(weaponIntensity), 1, 1);
        
        
        
        addLabel("Weapon", 0);
        
        weaponList = new JComboBox<>(new String[]
        {
            FireHose.class.getSimpleName(),
            FireThrower.class.getSimpleName()
        });
        weaponList.setSelectedIndex(0);
        this.add(format(weaponList), 0, 1);
        
        
        
        addLabel("Speed", 2);
        
        speed = new JTextField("10.0");
        this.add(format(speed), 2, 1);
        
        this.pack();
    }
    
    public IItem getWeapon()
    {
        Double intensity;
        try
        {
            intensity = Double.parseDouble(weaponIntensity.getText());
        }
        catch(NumberFormatException ex)
        {
            intensity = 1.0;
        }
        
        IItem item = null;
        
        switch(weaponList.getSelectedItem().toString())
        {
            case "FireHose":
                item = new FireHose(intensity);
                break;
                
            case "FireThrower":
                item = new FireThrower(intensity);
                break;
        }
        
        return item;
    }
    public Double getSpeed()
    {
        try
        {
            return Double.parseDouble(speed.getText());
        }
        catch(NumberFormatException ex)
        {
            return 10.0;
        }
    }
}
