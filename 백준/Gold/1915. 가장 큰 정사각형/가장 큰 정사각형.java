import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            String line = br.readLine();
            for(int j=1; j<=m; j++){
                map[i][j] = Integer.parseInt(line.charAt(j-1)+"");
            }
        }

        int[][] dp = new int[n+1][m+1];
        int max = 0;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(map[i][j]==1){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        System.out.println(max*max);
    }
}
