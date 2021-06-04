package eight;

import util.TestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author TK
 */
public class TrieTree {
    public Node root;

    TrieTree() {
        root = new Node();
    }

    public class Node{
        public int pass;
        public int end;
        public Node[] nexts = new Node[26];
    }

    public void insert(String word) {
        final char[] chars = word.toCharArray();
        Node p = root;
        p.pass++;
        for (char aChar : chars) {
            final int index = aChar - 'a';
            if (p.nexts[index] == null) {
                p.nexts[index] = new Node();
            }
            p = p.nexts[index];
            p.pass++;
        }
        p.end++;
    }

    public int search(String word) {
        final char[] chars = word.toCharArray();
        Node p = root;
        for(char aChar : chars) {
            final int index = aChar - 'a';
            if(null == p.nexts[index]) {
                return 0;
            }
            p = p.nexts[index];
        }
        return p.end;
    }

    public void delete(String word) {
        if(search(word) == 0) {
            return;
        }

        final char[] chars = word.toCharArray();
        Node p = root;
        for(char aChar : chars) {
            final int index = aChar - 'a';
            if(p.nexts[index].pass - 1 == 0) {
                p.nexts[index] = null;
                return;
            }else {
                p = p.nexts[index];
            }
            p.pass--;
        }
        p.end--;

    }

    public int prefixNumber(String word) {
        final char[] chars = word.toCharArray();

        Node p = root;
        for(char aChar : chars) {
            final int index = aChar - 'a';
            if(p.nexts[index] == null) {
                return 0;
            }
            p = p.nexts[index];
        }
        return p.pass;
    }


    public static class Right{
        public Map<String, Integer> map = new HashMap();

        public void insert(String word) {
            if(map.containsKey(word)) {
                map.put(word, map.get(word)+1);
            }else {
                map.put(word, 1);
            }
        }

        public int search(String word) {

            if(map.containsKey(word)) {
                return map.get(word);
            }

            return 0;
        }

        public void delete(String word) {
            if(!map.containsKey(word)) {
                return;
            }
            final Integer count = map.get(word);
            if(count - 1 == 0) {
                map.remove(word);
            }else {
                map.put(word, count - 1);
            }
        }

        public int prefixNumber(String word) {
            final Set<String> keys = map.keySet();
            int count = 0;
            for (String key: keys) {
                if(key.startsWith(word)) {
                    count += map.get(key);
                }
            }
            return count;
        }

    }



    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLen = 20;

        for (int i = 0; i < testTimes; i++) {
            final String word = TestUtil.generateRandomString(maxLen);
            final TrieTree trieTree = new TrieTree();
            trieTree.insert(word);

            final Right right = new Right();
            right.insert(word);

            if(trieTree.search(word) != right.search(word)) {
                System.out.println("failed");
                return;

            }

            if(trieTree.prefixNumber(word) != right.prefixNumber(word)) {
                System.out.println("failed");
                return;
            }

            final double random = Math.random();
            if(random > 0.8) {
                trieTree.delete(word);
                right.delete(word);
            }

        }

        System.out.println("success");


    }
}
