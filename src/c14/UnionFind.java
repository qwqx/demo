package c14;

import util.ListUtil;

import java.util.*;

/**
 * @author TK
 */
public class UnionFind<V> {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    Map<V, Node<V>> valueMap = new HashMap<>();
    Map<Node<V>, Node<V>> parentMap = new HashMap<>();
    Map<Node<V>, Integer> sizeMap = new HashMap<>();

    UnionFind(List<V> values) {
        for(V v : values) {
            final Node<V> node = new Node<>(v);
            valueMap.put(v, node);
            parentMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    public Node findFather(V a) {
        Node curr = valueMap.get(a);
        final Set<Node> parentSet = new HashSet<>();
        while (curr != parentMap.get(curr)) {
            parentSet.add(curr);
            curr = parentMap.get(curr);
        }

        for(Node node : parentSet) {
            parentMap.put(node, curr);
        }

        return curr;
    }



    public boolean isSameSet(V a, V b) {
        return findFather(a) == findFather(b);
    }

    public void union(V a, V b) {
        final Node fatherA = findFather(a);
        final Node fatherB = findFather(b);

        Node big = sizeMap.get(fatherA) >= sizeMap.get(fatherB) ? fatherA : fatherB;
        Node small = big == fatherA ? fatherB : fatherA;

        parentMap.put(small, big);
        sizeMap.put(big, sizeMap.get(fatherA) + sizeMap.get(fatherB));
        sizeMap.remove(small);
    }

    public int size() {
        return sizeMap.size();
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        final UnionFind<String> unionFind = new UnionFind<>(list);
        unionFind.union("a", "b");
        unionFind.union("a", "c");
        unionFind.union("a", "f");
        System.out.println(unionFind.isSameSet("a", "f"));
    }


}
