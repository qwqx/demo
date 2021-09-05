package c19;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TK
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 */
public class StickersToSpellWord {

    public static int minStickers1(String[] stickers, String target) {
        final int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            String rest = minus1(target, stickers[i]);
            if (!rest.equals(target)) {
                min = Math.min(min, process1(stickers, rest));
            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int minStickers2(String[] stickers, String target) {

        int[][] count = new int[stickers.length][26];
        for (int j = 0; j < stickers.length; j++) {
            final char[] chars = stickers[j].toCharArray();
            for (int i = 0; i < chars.length; i++) {
                count[j][chars[i] - 'a']++;
            }
        }
        int ans = process2(count, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process2(int[][] sCount, String t) {
        if (t.length() == 0) {
            return 0;
        }

        final char[] tChar = t.toCharArray();
        int[] tCount = new int[26];
        for (Character c : tChar) {
            tCount[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < sCount.length; i++) {
            if (sCount[i][tChar[0] - 'a'] > 0) {

                final StringBuffer sb = new StringBuffer();
                for (int j = 0; j < tCount.length; j++) {
                    final int num = tCount[j] - sCount[i][j];
                    while (num > 0) {
                        sb.append((char) j + 'a');
                    }
                }

                min = Math.min(min, process2(sCount, sb.toString()));

            }
        }

        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    /**
     * ☆☆☆☆
     * @param stickers
     * @param target
     * @return
     */
    public static int minStickers3(String[] stickers, String target) {

        int[][] count = new int[stickers.length][26];
        for (int j = 0; j < stickers.length; j++) {
            final char[] chars = stickers[j].toCharArray();
            for (int i = 0; i < chars.length; i++) {
                count[j][chars[i] - 'a']++;
            }
        }

        final Map<String, Integer> cache = new HashMap<>();
        cache.put("", 0);
        int ans = process3(count, target, cache);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process3(int[][] sCount, String t, Map<String, Integer> cache) {
        if (cache.containsKey(t)) {
            return cache.get(t);
        }

        final char[] tChar = t.toCharArray();
        int[] tCount = new int[26];
        for (Character c : tChar) {
            tCount[c - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < sCount.length; i++) {
            if (sCount[i][tChar[0] - 'a'] > 0) {

                final StringBuilder sb = new StringBuilder();
                for (int j = 0; j < tCount.length; j++) {
                    if (tCount[j] <= 0) {
                        continue;
                    }
                    int num = tCount[j] - sCount[i][j];
                    while (num > 0) {
                        sb.append((char) (j + 'a'));
                        num--;
                    }
                }

                min = Math.min(min, process3(sCount, sb.toString(), cache));

            }
        }

        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        cache.put(t, ans);
        return ans;
    }


    public static String minus(String target, String sub) {
        for (int i = 0; i < sub.length(); i++) {
            target = target.replaceFirst(String.valueOf(sub.charAt(i)), "");
        }
        return target;
    }

    public static String minus1(String target, String sub) {
        final char[] targetChars = target.toCharArray();
        final char[] subChars = sub.toCharArray();

        int[] count = new int[26];
        for (int i = 0; i < targetChars.length; i++) {
            count[targetChars[i] - 'a']++;
        }

        for (int i = 0; i < subChars.length; i++) {
            count[subChars[i] - 'a']--;
        }

        final StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i]; j++) {
                stringBuffer.append((char) (i + 'a'));
            }
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        int testTime = 1000;
        String[] s = {"with","example","science"};
        final int num = minStickers3(s, "thehat");


    }
}
