/**
 * This class represents an undirected graph
 */
public class GraphM {

    Cocktail[] nodes;
    double[][] matrix;

    //initialize a graph with n nodes
    public GraphM(int n){
        nodes = new Cocktail[n];
        matrix = new double[n][n];
    }

    //Return the number of nodes in this graph
    public int nodesCount(){
        return nodes.length;
    }

    //Get the value of node with index u
    public Cocktail getNode(int u){
        return nodes[u];
    }

    //Set the node of index u with given value
    public void setNode(int u, Cocktail c){
        nodes[u] = c;
    }

    //Add an edge between node u and v with given weight
    public void addEdge(int u, int v, double weight){
        if(weight <= 0){
            return ;
        }
        matrix[u][v] = weight;
        matrix[v][u] = weight;
    }

    //Returns true if there's an edge between the given two nodes, false otherwise
    public boolean hasEdge(int u, int v){
        return matrix[u][v] != 0;
    }

    public double getEdge(int u, int v) {
        return matrix[u][v];
    }

    //Returns an array of indices of nodes adjacent to the given node
    public int[] neighbors(int u){
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
