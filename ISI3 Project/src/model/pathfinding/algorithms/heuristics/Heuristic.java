package model.pathfinding.algorithms.heuristics;

import model.graph.Node;

/**
 * This interface allows to compute and return the value computed between two nodes
 * in the same graph.
 */
public interface Heuristic
{
    /**
     * Get the value computed between <b>node1</b> and <b>node2</b>.
     * <p>
     * If node1 and node2 cannot be computed, this method returns <b>NaN</b>
     * (<i>Double.NaN</i>).
     * @param node1 First node.
     * @param node2 Second node.
     * @return The value computed between <b>node1</b> and <b>node2</b>.
     */
    public double getValue(Node node1, Node node2);
}
