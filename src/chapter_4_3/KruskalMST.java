package chapter_4_3;

import chapter_1_3.Queue;
import chapter_1_5.WeightedQuickUnionUF;
import chapter_2.PriorityQueueResize;
import utils.In;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        PriorityQueueResize<Edge> pq = new PriorityQueueResize(PriorityQueueResize.Orientation.MIN);
        for(Edge e: G.edges()) pq.insert(e);

        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.deleteTop();
            int v = e.either();
            int w = e.other(v);
            if(uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double d = 0.0;
        for(Edge e: edges()) {
            d += e.weight();
        }
        return d;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST kMST = new KruskalMST(G);
        for(Edge e: kMST.edges()) {
            System.out.println(e);
        }
        System.out.println(kMST.weight());
    }
}
