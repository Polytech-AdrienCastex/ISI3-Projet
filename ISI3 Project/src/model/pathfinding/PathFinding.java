package model.pathfinding;

import java.util.List;
import model.Node;

public interface PathFinding {
    /**
     * Retourner le meilleur chemin
     * @param origin Noeud d'origine
     * @param dest Noeud de destination
     * @return List de Noeud correspondant au meilleur chemin
     */
    public List<Node> getShortestPath(Node origin, Node dest);
}
