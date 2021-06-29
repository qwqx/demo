package c16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author TK
 */
public class GraphBFS {

    public static void bfs(Node node) {
        final Queue<Node> queue = new LinkedList<>();
        final HashSet<Node> set = new HashSet<>();

        queue.add(node);
        set.add(node);
        while(!queue.isEmpty()) {
            final Node poll = queue.poll();
            System.out.println(poll.value);

            for (Node next : poll.nexts) {
                if(!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    /**
     *
     * 1-5-8
     * | |
     * 7-9-4
     * @param args
     */
    public static void main(String[] args) {

        int[][] matrix = {{1,1,5},{3,1,7},{6,5,8},{4,5,9},{2,7,9},{2,9,4}};

        final Graph generate = GraphGenerater.generate(matrix);

        bfs(generate.nodes.get(1));

    }
}
