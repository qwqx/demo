package c16;

import java.util.*;

/**
 * @author TK
 */
public class Kruskal {

    public static class UnionFind{
        Map<Node, Node> parentMap;
        Map<Node, Integer> sizeMap;

        UnionFind() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSet(Collection<Node> nodes) {
            for(Node node : nodes) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node node) {

            Node curr = node;
            final Stack<Node> help = new Stack<>();
            while (curr != parentMap.get(curr)) {
                help.push(curr);
                curr = parentMap.get(curr);
            }

            while (!help.isEmpty()) {
                parentMap.put(help.pop(), curr);
            }

            return curr;
        }

        public boolean isSameSet(Node node1, Node node2) {
            return findFather(node1) == findFather(node2);
        }

        public void union(Node node1, Node node2) {
            final Node father1 = findFather(node1);
            final Node father2 = findFather(node2);

            if(father1 != father2) {
                Node max = sizeMap.get(father1) >= sizeMap.get(father2) ? father1 : father2;
                Node min = max == father1 ? father2 : father1;

                parentMap.put(min, max);
                sizeMap.put(max, sizeMap.get(father1) + sizeMap.get(father2));
                sizeMap.remove(min);
            }

        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        final UnionFind unionFind = new UnionFind();
        unionFind.makeSet(graph.nodes.values());

        final PriorityQueue<Edge> stack = new PriorityQueue<>(Comparator.comparingInt((e -> e.weight)));
        for(Edge edge : graph.edges) {
            stack.add(edge);
        }

        final Set<Edge> ans = new HashSet<>();
        while (!stack.isEmpty()) {
            final Edge curr = stack.poll();
            if(!unionFind.isSameSet(curr.from, curr.to)) {
                ans.add(curr);
                unionFind.union(curr.from, curr.to);
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

        final Set<Edge> edges = kruskalMST(generate);

        for (Edge edge : edges) {
            System.out.println(edge.from.value + "-->" + edge.to.value + ",  " + edge.weight);
        }
    }
}
