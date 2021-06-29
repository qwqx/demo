package c16;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author TK
 */
public class TopologicalOrderDFS1 {

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
        public int deep;

        Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        final HashMap<DirectedGraphNode, Record> recordMap = new HashMap<>();
        for(DirectedGraphNode curr : graph) {
            f(curr, recordMap);
        }

        final ArrayList<Record> records = new ArrayList<>(recordMap.values());
        records.sort(Comparator.<Record>comparingInt(s -> s.deep).reversed());

        final ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for(Record r : records) {
            ans.add(r.node);
        }
        return ans;
    }

    public static Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> recordMap) {
        if(recordMap.containsKey(node)) {
            return recordMap.get(node);
        }

        int nextDeep = 0;
        for(DirectedGraphNode next : node.neighbors) {
            nextDeep = Math.max(nextDeep, f(next, recordMap).deep);
        }

        final Record record = new Record(node, nextDeep + 1);
        recordMap.put(node, record);

        return record;
    }


}
