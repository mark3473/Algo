import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] graph = new int[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i=1; i<=N; i++){
            boolean[] visited = new boolean[N+1];
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            while(!q.isEmpty()){
                int cur = q.poll();
                if(visited[cur]) continue;
                visited[cur] = true;
                if(graph[cur]==i) {
                    count++;
                    sb.append(i).append("\n");
                }
                else q.add(graph[cur]);
            }
        }
        System.out.println(count);
        System.out.println(sb);
    }
}
