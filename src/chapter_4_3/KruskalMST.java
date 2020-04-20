package chapter_4_3;

import chapter_1_3.Queue;
import chapter_1_5.WeightedQuickUnionUF;
import chapter_2.PriorityQueueResize;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        PriorityQueueResize<Edge> pq = new PriorityQueueResize(PriorityQueueResize.Orientation.MIN);
        for(Edge e: G.edges()) pq.insert(e);

        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());
    }
}
