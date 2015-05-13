package model.robot;

import java.util.List;
import model.graph.Node;
import model.pathfinding.Authorizer;

/**
 * Robot en général
 */
public abstract class Robot implements Authorizer {
    private Double vitesse;
    private Node currentNode;
    private List<Node> destination;

    public Robot(Double vitesse, Node currentNode) {
        this.vitesse = vitesse;
        this.currentNode = currentNode;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }
       
    /**
     * Retourner la valeur du meilleur chemin selon le noeud de destination
     * @param dest Noeud de destination
     * @return Valeur du meilleur chemin
     */
    public Double getPathValue(Node dest)
    {
        //PathFinding pf = 
        
        //Multiplier par la vitesse chaque arrête
        
        return null;
    }
    
    /**
     * Robot occupé ou non
     * @return vrai si occupé
     */
    public Boolean isBusy() {
        //Random rand = new Random();
        
        return false;
    }
    
    /**
     * Definir le destination du robot à partir du noeud de destination
     * @param dest Noeud cible (destination)
     */
    public void setDestination(Node dest)
    {
        //determiner chemin
    }
}
