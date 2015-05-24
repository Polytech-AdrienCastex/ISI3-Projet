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
        
        addButton("add_node", "addNode.png");
        addButton("add_edge", "addEdge.png");
        addSeparator();
        addButton("remove_node", "removeNode.png");
        addButton("remove_edge", "removeEdge.png");
        addSeparator();
        addButton("fire", "fire.png");
    }
}
