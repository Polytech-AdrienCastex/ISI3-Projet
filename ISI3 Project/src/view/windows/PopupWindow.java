package view.windows;

import controller.actionmanagers.ActionManager;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRootPane;

/**
 *
 */
public abstract class PopupWindow extends Window
{
    public PopupWindow(int nbLine)
    {
        this(nbLine, null);
    }
    public PopupWindow(int nbLine, ActionManager actionManager)
    {
        super(actionManager);
        
        this.setLayout(new GridLayout(nbLine, 2, 5, 5));
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
        this.getRootPane().setBackground(this.getBackground());
        this.getContentPane().setBackground(this.getBackground());
        this.getLayeredPane().setBackground(this.getBackground());
    }
    
    protected JComponent format(JComponent c)
    {
        c.setBackground(Color.white);
        c.setOpaque(true);
        return c;
    }
    protected JLabel addLabel(String text, int x, int y)
    {
        JLabel label = new JLabel(text);
        label.setOpaque(rootPaneCheckingEnabled);
        format(label);
        this.add(label, x, y);
        return label;
    }
}
