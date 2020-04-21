package chapter_4_3;

import chapter_1_3.Queue;
import chapter_2.PriorityQueueResize;

public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private PriorityQueueResize<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new Queue<>();
        pq = new PriorityQueueResize<>(PriorityQueueResize.Orientation.MIN);
        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.deleteTop();
            int v = e.either();
            int w = e.other(v);
            if(marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if(!marked[v]) visit(G, v);
            if(!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for(Edge e: G.adj(v)) {
            if(!marked[e.other(v)]) pq.insert(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double w = 0.0;
        for(Edge e: edges()) {
            w += e.weight();
        }
        return w;
    }
}
