package model.pathfinding.algorithms;

import java.util.Collection;
import model.SurfaceType;
import model.authorizer.Auth4x4;
import model.authorizer.AuthPafPaf;
import model.authorizer.AuthSnapSnap;
import model.authorizer.AuthTank;
import model.authorizer.Authorizer;
import model.elementary.Point;
import model.elementary.Waterable;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
import model.graph.project.FireableNode;
import model.graph.project.ProjectEdge;
import model.pathfinding.PathFinding;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public abstract class ShortestPathTest
{
    /**
     * Set up
     */
    @Before
    public void setUp()
    {
        graph = new Graph();
        A = new FireableNode(graph, new Point(2.0, 0.0));
        B = new FireableNode(graph, new Point(4.0, 0.0));
        C = new FireableNode(graph, new Point(3.0, -2.0));
        E = new FireableNode(graph, new Point(6.0, 0.0));
        F = new FireableNode(graph, new Point(0.0, 2.0));
        G = new FireableNode(graph, new Point(1.0, 2.0));
        H = new FireableNode(graph, new Point(3.0, 2.0));
        I = new FireableNode(graph, new Point(3.0, 4.0));
        O = new FireableNode(graph, new Point(0.0, 0.0));
        X = new FireableNode(graph, new Point(6.0, 4.0));
        
        OA = new ProjectEdge(O, A, SurfaceType.Escarpe, 1.0);
        OG = new ProjectEdge(O, G, SurfaceType.Plat, 1.0);
        OF = new ProjectEdge(O, F, SurfaceType.Plat, 1.0);
        IC = new ProjectEdge(I, C, SurfaceType.Plat, 1.0);
        AH = new ProjectEdge(A, H, SurfaceType.Plat, 1.0);
        AG = new ProjectEdge(A, G, SurfaceType.Plat, 1.0);
        HB = new ProjectEdge(H, B, SurfaceType.Plat, 1.0);
        HE = new ProjectEdge(H, E, SurfaceType.Plat, 1.0);
        BE = new ProjectEdge(B, E, SurfaceType.Plat, 1.0);
        OC = new ProjectEdge(O, C, SurfaceType.Plat, 1.0);
        CE = new ProjectEdge(C, E, SurfaceType.Plat, 1.0);
        
        ((FireableNode)C).setFireIntensity(10.0);
        ((Waterable)HE).setUnderWater(true);
        
        auth4x4 = new Auth4x4();
        authPafPaf = new AuthPafPaf();
        authSnapSnap = new AuthSnapSnap();
        authTank = new AuthTank();
        
        pathFindingAlgorithm = getPathFinding();
    }
    
    /**
     * Get path finding to use for tests for herited classes
     * @return path finding to use for tests
     */
    protected abstract PathFinding getPathFinding();
    
    /**
     *      (I)---|
     *   --------[C]--------
     *   |                 |
     *  (O)~~~(A)   (B)---(E)
     *   |\    /\   /      x
     *   |  \ /  \ /    xxxx
     *  (F)-(G)  (H)xxxxx       (X)
     * 
     * --- Normal
     * ~~~ Escarpé
     * xxx Immergé
     * 
     * (...) Node
     * [...] Node on fire
     */
    protected Graph graph;
    /**
     * Origin
     */
    protected Node O;
    protected Node H;
    /**
     * Dead end
     */
    protected Node I;
    /**
     * Not possible
     */
    protected Node X;
    protected Node A;
    protected Node B;
    protected Node C;
    protected Node E;
    protected Node F;
    protected Node G;
    
    protected Edge OA;
    protected Edge OC;
    protected Edge OG;
    protected Edge OF;
    protected Edge IC;
    protected Edge AH;
    protected Edge AG;
    protected Edge HB;
    protected Edge HE;
    protected Edge BE;
    protected Edge CE;
    
    protected Authorizer auth4x4;
    protected Authorizer authPafPaf;
    protected Authorizer authSnapSnap;
    protected Authorizer authTank;
    
    protected final Edge[] NO_PATH = new Edge[0];
    
    
    protected PathFinding pathFindingAlgorithm;

    /**
     * Test of getShortestPath method
     */
    @Test
    public void testGetShortestPath_4x4()
    {
        System.out.println(pathFindingAlgorithm.getClass().getName() + ".getShortestPath 4x4");
        
        compute(O, E, auth4x4, new Edge[]
        {
            OA, AH, HE
        });
        
        compute(O, I, auth4x4, NO_PATH);
        compute(O, X, auth4x4, NO_PATH);
    }
    
    /**
     * Test of getShortestPath method
     */
    @Test
    public void testGetShortestPath_PafPaf()
    {
        System.out.println(pathFindingAlgorithm.getClass().getName() + ".getShortestPath Paf Paf");
        
        compute(O, E, authPafPaf, new Edge[]
        {
            OA, AH, HB, BE
        });
        
        compute(O, I, authPafPaf, NO_PATH);
        compute(O, X, authPafPaf, NO_PATH);
    }
    
    /**
     * Test of getShortestPath method
     */
    @Test
    public void testGetShortestPath_SnapSnap()
    {
        System.out.println(pathFindingAlgorithm.getClass().getName() + ".getShortestPath Snap Snap");
        
        compute(O, E, authSnapSnap, new Edge[]
        {
            OG, AG, AH, HB, BE
        });
        
        compute(O, I, authSnapSnap, NO_PATH);
        compute(O, X, authSnapSnap, NO_PATH);
    }
    
    /**
     * Test of getShortestPath method
     */
    @Test
    public void testGetShortestPath_Tank()
    {
        System.out.println(pathFindingAlgorithm.getClass().getName() + ".getShortestPath Tank");
        
        compute(O, E, authTank, new Edge[]
        {
            OC, CE
        });
        
        compute(O, I, authTank, new Edge[]
        {
            OC, IC
        });
        compute(O, X, authTank, NO_PATH);
    }
    
    private void compute(Node from, Node to, Authorizer auth, Edge[] wanted)
    {
        Dijkstra instance = new Dijkstra();
        
        Collection<Edge> result = instance.getShortestPath(from, to, auth);
        assertArrayEquals(wanted, result.toArray());
    }
}
