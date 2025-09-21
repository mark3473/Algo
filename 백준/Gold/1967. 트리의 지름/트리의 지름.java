import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
    static class Node{
        int idx;
        int weight;

        Node(int t, int w){
            this.idx = t;
            this.weight = w;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 노드의 개수
        boolean[] visited = new boolean[N];
        List<Node>[] graph = new ArrayList[N];
        for(int i=0; i<N; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            int value = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, value));
            graph[to].add(new Node(from, value));
        }

        // 우선 루트 노드에서 가장 먼 노드 찾기 ( 트리의 지름의 한쪽 끝 찾기 )
        int farNode = 0;
        int farDist = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0});
        visited[0] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[1]>farDist){
                farDist = cur[1];
                farNode = cur[0];
            }

            for(Node next : graph[cur[0]]){
                if(visited[next.idx]) continue;
                q.add(new int[] {next.idx, cur[1]+next.weight});
                visited[next.idx] = true;
            }
        }

        // 트리 지름의 한쪽 끝에서 다시 가장 먼 곳 찾기
        visited = new boolean[N];
        q.add(new int[] {farNode, 0});
        visited[farNode] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[1]>farDist){
                farDist = cur[1];
            }

            for(Node next : graph[cur[0]]){
                if(visited[next.idx]) continue;
                q.add(new int[] {next.idx, cur[1]+next.weight});
                visited[next.idx] = true;
            }
        }

        System.out.println(farDist);
    }

}