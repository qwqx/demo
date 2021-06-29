package c16;

import java.util.*;

/**
 * @author TK
 */
public class TopologicalOrderDFS2 {

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record{
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode n, long o) {
            node = n;
            nodes = o;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(List<DirectedGraphNode> graph) {
        final Map<DirectedGraphNode, Record> records = new HashMap<>();
        for(DirectedGraphNode node : graph) {
            f(node, records);
        }
        final ArrayList<Record> values = new ArrayList<>(records.values());
        values.sort((s1,s2) -> s1.nodes == s2.nodes ? 0 : (s1.nodes > s2.nodes ? -1 : 1));

        final ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for(Record r : values) {
            ans.add(r.node);
        }

        return ans;
    }

    public static Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> records) {
        if(records.containsKey(node)) {
            return records.get(node);
        }

        long nodes = 0;
        for (DirectedGraphNode next : node.neighbors) {
            nodes += f(next, records).nodes;
        }

        final Record record = new Record(node, nodes + 1);
        records.put(node, record);

        return record;
    }

}
