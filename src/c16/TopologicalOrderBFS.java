package c16;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author TK
 */
public class TopologicalOrderBFS {

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        final HashMap<DirectedGraphNode, Integer> inDegreeMap = new HashMap<>();
        for(DirectedGraphNode node : graph) {
            inDegreeMap.put(node, 0);
        }

        for(DirectedGraphNode node : graph) {
            for(DirectedGraphNode next : node.neighbors) {
                inDegreeMap.put(next, inDegreeMap.get(next) + 1);
            }
        }

        Stack<DirectedGraphNode> zero = new Stack<>();

        for(DirectedGraphNode node : inDegreeMap.keySet()) {
            if(inDegreeMap.get(node) == 0) {
                zero.add(node);
            }
        }

        final ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while(!zero.isEmpty()) {
            final DirectedGraphNode pop = zero.pop();
            ans.add(pop);

            for(DirectedGraphNode next : pop.neighbors) {
                inDegreeMap.put(next, inDegreeMap.get(next) - 1);
                if(inDegreeMap.get(next) == 0) {
                    zero.push(next);
                }
            }

        }


        return ans;
    }
}
