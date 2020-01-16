package _15.series.UnionFindSet;


//1061. 按字典序排列最小的等效字符串
//根据A,B字符串得到的等价信息，求字符串S 的按字典序最小的等价字符串
//A = "parker", B = "morris", S = "parser"  "makkek"  [m,p], [a,o], [k,r,s], [e,i] 共 4 组
public class smallestEquivalentString {
    //思路:并查集思路，将A,B的并查集组迭代更新，然后迭代S，求S的每个字符串所属于的组，更新最小等价字符串
    int[] parent = new int[256];
    int find(int i){
        if(i == parent[i]) return i;
        else return find(parent[i]);
    }
    public String smallestEquivalentString(String A, String B, String S) {
        for(int i=0;i<parent.length;i++) parent[i] = i;
        for(int i=0;i<B.length();i++){
            char a = A.charAt(i);
            char b = B.charAt(i);

            int pa = find(a);
            int pb = find(b);
            parent[Math.max(pa, pb)] = Math.min(pa, pb);
        }
        char[] res = new char[S.length()];
        for(int i=0;i<S.length();i++) res[i] = (char)find(S.charAt(i));
        return new String(res);
    }
}
