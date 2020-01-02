package _19.Designer;

public class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWordOrNot = false;

    private TrieNode root = new TrieNode();

    /**
     * 插入和查找字符时间复杂度 O(L)
     *
     * 1.确认一个单词是否在字典中存在
     * 2.求得字典中含有某前缀的所有单词
     * 3.计算字典中含有某前缀的单词的个数
     * 4.计算字典中含有某前缀的单词的出现频率
     * @param word
     */
    public void addWord(String word){
        TrieNode pointer = root;
        for (char w:word.toCharArray()){
            if (pointer.children[w - 'a'] == null){
                pointer.children[w - 'a'] = new TrieNode();
            }
            pointer = pointer.children[w - 'a'];
        }
        pointer.isWordOrNot = true;
    }
}
