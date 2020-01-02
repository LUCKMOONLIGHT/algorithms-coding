package _19.Designer;

/**
 * 设计一个数据结构，这个数据可以添加单词，还有可以查找输入单词是否存在，
 * 但是这里查找的输入单词中可以含有特殊字符 ‘.’，’.’ 可以表示任意字符。
 * 字典树其余部分还是不变，主要是查找部分需要做些调整，
 * 这里写了一个递归深度优先搜索的方式去查找，和二叉树遍历的方式类似，只不过这里变成了多叉树。
 * 另外就是遇到当前字符是 ‘.’ 时，需要把当前节点的所有存在的子节点都考虑一遍
 */
class WordDictionary {

    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
        char c;

        TrieNode() {}

        TrieNode(char c) {
            this.c = c;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        char[] wordArr = word.toCharArray();

        TrieNode pointer = root;
        for (int i = 0; i < wordArr.length; ++i) {
            if (pointer.children[wordArr[i] - 'a'] == null) {
                pointer.children[wordArr[i] - 'a'] = new TrieNode(wordArr[i]);//添加结点
            }

            pointer = pointer.children[wordArr[i] - 'a'];//指针指向该结点
        }

        pointer.isWord = true;//字符串结束标志
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(0, word.toCharArray(), root);
    }

    private boolean helper(int index, char[] word, TrieNode root) {
        if (index == word.length) {
            return root.isWord;
        }

        char curC = word[index];

        if (curC != '.') {
            if (root.children[curC - 'a'] != null) {
                return helper(index + 1, word, root.children[curC - 'a']);
            } else {
                return false;
            }
        }

        if (curC == '.') { //循环遍历某个子节点不为空，递归
            for (int i = 0; i < root.children.length; ++i) {
                if (root.children[i] != null && helper(index + 1, word, root.children[i])) {
                    return true;
                }
            }
        }

        return false;
    }
}
