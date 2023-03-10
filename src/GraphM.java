/**
 * This class represents an undirected graph implemented using adjacency matrix
 */

public class GraphM {

    /**
     * matrix: a 2D array
     */
    double[][] matrix;

    /**
     * constructor: initialize a graph with n nodes
     */
    public GraphM(int n){
        matrix = new double[n][n];
    }

    /**
     * Return the number of nodes in this graph.
     */
    public int nodesCount(){
        return matrix.length;
    }

    /**
     * Add an edge between node u and v with given weight.
     */
    public void addEdge(int u, int v, double weight) {
        if (weight <= 0) {
            return;
        }
        matrix[u][v] = weight;
        matrix[v][u] = weight;
    }

    /**
     * Return true if there's an edge between the given two nodes, false otherwise.
     */
    public boolean hasEdge(int u, int v){
        return matrix[u][v] != 0;
    }

    /**
     * Get edge(u, v).
     */
    public double getEdge(int u, int v) {
        return matrix[u][v];
    }

    /**
     * Return an array of indices of nodes adjacent to the given node.
     */
    public int[] neighbors(int u) {
        int num = 0, n = nodesCount();
        for(int i = 0; i < n; i++){
            if(hasEdge(u, i)){
                num++;
            }
        }
        int[] neighbors = new int[num];
        int index = 0;
        for(int i = 0; i < n; i++){
            if(hasEdge(u, i)){
                neighbors[index++] = i;
            }
        }
        return neighbors;
    }

}
