package c17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TK
 */
public class PrintAllPermutation {

    public static void permutation(String s) {

        final List rest = new ArrayList<>();
        final List<String> ans = new ArrayList<>();

        for(Character c : s.toCharArray()) {
            rest.add(c);
        }
        f(rest, ans, "");

        for (String str : ans) {
            System.out.println(str);
        }

    }

    public static void f(List<Character> rest, List<String> ans, String path) {
        if(rest.isEmpty()) {
            ans.add(path);
        }

        for(int i = 0; i < rest.size(); i++) {
            final Character curr = rest.get(i);
            final Character remove = rest.remove(i);
            f(rest, ans, path + curr);
            rest.add(i, remove);
        }

    }


    public static void permutation2(String s) {
        final List<String> ans = new ArrayList<>();

        g(s.toCharArray(), 0, ans);

        for (String str : ans) {
            System.out.println(str);
        }

    }

    public static void g(char[] str, int index, List<String> ans) {
        if(index == str.length) {
            ans.add(String.valueOf(str));
        }

        boolean[] visited = new boolean[256];
        for(int i = index; i < str.length; i++) {
            if(!visited[str[i]]) {
                visited[str[i]] = true;
                swap(str, index, i);
                g(str, index + 1, ans);
                swap(str, index, i);
            }

        }

    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) {
        permutation("acbc");

        System.out.println("======================2");
        permutation2("acbc");
    }
}
