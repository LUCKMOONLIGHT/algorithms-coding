package _6.dfs.DFS;

/**
 * 单词搜索 - DFS
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 思路：
 * DFS(grid, i, j, word, index)
 */
public class existI {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};


    public boolean exist(char[][] board, String word) {
        if (board == null || board[0] == null || board.length == 0 || board[0].length == 0 ||
                word == null || word.length() == 0)
            return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && existDFS(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean existDFS(char[][] board, String word, int x, int y, int index) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length ||
                word.charAt(index) != board[x][y] || board[x][y] == '#')
            return false;
        if (index == word.length() - 1) return true;
        board[x][y] = '#'; //表示为访问过
        for (int i = 0; i < dx.length; i++) {
            if (existDFS(board, word, x + dx[i], y + dy[i], index + 1)) {
                return true;
            }
        }
        board[x][y] = word.charAt(index); //表示搜索不成功，还原单词
        return false;
    }
}
