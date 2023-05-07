public class ConnectedComponents {
    private boolean[] visited;
    private int[] id;
    private int numComponents;

    public ConnectedComponents(Graph g){
        visited = new boolean[g.numVertices()];
        id = new int[g.numVertices()];  

        for (int v=0; v < g.numVertices(); v++){
            if (!visited[v]){
                dfs(g, v);
                numComponents++;
            }
        }
    }   

    private void dfs(Graph g, int v){
        visited[v] = true;
        for (int w: g.adj(v)){
            id[v] = numComponents;
            if (!visited[w]){
                dfs(g,w);
            }
        }
    }

    public int count(){
        return numComponents;
    }
}
