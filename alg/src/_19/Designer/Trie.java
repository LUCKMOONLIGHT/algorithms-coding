package _19.Designer;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 */
class Trie {
    /** Initialize your data structure here. */
    private final int ALPHABET_SIZE = 26;

    private class TrieNode {
        private TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        private boolean isWordOrNot = false;
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode pointer = root;

        for (char c : word.toCharArray()) {
            if (pointer.children[c - 'a'] == null) {
                pointer.children[c - 'a'] = new TrieNode();
            }

            pointer = pointer.children[c - 'a'];
        }

        pointer.isWordOrNot = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode pointer = this.root;

        for (char c : word.toCharArray()) {
            if (pointer.children[c - 'a'] == null) {
                return false;
            }

            pointer = pointer.children[c - 'a'];
        }

        return pointer.isWordOrNot;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode pointer = root;

        for (char c : prefix.toCharArray()) {
            if (pointer.children[c - 'a'] == null) {
                return false;
            }

            pointer = pointer.children[c - 'a'];
        }

        return true;
    }
}
