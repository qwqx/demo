package c16;

import java.util.*;

/**
 * 根据入度bfs
 * @author TK
 */
public class TopologicalSort {

    public static List<Node> sort(Graph graph) {
        final Map<Node, Integer> inDegreeMap = new HashMap<>();
        final Stack<Node> stack = new Stack<>();
        for(Node node : graph.nodes.values()) {
            inDegreeMap.put(node, node.in);
            if(inDegreeMap.get(node) == 0) {
                stack.push(node);
            }
        }


        final ArrayList<Node> ans = new ArrayList<>();
        while(!stack.isEmpty()) {
            final Node pop = stack.pop();
            ans.add(pop);

            for(Node next : pop.nexts) {
                inDegreeMap.put(next, inDegreeMap.get(next) -1);

                if(inDegreeMap.get(next) == 0) {
                    stack.push(next);
                }
            }
        }

        return ans;
    }

    /**
     *
     * 1->5->8->3
     * v  V
     * 7->9->4
     * @param args
     */
    public static void main(String[] args) {

        int[][] matrix = {{1,1,5},{3,1,7},{6,5,8},{4,5,9},{2,7,9},{2,9,4}, {2,8,3}};

        final Graph generate = GraphGenerater.generate(matrix);

        final List<Node> sort = sort(generate);

        for (int i = 0; i < sort.size(); i++) {
            System.out.println(sort.get(i).value + " ");
        }

    }
}
