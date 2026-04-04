import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물 수
            int K = Integer.parseInt(st.nextToken()); // 순서 수

            st = new StringTokenizer(br.readLine());
            int[] buildTime = new int[N+1];
            for(int i=1; i<=N; i++) buildTime[i] = Integer.parseInt(st.nextToken());

            ArrayList<Integer>[] graph = new ArrayList[N+1];
            for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();

            Queue<Integer> q = new LinkedList<>();
            int[] inD = new int[N+1];
            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                graph[X].add(Y);
                inD[Y]++;
            }

            int dest = Integer.parseInt(br.readLine()); // 최종건물

            for(int i=1; i<=N; i++){
                if(inD[i]==0) q.add(i);
            }

            int[] dp = new int[N+1]; // i번을 짓기 전까지 걸리는 시간
            while(!q.isEmpty()){
                int cur = q.poll();
                if(cur==dest){
                    sb.append(dp[cur]+buildTime[cur]).append("\n");
                    break;
                }

                for(int next : graph[cur]){
                    inD[next]--;
                    dp[next] = Math.max(dp[next], dp[cur]+buildTime[cur]);
                    if(inD[next]==0){
                        q.add(next);
                    }
                }
            }
        }

        System.out.println(sb);
    }
}
