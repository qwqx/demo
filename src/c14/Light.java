package c14;

import java.util.HashSet;

/**
 * @author TK
 */
public class Light {

    // str eg:".#...#..#."

    /**
     * 照亮当前位置，灯放哪
     * @param str
     * @return
     */
    public static int minLight(String str) {
        final char[] chars = str.toCharArray();

        int lightCount = 0;
        for (int i = 0; i < chars.length; ) {
            if ('#' == chars[i]) {
                i++;
                continue;
            } else {
                lightCount++;
                if (i + 1 == chars.length) {
                    break;
                }

                if ('#' == chars[i + 1]) {
                    i = i + 2;
                }/*else if('#' == chars[i + 2]) {
                    i = i+3;
                }*/ else {
                    i = i + 3;
                }
            }

        }

        return lightCount;
    }

    public static int minLight2(String str) {
        final String[] split = str.split("#");
        int count = 0;
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() == 0) {
                continue;
            }
            count += Math.max(1, split[i].length() % 3 > 0 ? (split[i].length() / 3 + 1) : split[i].length() / 3);
        }

        return count;
    }

    public static int minLight3(String str) {
        return process(str.toCharArray(), 0, new HashSet<>());
    }

    public static int process(char[] chars, int index, HashSet<Integer> lights) {
        if (index == chars.length) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != '#') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            int no = process(chars, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (chars[index] == '.') {
                lights.add(index);
                yes = process(chars, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    public static String generateStr() {
        String str = "";
        for (int i = 0; i < 10; i++) {
            str += (Math.random() > 0.7) ? "#" : ".";
        }
        return str;
    }

    public static void main(String[] args) {
        int testTimes = 100000;

        for (int i = 0; i < testTimes; i++) {
            final String s = generateStr();
            //System.out.println(s);

            if(minLight(s) != minLight2(s) || minLight2(s) != minLight3(s)) {
                System.out.println(s);
                System.out.println(minLight(s));
                System.out.println(minLight2(s));
                System.out.println(minLight3(s));
                System.out.println("failed");
                return;
            }

        }
        System.out.println("success");
    }
}
