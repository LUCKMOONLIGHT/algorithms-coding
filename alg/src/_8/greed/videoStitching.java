package _8.greed;

/**
 * 1024. 视频拼接 - 贪心算法
 * 从clip中选出n个片段，使得合并后覆盖T秒
 *
 * clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 *  [0,2] + [2,8] + [8,10]
 */
public class videoStitching {
    /**
     * 用dp[i]代表从0到时刻i最小片段个数；记录时刻 i-1 能表示的最大时刻maxRight
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching(int[][] clips, int T) {
        int maxRight = -1;
        int result = 0;
        for(int i = 1; i<= T; i++){ //遍历1-T秒长度
            boolean found = false;
            if(maxRight>= i){ //当maxRight大于当前长度i时，直接返回，从maxRight后一个位置开始
                found = true;
            }
            else{
                for(int j = 0; j<clips.length; j ++){ //迭代所有的片段
                    if(i-1>= clips[j][0] && i<= clips[j][1]){//当前段包含[i-1 i]秒中求最大的maxRight
                        found = true;
                        maxRight = Math.max(maxRight, clips[j][1]);
                    }
                }
                if(found){
                    result++;
                }
                else{
                    result= -1;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][]  arg = new int[][]{{0,2},{4,6},{8,10},{1,9},{1,5},{5,9},};
        videoStitching videoStitching = new videoStitching();
        int res = videoStitching.videoStitching(arg, 10);
        System.out.println(res);
    }
}
