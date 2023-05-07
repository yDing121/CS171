public class TwoColorDetector {
    private boolean[] visited;
    private boolean[] color;
    private boolean isTwoColorable;

    public TwoColorDetector(Graph g){
        visited = new boolean[g.numVertices()];
        color = new boolean[g.numVertices()];
        isTwoColorable = true;
        
        for (int v=0; v < g.numVertices(); v++){
            if (!visited[v]){
                dfs(g, v);
            }
        }
    }

    private void dfs(Graph g, int v){
        visited[v] = true;
        for (int w: g.adj(v)){
            if (!visited[w]){
                color[w] = !color[v];
                dfs(g, w);
            }
            else if (color[w] == color[v]){
                isTwoColorable = false;
            }
        }
    }

    public boolean isTwoColorable(){
        return isTwoColorable;
    }
}
