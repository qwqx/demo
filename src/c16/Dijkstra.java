package c16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author TK
 */
public class Dijkstra {

    public static Map<Node, Integer> dijkstra1(Node from) {

        final Set<Node> selected = new HashSet<>();
        final Map<Node, Integer> map = new HashMap<>();
        for(Edge edge : from.edges) {
            map.put(edge.to, edge.weight);
        }

        Node min = findMinInstanceAndUnselected(map, selected);
        selected.add(min);

        while(null != min) {

            for(Edge edge : min.edges) {
                int instance = map.get(min);
                if(map.containsKey(edge.to)) {
                    map.put(edge.to, Math.min(instance + edge.weight, map.get(edge.to)));
                }else {
                    map.put(edge.to, instance + edge.weight);
                }

            }

            min = findMinInstanceAndUnselected(map, selected);
            selected.add(min);
        }

        return map;
    }

    private static Node findMinInstanceAndUnselected(Map<Node, Integer> map, Set<Node> selected) {
        Node min = null;
        int minInstance = Integer.MAX_VALUE;
        for(Node node : map.keySet()) {
            if(map.get(node) < minInstance && !selected.contains(node)) {
                min = node;
                minInstance = map.get(node);
            }
        }
        return min;
    }

    public static int[] dijkstra2(int[][] graph, int from) {
        int size = graph.length;
        int[] instance = new int[size];
        boolean[] visited = new boolean[size];

        for(int i = 0; i< size; i++) {
            instance[i] = graph[from][i];
        }
        visited[from] = true;
        int minInstance = 0;
        int minIndex = from;
        /*for(int i = 0; i< size; i++) {
            if(!visited[i] && instance[i] < minInstance) {
                minInstance = instance[i];
                minIndex = i;
                visited[from] = true;
            }
        }*/

        int visitedCount = 1;
        while(visitedCount < size) {
            //minInstance = 99;//Integer.MAX_VALUE;
            for(int i = 0; i< size; i++) {
                if(!visited[i] && i != minIndex && instance[i] > minInstance + graph[minIndex][i]) {
                    instance[i] = minInstance + graph[minIndex][i];
                }
            }
            minInstance = 99;
            for(int i = 0; i< size; i++) {
                if(!visited[i] && instance[i] < minInstance) {
                    minInstance = instance[i];
                    minIndex = i;
                    visited[from] = true;
                }
            }
            visitedCount++;
        }

        return instance;

    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class Heap{
        public Node[] nodes;
        public Map<Node, Integer> indexMap;
        public Map<Node, Integer> instanceMap;
        int size;

        Heap(int size) {
            nodes = new Node[size];
            indexMap = new HashMap<>();
            instanceMap = new HashMap<> ();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void insertOrUpdateOrIgnore(Node node, int instance) {
            // update
            if(isInHeap(node)) {
                instanceMap.put(node, Math.min(instanceMap.get(node), instance));
                insertHeapify(node, indexMap.get(node));
            }

            // insert
            if(!isEntered(node)) {
                nodes[size] = node;
                indexMap.put(node, size);
                instanceMap.put(node, instance);

               insertHeapify(node, size++);
            }

            // isEntered ignore
        }

        public NodeRecord pop() {
            final NodeRecord nodeRecord = new NodeRecord(nodes[0], instanceMap.get(nodes[0]));
            swap(0, size - 1);
            indexMap.put(nodes[size - 1], -1);
            instanceMap.remove(nodes[size - 1]);


            heapify(0, --size);
            nodes[size] = null;

            return nodeRecord;
        }


        private void insertHeapify(Node node, int index) {
            while(instanceMap.get(nodes[(index - 1) / 2]) > instanceMap.get(nodes[index])) {
                swap(index, (index -1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index, int size) {

            /*while (index < size) {
                int left = index * 2 + 1;
                int right = left + 1;
                int leftInstance = left >= size ? Integer.MAX_VALUE : instanceMap.get(nodes[left]);
                int rightInstance = right >= size ? Integer.MAX_VALUE : instanceMap.get(nodes[right]);

                int minIndex = leftInstance <= rightInstance ? left : right;
                int minInstance = leftInstance <= rightInstance ? leftInstance : rightInstance;
                if (instanceMap.get(nodes[index]) > minInstance) {
                    swap(index, minIndex);
                    index = minIndex;
                }else {
                    break;
                }
            }*/

            int left = index * 2 + 1;
            while (left < size) {
                int smallIdx = left + 1 < size ? (instanceMap.get(nodes[left]) < instanceMap.get(nodes[left + 1]) ? left : left + 1) : left;

                if (instanceMap.get(nodes[index]) < instanceMap.get(nodes[smallIdx])) {
                    smallIdx = index;
                }
                if(smallIdx == index) {
                    break;
                }

                swap(index, smallIdx);
                index = smallIdx;
                left = index * 2 + 1;
            }

        }

        public boolean isEntered(Node node) {
            return indexMap.containsKey(node);
        }

        public boolean isInHeap(Node node) {
            return isEntered(node) && indexMap.get(node) != -1;
        }

        public void swap(int i, int j) {
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;

            indexMap.put(nodes[i], i);
            indexMap.put(nodes[j], j);
        }


    }

    /**
     * 加强堆
     * ☆☆☆☆
     * @param from
     * @param size
     * @return
     */
    public static Map<Node, Integer> dijkstra3(Node from, int size) {
        final Map<Node, Integer> ans = new HashMap<>();

        final Heap heap = new Heap(size);
        heap.insertOrUpdateOrIgnore(from, 0);

        while(!heap.isEmpty()) {
            final NodeRecord pop = heap.pop();
            Node node = pop.node;
            int instance = pop.distance;

            for(Edge edge : node.edges) {
                heap.insertOrUpdateOrIgnore(edge.to, instance + edge.weight);
            }

            ans.put(node, instance);
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

        //
        int[][] matrix = {{1, 1, 5}, {3, 1, 7}, {6, 5, 8}, {4, 5, 9}, {2, 7, 9}, {2, 9, 4}, {2, 8, 3}};

        final Graph generate = GraphGenerater.generate(matrix);

        final Map<Node, Integer> map = dijkstra1(generate.nodes.get(1));

        for (Node node : map.keySet()) {
            System.out.println(node.value + "-->" + map.get(node).intValue());
        }

        System.out.println("堆优化：");

        final Map<Node, Integer> nodeIntegerMap = dijkstra3(generate.nodes.get(1), 7);
        for (Node node : map.keySet()) {
            System.out.println(node.value + "-->" + map.get(node).intValue());
        }


        System.out.println("");
        int[][] matrix2 = {
                {00, 99, 99, 99, 99, 99, 99, 99, 99, 99},
                {99, 00, 99, 99, 99, 01, 99, 03, 99, 99},
                {99, 99, 00, 99, 99, 99, 99, 99, 99, 99},
                {99, 99, 99, 00, 99, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 00, 99, 99, 99, 99, 99},
                {99, 99, 99, 99, 99, 00, 99, 99, 06, 04},
                {99, 99, 99, 99, 99, 99, 00, 99, 99, 99},
                {99, 99, 99, 99, 99, 99, 99, 00, 99, 02},
                {99, 99, 99, 02, 99, 99, 99, 99, 00, 99},
                {99, 99, 99, 99, 02, 99, 99, 99, 99, 00},

        };

        final int[] ints = dijkstra2(matrix2, 1);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(i + "-->" + ints[i]);
        }
    }
}
