public class DepthFirstPaths {
    private boolean[] visited;
    private int[] edgeTo;
    private int source;

    public DepthFirstPaths(Graph g, int s){
        visited = new boolean[g.numVertices()];
        this.source = s;
        dfs(g,s);
    }

    private void dfs(Graph g, int v){
        visited[v] = true;
        for (int w: g.adj(v)){
            if (!visited[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }
        }
    }

    public boolean hasPath(int v){
        return visited[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPath(v)) return null;
        StackArray<Integer> path = new StackArray<Integer>();
        for (int x = v; x != source; x = edgeTo[x]){
            path.push(x);
        }
        path.push(source);
        return path;
    }
}
