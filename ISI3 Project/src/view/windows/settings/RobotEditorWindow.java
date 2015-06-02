package view.windows.settings;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.SurfaceType;
import model.authorizer.Auth4x4;
import model.authorizer.AuthPafPaf;
import model.authorizer.AuthSnapSnap;
import model.authorizer.Authorizer;
import model.item.FireHose;
import model.item.FireThrower;
import model.item.IItem;
import model.robot.Robot;
import model.robot.manager.Manager;
import view.windows.PopupWindow;

/**
 *
 */
public class RobotEditorWindow extends PopupWindow
{
    public RobotEditorWindow(Manager[] managers)
    {
        super(5);
        
        this.setTitle("Robot");
        this.managers = managers;
    }
    
    protected final Manager[] managers;
    protected JComboBox<Manager> managersList;
    protected JComboBox<Authorizer> authorizerList;
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
        
        
        addLabel("Manager", 0);
        
        managersList = new JComboBox<>(managers);
        managersList.setSelectedIndex(0);
        this.add(format(managersList), 0, 1);
        
        
        
        addLabel("Authorizer", 0);
        
        authorizerList = new JComboBox<>(new Authorizer[]
        {
            new Auth4x4(),
            new AuthPafPaf(),
            new AuthSnapSnap()
        });
        authorizerList.setSelectedIndex(0);
        this.add(format(authorizerList), 0, 1);
        
        
        this.pack();
    }
    
    public Manager getManager()
    {
        return (Manager)managersList.getSelectedItem();
    }
    public Authorizer getAuthorizer()
    {
        return (Authorizer)authorizerList.getSelectedItem();
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
