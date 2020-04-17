package chapter_4_2;

import chapter_1_3.Bag;
import utils.In;

public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(int v, int w) {
        if(v == w || hasEdge(v, w)) return;
        adj[v].add(w);
        E++;
    }

    public boolean hasEdge(int v, int w) {
        for(int s: adj(v)) {
            if(s == w) return true;
        }
        return false;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for(int w: adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public String toString() {
        String s = V + " вершин, " + E + " ребер\n";
        for (int v = 0; v < V; v++)
        {
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }
}
