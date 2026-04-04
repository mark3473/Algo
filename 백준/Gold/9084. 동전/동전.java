import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T= Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; t++){

            int N = Integer.parseInt(br.readLine());
            int[] values = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                values[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine()); // 목표 금액

            int[] dp = new int[M+1];
            dp[0] = 1;

            for(int i=0; i<N; i++){
                for(int j=0; j<=M; j++){
                    if(values[i]<=j) dp[j] += dp[j-values[i]];
                }
            }

            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);
    }
}
