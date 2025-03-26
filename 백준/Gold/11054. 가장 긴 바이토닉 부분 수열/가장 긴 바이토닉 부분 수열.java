import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 수열의 길이
        int[] nums = new int[N];
        int[][] dp = new int[2][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            dp[0][i] = 1;
            for(int j=0; j<i; j++){
                if(nums[i]>nums[j] && dp[0][i] < dp[0][j]+1){
                    dp[0][i] = dp[0][j]+1;
                }
            }
        }
        for(int i=N-1; i>=0; i--){
            dp[1][i] = 1;
            for(int j=N-1; j>i; j--){
                if(nums[i] > nums[j] && dp[1][i] < dp[1][j]+1){
                    dp[1][i] = dp[1][j]+1;
                }
            }
        }

        int ans = 0;
        for(int i=0; i<N; i++){
            ans = Math.max(dp[0][i]+dp[1][i], ans);
        }

        System.out.println(ans-1);
    }
}
