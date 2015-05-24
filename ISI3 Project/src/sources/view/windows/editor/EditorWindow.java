package sources.view.windows.editor;

import sources.controller.actionmanagers.EditorActionManager;
import sources.view.windows.GraphWindow;

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
