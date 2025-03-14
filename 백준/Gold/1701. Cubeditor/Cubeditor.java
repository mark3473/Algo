import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();
        int len = string.length();
        int[][] dp = new int[len+1][len+1];
        int ans = 0;
        for(int i=1; i<=len; i++){
            for(int j=i+1; j<=len; j++){
                if(string.charAt(i-1)==string.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        System.out.println(ans);
    }
}
