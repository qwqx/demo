package util;

/**
 * @author TK
 */
public class BTUtil {

    public static BTNode generateBT(int maxLevel) {
        return generateBT(maxLevel, 10D);
    }

    // sparseDegree > 1 越小越稀疏
    public static BTNode generateBT(int maxLevel, Double sparseDegree) {
        return generateRandomBT(1, maxLevel, sparseDegree);
    }

    public static BTNode generateRandomBT(int level, int maxLevel, Double sparseDegree) {

        if (level > maxLevel) {
            return null;
        }

        final double random = Math.random();
        //System.out.println("random:" + random + ",level:" + level);
        if (random < (level / sparseDegree)) {
            return null;
        }
        final BTNode root = new BTNode((int) (Math.random() * 10));
        root.left = generateRandomBT(level + 1, maxLevel, sparseDegree);
        root.right = generateRandomBT(level + 1, maxLevel, sparseDegree);
        return root;
    }

    public static void print(BTNode root) {
        System.out.println("BT:");
        inOrderPrint(root, 0, "H", 17);
        System.out.println();
    }

    public static void inOrderPrint(BTNode root, int height, String to, int len) {
        if (null == root) {
            return;
        }

        String val = to + root.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenL - lenM;
        val = getSpace(lenL) + val + getSpace(lenR);

        inOrderPrint(root.right, height + 1, "v", len);
        System.out.println(getSpace(height * len) + val);
        inOrderPrint(root.left, height + 1, "^", len);
    }

    public static String getSpace(int len) {
        String space = " ";
        final StringBuffer sb = new StringBuffer();
        while (len > 0) {
            sb.append(space);
            len--;
        }
        return sb.toString();
    }

    public static boolean same(BTNode root1, BTNode root2) {

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null && root2 != null) {
            return false;
        }

        if (root1 != null && root2 == null) {
            return false;
        }

        if (root1.value != root2.value) {
            return false;
        }

        return same(root1.left, root2.left) && same(root1.right, root2.right);
    }

    public static BTNode getRandomNode(BTNode root, int height) {
        int i = 0;
        BTNode curr = root;
        int n = (int) (Math.random() * height) + 1;
        while (i < n && null != curr) {
            final boolean left = Math.random() > 0.5;
            if (left && null != curr.left) {
                curr = curr.left;
            } else if (!left && null != curr.right) {
                curr = curr.right;
            }
            i++;
        }

        return curr;
    }
}
