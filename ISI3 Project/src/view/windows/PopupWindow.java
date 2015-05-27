package view.windows;

import controller.actionmanagers.ActionManager;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRootPane;

/**
 * This class represent a popup window.
 */
public abstract class PopupWindow extends Window
{
    /**
     * Constructor.
     * @param nbLine Number of lines of the grid used to display the components.
     */
    public PopupWindow(int nbLine)
    {
        this(nbLine, null);
    }
    /**
     * Constructor.
     * @param nbLine Number of lines of the grid used to display the components.
     * @param actionManager Action manager linked to this window.
     */
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
    
    /**
     * Format the JComponent <b>c</b> in the format of the popup window.
     * @param c JComponent to format.
     * @return The same JComponent.
     */
    protected JComponent format(JComponent c)
    {
        c.setBackground(Color.white);
        c.setOpaque(true);
        return c;
    }
    /**
     * Add a new label with the text <b>text</b> and on a line number <b>lineNumber</b>.
     * @param text Text of the label.
     * @param lineNumber Number of the line where put the label on the popup
     * window.
     * @return The JLabel created (already added on the window).
     */
    protected JLabel addLabel(String text, int lineNumber)
    {
        JLabel label = new JLabel(text);
        label.setOpaque(rootPaneCheckingEnabled);
        format(label);
        this.add(label, lineNumber, 0);
        return label;
    }
}
