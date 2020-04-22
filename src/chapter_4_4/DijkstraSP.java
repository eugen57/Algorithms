package chapter_4_4;

import chapter_1_3.Stack;
import chapter_2.IndexMinPQ;
import chapter_4_3.Edge;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        pq = new IndexMinPQ<>(G.V());
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(G, pq.deleteMin());
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for(DirectedEdge e: G.adj(v)) {
            if(distTo[e.to()] > distTo[v] + e.weight()) {
                int w = e.to();
                edgeTo[w] = e;
                distTo[w] = distTo[v] + e.weight();
                if(pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public double distTo(int v) { return distTo[v]; }

    public boolean hasPathTo(int v) { return distTo[v] < Double.POSITIVE_INFINITY; }

    public Iterable<DirectedEdge> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null ; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
