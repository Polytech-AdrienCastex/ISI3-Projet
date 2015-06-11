package view.windows.settings;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.authorizer.Auth4x4;
import model.authorizer.AuthPafPaf;
import model.authorizer.AuthSnapSnap;
import model.authorizer.Authorizer;
import model.item.FireHose;
import model.item.FireThrower;
import model.item.IItem;
import model.robot.manager.Manager;
import view.windows.PopupWindow;

/**
 * Popup view robot editor to add new robot.
 */
public class RobotEditorWindow extends PopupWindow
{
    /**
     * Constructor
     * @param managers Models managers to use in this view.
     */
    public RobotEditorWindow(Manager[] managers)
    {
        super(5);
        
        this.setTitle("Robot");
        this.managers = managers;
    }
    
    /**
     * Managers used in this view.
     */
    protected final Manager[] managers;
    
    /**
     * JComboBox to display managers.
     */
    protected JComboBox<Manager> managersList;
    
    /**
     * JComboBox to display authorizers.
     */
    protected JComboBox<Authorizer> authorizerList;
    
    /**
     * JComboBox to display weapon.
     */
    protected JComboBox<String> weaponList;
    
    /**
     * Text field for the weapon intensity.
     */
    protected JTextField weaponIntensity;
    
    /**
     * Text field for the speed of the new robot.
     */
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
    
    /**
     * Get the manager selected.
     * @return Manager selected.
     */
    public Manager getManager()
    {
        return (Manager)managersList.getSelectedItem();
    }
    
    /**
     * Get the authorizer selected.
     * @return Authorizer selected.
     */
    public Authorizer getAuthorizer()
    {
        return (Authorizer)authorizerList.getSelectedItem();
    }
    
    /**
     * Get the weapon selected.
     * @return Item selected.
     */
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
    
    /**
     * Get the speed of the robot.
     * @return speed selected.
     */
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
