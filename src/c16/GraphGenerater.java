package c16;

/**
 * @author TK
 */
public class GraphGenerater {

    /**
     *
     * @param matrix [weight, from, to]
     * @return
     */
    public static Graph generate(int[][] matrix) {

        final Graph graph = new Graph();

        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];

            Node fromNode = graph.nodes.get(from);
            if(null == fromNode) {
                fromNode = new Node(from);
            }

            Node toNode = graph.nodes.get(to);
            if(null == toNode) {
                toNode = new Node(to);
            }

            // graph
            graph.nodes.put(from, fromNode);
            graph.nodes.put(to, toNode);

            final Edge edge = new Edge(weight, fromNode, toNode);
            graph.edges.add(edge);


            // node
            fromNode.out++;
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);

            toNode.in++;

        }

        return graph;

    }
}
