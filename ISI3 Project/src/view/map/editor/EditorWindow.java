package view.map.editor;

import controller.actionmanagers.ActionManager;
import controller.actionmanagers.EditorActionManager;
import view.main.*;
import controller.actionmanagers.MainActionManager;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import model.elementary.Point;
import model.graph.Graph;
import view.Button;
import view.GraphWindow;
import view.Window;
import view.map.EdgeDrawer;
import view.map.GraphDrawer;
import view.map.NodeDrawer;

/**
 *
 */
public class EditorWindow extends GraphWindow implements IEditorView
{
    public EditorWindow(EditorActionManager actionManager)
    {
        super(actionManager);
        
        this.setTitle("Editor");
    }
    
    @Override
    public void initialize()
    {
        super.initialize();
    }
}
