import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int idx;
        int time;

        Node(int i, int d){
            this.idx = i;
            this.time = d;
        }

        public int compareTo(Node o){
            return this.time-o.time;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int M = Integer.parseInt(st.nextToken()); // 도로 수
        int X = Integer.parseInt(st.nextToken()); // 파티 장소

        ArrayList<Node>[] graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, t));
        }

        // 모든 지점에서 X 까지의 최단 거리를 구하고 X에서 모든 지점까지의 최단 거리를 구하기
        int[] go = new int[N+1];
        for(int start=1; start<=N; start++){
            if(start==X) continue;

            boolean[] visited = new boolean[N+1];
            PriorityQueue<Node> pq = new PriorityQueue<>();
            int[] minDist = new int[N+1];
            Arrays.fill(minDist, Integer.MAX_VALUE);
            minDist[start] = 0;
            pq.add(new Node(start, 0));
            while(!pq.isEmpty()){
                Node cur = pq.poll();
                if(cur.idx == X) break;
                if(visited[cur.idx]) continue;
                visited[cur.idx] = true;

                for(Node next : graph[cur.idx]){
                    int dist = next.time + cur.time;
                    if(dist<minDist[next.idx]){
                        minDist[next.idx] = dist;
                        pq.add(new Node(next.idx, dist));
                    }
                }
            }

            go[start] = minDist[X];
        }

        int[] come = new int[N+1];
        Arrays.fill(come, Integer.MAX_VALUE);
        come[X] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.idx]){
                if(come[next.idx] > come[cur.idx]+next.time){
                    come[next.idx] = come[cur.idx] + next.time;
                    pq.add(new Node(next.idx, come[next.idx]));
                }
            }
        }

        int ans = 0;
        for(int i=1; i<=N; i++){
            ans = Math.max(ans, go[i]+come[i]);
        }

        System.out.println(ans);
    }
}
