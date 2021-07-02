package c18;

/**
 * @author TK
 */
public class CardsInLine {

    public static int win1(int[] cards) {
        int first = f1(cards, 0, cards.length - 1);
        int second = g1(cards, 0, cards.length - 1);
        System.out.println("first:" + first);
        System.out.println("second:" + second);

        return Math.max(first, second);
    }

    public static int f1(int[] cards, int L, int R) {
        if (L == R) {
            return cards[L];
        }

        int p1 = cards[L] + g1(cards, L + 1, R);
        int p2 = cards[R] + g1(cards, L, R - 1);

        return Math.max(p1, p2);
    }

    public static int g1(int[] cards, int L, int R) {
        if (L == R) {
            return 0;
        }

        int p1 = f1(cards, L + 1, R);
        int p2 = f1(cards, L, R - 1);

        return Math.min(p1, p2);
    }


    public static int win2(int[] cards) {
        int[][] fmap = new int[cards.length][cards.length];
        int[][] gmap = new int[cards.length][cards.length];
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f2(cards, 0, cards.length - 1, fmap, gmap);
        int second = g2(cards, 0, cards.length - 1, fmap, gmap);
        System.out.println("first:" + first);
        System.out.println("second:" + second);

        return Math.max(first, second);
    }

    public static int f2(int[] cards, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }

        int ans;
        if (L == R) {
            ans = cards[L];
        } else {
            int p1 = cards[L] + g2(cards, L + 1, R, fmap, gmap);
            int p2 = cards[R] + g2(cards, L, R - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }

        fmap[L][R] = ans;
        return ans;
    }

    public static int g2(int[] cards, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }

        int ans = 0;
        if (L != R) {
            int p1 = f2(cards, L + 1, R, fmap, gmap);
            int p2 = f2(cards, L, R - 1, fmap, gmap);
            ans = Math.min(p1, p2);
        }

        gmap[L][R] = ans;
        return ans;
    }


    public static int win3(int[] cards) {
        int[][] fmap = new int[cards.length][cards.length];
        int[][] gmap = new int[cards.length][cards.length];
        /*for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }*/

        for (int i = 0; i < cards.length; i++) {
            fmap[i][i] = cards[i];
        }

        for(int i = 1; i< cards.length; i++) {

            int row = 0;
            int col = i;
            while(col < cards.length) {
                fmap[row][col] = Math.max(cards[col] + gmap[row][col-1], cards[row] + gmap[row+1][col] );
                gmap[row][col] = Math.min(fmap[row][col-1], fmap[row+1][col] );

                row++;
                col++;
            }
        }



        int first = fmap[0][cards.length-1];
        int second = gmap[0][cards.length-1];
        System.out.println("first:" + first);
        System.out.println("second:" + second);

        return Math.max(first, second);
    }

    public static void main(String[] args) {
        int[] cards = {50, 100, 10, 40};
        final int win1 = win1(cards);
        System.out.println(win1);

        final int win2 = win2(cards);
        System.out.println(win1);

        final int win3 = win3(cards);
        System.out.println(win3);
    }
}
