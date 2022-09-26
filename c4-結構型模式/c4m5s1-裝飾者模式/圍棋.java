/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class 圍棋 {
    private final static int[][] board = {
            {0, 0, 0, 1, 2, 2},
            {0, 2, 2, 1, 2, 1},
            {2, 1, 1, 2, 1, 2},
            {0, 2, 1, 1, 2, 1},
            {0, 0, 2, 2, 1, 2},
            {0, 0, 0, 0, 2, 0}
    };

    public static void main(String[] args) {
        boolean[][] visit = new boolean[board.length][board.length];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                boolean answer = 是否被圍住(visit, y, x);
                System.out.print( answer ? "1" : "0");
            }
            System.out.println();
        }
    }

    private static boolean 是否被圍住(boolean[][] visit, int y, int x) {
        if (board[y][x] == 0) {
            return false;
        }
        int[][] surround = {{y, x - 1}, {y + 1, x}, {y, x + 1}, {y - 1, x}, };
        visit[y][x] = true;
        for (int[] point : surround) {
            int vY = point[0], vX = point[1];
            if (vY >= 0 && vX >= 0 && vY < visit.length && vX < visit[vY].length
                    && !visit[vY][vX]) {
                if (board[vY][vX] == 0 || (board[vY][vX] == board[y][x] && !是否被圍住(visit, vY, vX))) {
                    return false;
                }
            }
        }
        return true;
    }

}
