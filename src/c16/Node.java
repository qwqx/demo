package c16;

import java.util.LinkedList;
import java.util.List;

/**
 * @author TK
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public List<Node> nexts;
    public List<Edge> edges;

    Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new LinkedList<>();
        edges = new LinkedList<> ();
    }
}
