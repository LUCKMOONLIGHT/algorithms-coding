#### [125. 验证回文串](https://leetcode-cn.com/problems/valid-palindrome/)**简单**

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

```java
//难度：简单
//回文字符串反转后与原字符串相等equal
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) return true;
        int len = s.length();
        String str = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<len;i++){
            char c = str.charAt(i);
            if ((c >= '0' && c <= '9') || (c >='a' && c <= 'z')){
                sb.append(c);
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }
}
```

#### [131. 分割回文串](https://leetcode-cn.com/problems/palindrome-partitioning/)**中等**

给定一个字符串 *s*，将 *s* 分割成一些子串，使每个子串都是回文串。

返回 *s* 所有可能的分割方案。

```java
//难度：中等 5ms
//回溯法
/**
输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]
**/
class Solution {
   public static List<List<String>> partition(String s) {
	List<List<String>> res = new ArrayList<>();
	backTrack(s.toCharArray(), 0, new ArrayList<>(), res);
	return res;
    }
    private static void backTrack(char[] s, int idx, List<String> cur, List<List<String>> res){
	if(idx == s.length){
	    res.add(new ArrayList<>(cur));
	    return;
	}
	for(int i = idx; i < s.length; i++){
	    if(isPalind(s, idx, i)){
                cur.add(new String(s, idx, i-idx+1));
	        backTrack(s, i + 1, cur, res);
	        cur.remove(cur.size()-1);
            }
	}
    }
    private static boolean isPalind(char[] s, int i, int j){
	while(i < j){
	    if(s[i++] != s[j--])
	        return false;
	}
	return true;
    }
}
```

#### [139. 单词拆分](https://leetcode-cn.com/problems/word-break/)**中等**

给定一个**非空**字符串 *s* 和一个包含**非空**单词列表的字典 *wordDict*，判定 *s* 是否可以被空格拆分为一个或多个在字典中出现的单词。

```java
/**
难度：中等
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false

【笔记】动态规划。dp[i]表示字符串s的前i个字符能否拆分成wordDict。
**/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 可以类比于背包问题
        int n = s.length();
        // memo[i] 表示 s 中以 i - 1 结尾的字符串是否可被 wordDict 拆分
        boolean[] memo = new boolean[n + 1];
        memo[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (memo[j] && wordDict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[n];
    }
}
```

#### [344. 反转字符串](https://leetcode-cn.com/problems/reverse-string/)**简单**

```java
class Solution {
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length-1;
        while(i < j){
            swap(s,i,j);
            i++;
            j--;
        }
    }
    public static void swap(char[] s,int i,int j){
        char tmp = s[j];
        s[j] = s[i];
        s[i] = tmp;
    }
}
```

#### [387. 字符串中的第一个唯一字符](https://leetcode-cn.com/problems/first-unique-character-in-a-string/)**简单**

给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

```java
//字符映射到数组位置的方法
class Solution {
    public int firstUniqChar(String s) {
        int[] mapping = new int[26];
        for(int i=0;i<s.length();i++){
            mapping[s.charAt(i) - 'a']++;
        }
        
        for(int j=0;j<s.length();j++){
            if(mapping[s.charAt(j) - 'a'] ==  1) return j;
        }
        return -1;
    }
}
```

#### [242. 有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/)**简单**

给定两个字符串 *s* 和 *t* ，编写一个函数来判断 *t* 是否是 *s* 的一个字母异位词。

```
输入: s = "anagram", t = "nagaram"
输出: true
```

```java
//字符映射到数组位置的方法
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] m1 = new int[26];
        int[] m2 = new int[26];
        for(int i=0;i<s.length();i++)
            m1[s.charAt(i) - 'a']++;
        for(int j=0;j<t.length();j++)
            m2[t.charAt(j) - 'a']++;
        for(int k=0;k<26;k++)
            if(m1[k] != m2[k]) return false;
        return true;
    }
}
```

#### [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)**中等**

实现一个 Trie (前缀树)，包含 `insert`, `search`, 和 `startsWith` 这三个操作。

```java
/**
Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true

236 ms   77.5 MB
**/
class Trie {

    TreeNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode("");
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        helper(root,0,word);
    }

    public void helper(TreeNode root,int idx,String word){
        if(idx>=word.length()){
            root.end=true;
            return;
        }
        if(root.nodes==null){
            root.nodes = new ArrayList();
            TreeNode t = new TreeNode(String.valueOf(word.charAt(idx)));
            root.nodes.add(t);
            helper(t,idx+1,word);
        }else{
            TreeNode next = null;
            for(TreeNode t:root.nodes){
                if(t.val.equals(String.valueOf(word.charAt(idx)))){
                    next=t;
                    break;
                }
            }
            if(next==null){
                next = new TreeNode(String.valueOf(word.charAt(idx)));
                root.nodes.add(next);
                helper(next,idx+1,word);
            }else{
                helper(next,idx+1,word);
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return helperSeach(root,0,word);
    }

    public boolean helperSeach(TreeNode t,int idx,String word){
        if(idx>=word.length()&&t.end){return true;}
        else if(idx>=word.length()||t.nodes==null){return false;}
        for(TreeNode tmp:t.nodes){
            if(tmp.val.equals(String.valueOf(word.charAt(idx)))){
                return helperSeach(tmp,idx+1,word);
            }
        }
        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return helperStartsWith(root,0,prefix);
    }

    public boolean helperStartsWith(TreeNode t,int idx,String word){
        if(idx>=word.length()){return true;}
        else if(t.nodes==null){return false;}
        for(TreeNode tmp:t.nodes){
            if(tmp.val.equals(String.valueOf(word.charAt(idx)))){
                return helperStartsWith(tmp,idx+1,word);
            }
        }
        return false;
    }

    public class TreeNode{
        public boolean end = false;
        public String val;
        public List<TreeNode> nodes;
        public TreeNode(String val){
            this.val =val;
        }
    }

}

```



#### [140. 单词拆分 II](https://leetcode-cn.com/problems/word-break-ii/)**困难**

给定一个**非空**字符串 *s* 和一个包含**非空**单词列表的字典 *wordDict*，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

```java
/**
13 ms,35.4MB
**/
class Solution {
    Set<String> set;
    List<String> ans;
    public List<String> wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>();
        ans = new ArrayList<>();
        if(wordBreak_check(s,wordDict)==false){
            return ans;
        }
        for(String tmpS:wordDict){
            set.add(tmpS);
        }
        String cur = "";
        dfs(s,cur);
        return ans;
    }
    public void dfs(String last, String pre){
        if(set.contains(last)){
            ans.add(pre.concat(last));
        }
        int len = last.length();
        for(int i=1;i<len;i++){
            if(set.contains(last.substring(0,i))){
                dfs(last.substring(i, len), pre.concat(last.substring(0, i)).concat(" "));
            }
        }
    }
    public boolean wordBreak_check(String s, List<String> wordDict) {
        int maxWordLength = 0;
        for(int i=0;i<wordDict.size();i++){
            maxWordLength = Math.max(maxWordLength,wordDict.get(i).length());
        }
        boolean[] dp = new boolean [s.length()+1];
        dp[0] = true;
        for(int i=1;i<s.length()+1;i++){
            int x = i-maxWordLength>0?i-maxWordLength:0;
            for(int j=x;j<i;j++){
                if(dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
```

#### 

#### [212. 单词搜索 II](https://leetcode-cn.com/problems/word-search-ii/)**困难**

给定一个二维网格 **board** 和一个字典中的单词列表 **words**，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

```java
/**
输入: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

输出: ["eat","oath"]

执行用时 : 20 ms, 在Word Search II的Java提交中击败了96.94% 的用户 内存消耗 : 45.8 MB, 在Word Search II的Java提交中击败了90.88% 的用户

思路：先将words数组中所有待查找的字符串组织成一颗字符串前缀树，之后在DFS的过程中使用这棵前缀树帮助筛选。
**/
```

