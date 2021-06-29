package c16;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author TK
 */
public class GraphDFS {

    public static void dfs(Node node) {
        final HashSet<Node> set = new HashSet<>();
        process(node, set);
    }

    public static void process(Node node, Set<Node> set) {
        System.out.print(node.value + " ");
        set.add(node);

        for (Node next : node.nexts) {
            if(!set.contains(next)) {
                process(next, set);
            }
        }
    }

    public static void dfs2(Node node) {
        final HashSet<Node> set = new HashSet<>();

        final Stack<Node> stack = new Stack<>();
        stack.push(node);
        System.out.print(node.value + " ");
        set.add(node);
        while(!stack.isEmpty()) {
            final Node pop = stack.pop();
            for(Node next : pop.nexts) {
                if(!set.contains(next)) {
                    stack.push(pop);
                    stack.push(next);
                    set.add(next);
                    System.out.print(next.value + " ");
                    break;
                }
            }
        }
    }

    /**
     *
     * 1->5->8
     * v  V
     * 7->9->4
     * @param args
     */
    public static void main(String[] args) {

        int[][] matrix = {{1,1,5},{3,1,7},{6,5,8},{4,5,9},{2,7,9},{2,9,4}};

        final Graph generate = GraphGenerater.generate(matrix);

        dfs(generate.nodes.get(1));
        System.out.println();
        dfs2(generate.nodes.get(1));

    }
}
