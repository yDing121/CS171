public class CycleDetector {
    private boolean[] visited;
    private boolean hasCycle;

    public CycleDetector(Graph g){
        visited = new boolean[g.numVertices()]; 

        for (int v=0; v < g.numVertices(); v++){
            if (!visited[v]){
                dfs(g, v, v);
            }
        }
    }   

    private void dfs(Graph g, int v, int u){
        visited[v] = true;
        for (int w: g.adj(v)){
            if (!visited[w]){
                dfs(g, w, v);
            }
            else if (w != u){
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }
}
