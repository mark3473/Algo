import java.io.*;
import java.util.*;

public class Main {
    static int[] graph;
    static boolean[] visited;
    static boolean[] finished;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for(int t=0; t<T; t++){
            int n = Integer.parseInt(br.readLine()); // 학생 수

            cnt = 0;
            graph = new int[n];
            visited = new boolean[n];
            finished = new boolean[n];
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<n; i++){
                graph[i] = Integer.parseInt(st.nextToken())-1;
            }

            for(int i=0; i<n; i++){
                if(!finished[i]) dfs(i);
            }

            sb.append(n-cnt).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int cur){
        visited[cur] = true;
        int next = graph[cur];

        if(!visited[next]) dfs(next);
        else if(!finished[next]) {
            cnt++;
            for(int i=next; i!=cur; i=graph[i]){
                cnt++;
            }
        }

        finished[cur] = true;
    }
}
