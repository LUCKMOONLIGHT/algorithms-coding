package _6.dfs.trackback;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 79.单词搜索 - DFS
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 思路：
 * DFS(grid, i, j, word, index)
 * 1.二层for循环迭代每一个元素，从每一个元素开始dfs，如果dfs返回true，则返回true
 * 2.dfs判断边界和是否相等，相等的话判断k的位置，达到最后返回true，否则保存当前board值，修改成*，dfs，还原board值，返回res
 */
public class existI {
    public boolean exist(char[][] board, String word) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, int i, int j, String word, int k){
        //边界判断||访问判断
        if(i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]!=word.charAt(k)) return false;
        //条件结果判断
        if(k == word.length() - 1) return true;
        //访问更新
        char tmp = board[i][j];
        board[i][j] = '*';
        boolean res = dfs(board, i+1, j, word, k+1) || dfs(board, i-1, j, word, k+1) ||
                dfs(board, i, j-1, word, k+1) || dfs(board, i, j+1, word, k+1);
        //访问还原
        board[i][j] = tmp;
        return res;
    }

    //212. 单词搜索 II  11ms
    //思路：先将words数组中所有待查找的字符串组织成一颗字符串前缀树，之后在DFS的过程中使用这棵前缀树帮助筛选

    public List<String> findWords(char[][] board, String[] words) {
        //构建字典树
        Trie myTrie=new Trie();
        TrieNode root=myTrie.node;
        //字符串插入字典树
        for(String s:words)
            myTrie.insert(root, s);

        //使用set防止重复[["a","a"]]["a"]   预期["a"]  输出["a","a"]
        Set<String> result = new HashSet<>();
        for(int i=0;i<board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                dfs(board,i,j,result,root);
            }
        }
        return new LinkedList<String>(result);
    }
    private void dfs(char[][] board,int i,int j, Set<String> result,TrieNode cur){
        //边界以及是否已经访问判断,原始数组做标记作为是否访问，字典树的子节点是否存在
        if(i<0||i>=board.length||j<0||j>=board[0].length||board[i][j] == '*' || cur.child[board[i][j]-'a'] == null)
            return;
        //如果子节点存在的话，更新字典树节点
        cur=cur.child[board[i][j]-'a'];
        //原数组做标记表示已经访问了
        char c = board[i][j];
        board[i][j] = '*';

        //如果访问到了字典叶子节点上，说明前面的字符都匹配上了
        if(cur.end)
        {
            result.add(cur.val);
            //找到单词后不能回退还原数组，因为可能是“ad” “addd”这样的单词得继续回溯
        }
        //继续dfs查找
        dfs(board,i+1,j,result,cur);
        dfs(board,i,j+1,result,cur);
        dfs(board,i,j-1,result,cur);
        dfs(board,i-1,j,result,cur);
        //最后要回退，因为下一个起点可能会用到上一个起点的字符
        board[i][j] = c;
    }

    class Trie{
        TrieNode node;
        public Trie(){node = new TrieNode();}
        public void insert(TrieNode cur, String str){
            for(char c:str.toCharArray()){
                if(cur.child[c - 'a'] == null)
                    cur.child[c - 'a'] = new TrieNode();
                cur = cur.child[c - 'a'];
            }
            cur.end = true;
            cur.val = str;
        }
        public boolean find(TrieNode cur, String str) {
            for(char c:str.toCharArray()){
                if (cur.child[c - 'a'] == null)
                    return false;
                cur = cur.child[c - 'a'];
            }
            return cur.end;
        }
        public boolean startsWith(TrieNode cur,String str) {
            for(char c:str.toCharArray()){
                if(cur.child[c - 'a']==null)
                    return false;
                cur = cur.child[c - 'a'];
            }
            return true;
        }
    }

    class TrieNode{
        String val;
        TrieNode[] child = new TrieNode[26];
        boolean end;
        public TrieNode(){}
    }


    //Trie树(字典树)(前缀树)
    //Trie的核心思想是空间换时间。利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的
    /**基本性质
     * 1.根节点不包含字符，每条边代表一个字符
     * 2.从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串
     * 3.每个节点的所有子节点包含的字符都不相同
     */

}
