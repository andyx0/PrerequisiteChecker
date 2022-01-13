package prereqchecker;

import java.util.*;

public class DepthFirstSearch {
    private HashSet<String> marked;
    private int count; // number of vertices connected to s

    /**
     * Computes the vertices in graph {@code G} that are
     * connected to the source vertex {@code s}.
     * 
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstSearch(Digraph G, String s) {
        marked = new HashSet<>();
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Digraph G, String v) {
        count++;
        marked.add(v);
        for (String w : G.adj(v)) {
            if (!marked.contains(w)) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(String v) {
        return marked.contains(v);
    }

    public HashSet<String> getMarked() {
        return marked;
    }

    /**
     * Returns the number of vertices connected to the source vertex {@code s}.
     * 
     * @return the number of vertices connected to the source vertex {@code s}
     */
    public int count() {
        return count;
    }
}