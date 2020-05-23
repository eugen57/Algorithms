package chapter_6_4;

import chapter_1_3.Queue;
import utils.In;
import utils.StdOut;

public class FordFulkerson {
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;

    public FordFulkerson(FlowNetwork G, int s, int t) {
        while (hasAugmentingPath(G, s, t)) {
            // Вычисление минимальной пропускной способности
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v !=  s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }

            // Расширение пути
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
        }
    }

    public double value() { return value; }

    public boolean inCut(int v) { return marked[v]; }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        marked = new boolean[G.V()];
        edgeTo = new FlowEdge[G.V()];
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for(FlowEdge e: G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = e;
                    queue.enqueue(w);
                }
            }
        }
        return marked[t];
    }

    private boolean localEq(FlowNetwork G, int v) {
        // Проверка локального баланса в каждой вершине.
        double EPSILON = 1E-11;
        double netflow = 0.0;
        for (FlowEdge e : G.adj(v))
            if (v == e.from()) netflow -= e.flow();
            else netflow += e.flow();
        return Math.abs(netflow) < EPSILON;
    }

    private boolean isFeasible(FlowNetwork G, int s, int t) {
        // Проверка, что поток по каждому ребру неотрицателен
        // и не превышает его пропускную способность,
        for (int v = 0; v < G.V(); v++)
            for (FlowEdge e : G.adj(v))
                if (e.flow() < 0 || e.flow() > e.capacity())
                    return false;
        // Проверка локального баланса в каждой вершине,
        for (int v = 0; v < G.V(); v++)
            if (v != s && v != t && !localEq(G, v))
                return false;
        return true;
    }

    public static void main(String[] args) {
        FlowNetwork G = new FlowNetwork (new In(args[0]));
        int s = 0, t = G.V() - 1;
        FordFulkerson maxflow = new FordFulkerson (G, s, t);
        StdOut.println("Максимальный поток из " + s + " в " + t);
        for (int v = 0; v < G.V(); v++)
            for (FlowEdge e : G.adj(v))
                if ((v == e.from()) && e.flow() > 0)
                    StdOut.println(" " + e);
        StdOut.println("Величина максимального потока = " + maxflow.value);
    }
}