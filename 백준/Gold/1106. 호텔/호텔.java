import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken()); // 목표치
        int N = Integer.parseInt(st.nextToken()); // 도시 수

        int[] cost = new int[N];
        int[] value = new int[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[C+101]; // dp[i] = i명을 늘리기 위한 최소 비용
        Arrays.fill(dp, 100000000);
        dp[0] = 0;

        for(int i=0; i<N; i++){
            for(int j=value[i]; j<=C+100; j++){
                // 이번 도시에서 홍보를 할때 vs 홍보를 안할 때
                dp[j] = Math.min(dp[j-value[i]]+cost[i], dp[j]);
            }
        }

        int ans = 100000000;
        for(int i=C; i<=C+100; i++){
            ans = Math.min(ans, dp[i]);
        }

        System.out.println(ans);
    }
}
