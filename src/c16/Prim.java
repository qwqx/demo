package c16;

import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author TK
 */
public class Prim {

    public static Set<Edge> primMST(Graph graph) {

        final PriorityQueue<Edge> stack = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        final HashSet<Edge> ans = new HashSet<>();
        final HashSet<Node> visited = new HashSet();

        for(Node node : graph.nodes.values()) {

            if(!visited.contains(node)) {
                visited.add(node);

                for(Edge edge : node.edges) {
                    stack.add(edge);
                }
            }

            while (!stack.isEmpty()) {
                final Edge curr = stack.poll();
                if (!visited.contains(curr.to)) {
                    ans.add(curr);
                    visited.add(curr.to);
                    for (Edge edge : curr.to.edges) {
                        stack.add(edge);
                    }
                }
            }
        }

        return ans;
    }


    /**
     * ☆☆☆
     * @param graph
     * @return
     */
    public static int primMST2(int[][] graph) {
        int size = graph.length;
        int[] instance = new int[size];
        boolean[] visit = new boolean[size];
        visit[0] = true;

        for(int i = 0; i< size; i++) {
            instance[i] = graph[0][i];
        }

        int sum = 0;
        for(int i = 1; i < size; i++) {
            int minInstance = 99;//Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if(!visit[j] && instance[j] < minInstance) {
                    minInstance = instance[j];
                    minIndex = j;
                }
            }

            if(minIndex == -1) {
                return sum;
            }

            visit[minIndex] = true;
            sum += minInstance;

            for(int j = 0; j < size; j++) {
                if(!visit[j] && instance[j] > graph[minIndex][j]) {
                    instance[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }

    /**
     *
     * 1->5->8->3
     * v  V
     * 7->9->4
     * @param args
     */
    public static void main(String[] args) {

        //
        int[][] matrix = {{1,1,5},{3,1,7},{6,5,8},{4,5,9},{2,7,9},{2,9,4}, {2,8,3}};

        final Graph generate = GraphGenerater.generate(matrix);

        final Set<Edge> edges = primMST(generate);

        for (Edge edge : edges) {
            System.out.println(edge.from.value + "-->" + edge.to.value + ",  " + edge.weight);
        }

        // 0 ->1,2,3,4
        // 1 -> 2,3,4
        // 2 -> 3, 4
        // 3 -> 4
        // 4 =>

        int[][] graph = {
                {99, 10, 40, 20, 50},
                {99, 99, 20, 30, 10},
                {99, 99, 99, 30, 10},
                {99, 99, 99, 99, 10},
                {99, 99, 99, 99, 99},
        };

        final int sum = primMST2(graph);
        System.out.println(sum);
    }
}
