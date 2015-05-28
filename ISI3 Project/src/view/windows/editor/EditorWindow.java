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
        super(actionManager, false);
        
        this.setTitle("Editor");
    }
    
    @Override
    public void initialize()
    {
        super.initialize();
        
        addButton("add_node", "buttons/addNode.png", "buttons/addNode_selected.png");
        addButton("add_edge", "buttons/addEdge.png", "buttons/addEdge_selected.png");
        addSeparator();
        addButton("remove_node", "buttons/removeNode.png", "buttons/removeNode_selected.png");
        addButton("remove_edge", "buttons/removeEdge.png", "buttons/removeEdge_selected.png");
        addSeparator();
        addButton("fire", "buttons/fire.png", "buttons/fire_selected.png");
    }
}
