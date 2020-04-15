package chapter_4_1;

import utils.StdIn;

public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);
        Graph g = sg.G();
        String source = args[2];
        if (!sg.contains(source)) {
            System.out.println("Error");
            return;
        }
        int s = sg.index(source);
        BreadthFirstPaths bfd = new BreadthFirstPaths(g, s);
        while (!StdIn.isEmpty()) {
            String dest = StdIn.readLine();
            if(sg.contains(dest)) {
                int t = sg.index(dest);
                if(bfd.hasPathTo(t)) {
                    for(int v: bfd.pathTo(t)) {
                        System.out.println("  " + sg.name(v));
                    }
                } else {
                    System.out.println("Not connected");
                }
            } else {
                System.out.println("Does not contain");
            }
        }
    }
}
