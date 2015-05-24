package view.windows.editor;

import controller.actionmanagers.EditorActionManager;
import view.windows.GraphWindow;

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
