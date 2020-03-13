package _0.array;


import java.util.ArrayList;
import java.util.List;

//118. 杨辉三角
//每个数根据其前一行来构造
//预置一些已知数1，便于书写
public class generate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows == 0) return res;
        res.add(new ArrayList<>());
        res.get(0).add(1);//预置1
        for(int i=1;i<numRows;i++){//行数
            List<Integer> cur = new ArrayList<>();
            List<Integer> pre = res.get(i-1);//上一行
            cur.add(1);//预置1
            for(int j=1;j<i;j++){//每行的和数
                cur.add(pre.get(j-1)+pre.get(j));
            }
            cur.add(1);//预置1
            res.add(cur);
        }
        return res;
    }
}
