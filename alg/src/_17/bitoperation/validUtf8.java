package _17.bitoperation;

//393. UTF-8 编码验证

//思路：

/**
 * 1.如果cnt为0，记录当前字节右边连续为1的个数，如果cnt为0，continue，如果1=cnt>4 false
 * 2.如果cnt不为0，记录当前位字节首位是不是10，cnt--
 * 3.判断cnt == 0
 */
public class validUtf8 {
    public boolean validUtf8(int[] data) {
        int cnt = 0;
        int mask1 = 1 << 7;
        int mask2 = 1 << 6;

        for(int i=0;i<data.length;i++){
            if(cnt == 0){
                int mask = 1 << 7;
                while((mask & data[i]) != 0){
                    cnt += 1;
                    mask = mask >> 1;
                }
                if(cnt == 0) continue;
                if(cnt > 4 || cnt == 1) return false;
            }else {
                if(!((mask1 & data[i]) != 0 && (mask2 & data[i]) == 0)) return false;
            }
            cnt -= 1;
        }
        return cnt == 0;
    }
}
