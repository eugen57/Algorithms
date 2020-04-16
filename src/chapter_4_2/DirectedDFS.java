package chapter_4_2;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s: sources) {
            if(!marked[s]) dfs(G, s);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }
}
