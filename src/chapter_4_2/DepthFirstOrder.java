package chapter_4_2;

import chapter_1_3.Queue;
import chapter_1_3.Stack;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        pre = new Queue<>();
        post= new Queue<>();
        reversePost = new Stack<>();
        for (int v = 0; v < G.V(); v++) {
            if(!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        pre.enqueue(v);
        for(int w: G.adj(v)) {
            if(!marked[w]) dfs(G, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Queue<Integer> pre() {
        return pre;
    }

    public Queue<Integer> post() {
        return post;
    }

    public Stack<Integer> reversePost() {
        return reversePost;
    }
}
