import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] graph = new int[n][n];
        int[][] ans = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                graph[i][j] = Integer.MAX_VALUE;
                if(i!=j) {
                    ans[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int cost = Integer.parseInt(st.nextToken());

            graph[from][to] = Math.min(graph[from][to], cost);
        }

        for(int i=0; i<n; i++){ // i번 도로부터 모든 도로까지의 최소 거리 비용 구하기
            Queue<Integer> q = new LinkedList<>(); // 현 위치에서 갈 수 있는 곳
            q.add(i);
            boolean[][] v = new boolean[n][n];
            v[i][i] = true;
            while(!q.isEmpty()){
                int cur = q.poll();
                for(int dest=0; dest<n; dest++){
                    if(graph[cur][dest]==Integer.MAX_VALUE) continue;
                    if(ans[i][dest] > ans[i][cur]+graph[cur][dest]){
                        ans[i][dest] = ans[i][cur]+graph[cur][dest];
                        q.add(dest);
                    }
                }
            }

        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(ans[i][j]==Integer.MAX_VALUE) sb.append("0");
                else sb.append(ans[i][j]);

                sb.append(" ");
            } sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
