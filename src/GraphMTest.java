import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class GraphMTest {

    GraphM graph;
    Cocktail c1;
    Cocktail c2;
    Cocktail c3;

    @Before
    public void setUp(){
        graph = new GraphM(10);
        c1 = new Cocktail("1-900-FUK-MEUP");
        c2 = new Cocktail("Collins Glass");
        c3 = new Cocktail("Irish coffee cup");
    }

    @Test
    public void testNodesCount(){
        assertEquals(10, graph.nodesCount());
        graph = new GraphM(15);
        assertEquals(15, graph.nodesCount());
    }

    @Test
    public void testSetNode(){
        graph.setNode(3, c1);
        graph.setNode(5, c2);
        graph.setNode(8, c3);
        assertEquals(c1, graph.getNode(3));
        assertEquals(c2, graph.getNode(5));
        assertEquals(c3, graph.getNode(8));
    }

    @Test
    public void testAddEdge(){
        graph.addEdge(1, 2, 0.5);
        graph.addEdge(2, 3, 0.6);
        assertEquals(0.5, graph.getEdge(1, 2));
        assertEquals(0.5, graph.getEdge(2, 1));
        assertEquals(0.6, graph.getEdge(2, 3));
        assertEquals(0.6, graph.getEdge(3, 2));
    }

    @Test
    public void testNeighbors(){
        graph.addEdge(1, 2, 0.3);
        graph.addEdge(1, 3, 0.3);
        graph.addEdge(1, 5, 0.3);
        graph.addEdge(1, 7, 0.3);
        graph.addEdge(3, 5, 0.2);
        graph.addEdge(3, 8, 0.3);
        int[] neighbor1 = graph.neighbors(1);
        assertEquals(4, neighbor1.length);
        assertEquals(2, neighbor1[0]);
        assertEquals(3, neighbor1[1]);
        assertEquals(5, neighbor1[2]);
        assertEquals(7, neighbor1[3]);
    }
}
