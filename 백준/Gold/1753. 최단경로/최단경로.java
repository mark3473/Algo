import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int idx;
        int weight;

        Node(int i, int w){
            this.idx = i;
            this.weight = w;
        }
    }

    static int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        int K = Integer.parseInt(br.readLine())-1; // 시작점 번호

        ArrayList<Node>[] graph = new ArrayList[V];
        for(int i=0; i<V; i++) graph[i] = new ArrayList<>();

        for(int e=0; e<E; e++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        int[] minDist = new int[V];
        Arrays.fill(minDist, MAX);
        minDist[K] = 0;

        boolean[] visited = new boolean[V];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.weight-o2.weight);
        pq.add(new Node(K, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll(); // 시작점에서 cur.idx까지 연결됐을 때 사용된 경로값 = cur.weight
            if(visited[cur.idx]) continue;
            visited[cur.idx] = true;

            for(Node next : graph[cur.idx]){
                if(minDist[next.idx] > minDist[cur.idx] + next.weight){
                    minDist[next.idx] = minDist[cur.idx]+next.weight;
                    pq.add(new Node(next.idx, minDist[next.idx]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int d : minDist){
            if(d==MAX) sb.append("INF");
            else sb.append(d);

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
