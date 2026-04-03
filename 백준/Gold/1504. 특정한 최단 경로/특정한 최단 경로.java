import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int idx;
        long weight;
        int v1;
        int v2;

        Node(int i, long w){
            this.idx = i;
            this.weight = w;
            this.v1 = 0;
            this.v2 = 0;
        }

        Node(int i, long w, int v1, int v2){
            this.idx = i;
            this.weight = w;
            this.v1 = v1;
            this.v2 = v2;
        }

        public int compareTo(Node o){
            return (int)(this.weight - o.weight);
        }
    }

    static long MAX = Long.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        ArrayList<Node>[] graph = new ArrayList[N];
        for(int i=0; i<N; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            long c = Long.parseLong(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken())-1;
        int v2 = Integer.parseInt(st.nextToken())-1;

        boolean[][][] visited = new boolean[N][2][2];
        long[][][] minDist = new long[N][2][2];
        for(int i=0; i<N; i++){
            for(int j=0; j<2; j++){
                for(int k=0; k<2; k++){
                    minDist[i][j][k] = MAX;
                }
            }
        }
        minDist[0][v1==0? 1:0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, v1==0? 1:0, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(visited[cur.idx][cur.v1][cur.v2]) continue;
            visited[cur.idx][cur.v1][cur.v2] = true;

            for(Node next : graph[cur.idx]){
                long tmp = cur.weight + next.weight;
                int tv1 = 0; int tv2 = 0;
                if(cur.v1==1 || next.idx==v1) tv1=1;
                if(cur.v2==1 || next.idx==v2) tv2=1;
                if(tmp < minDist[next.idx][tv1][tv2]){
                    minDist[next.idx][tv1][tv2] = tmp;
                    pq.add(new Node(next.idx, tmp, tv1, tv2));
                }
            }
        }

        System.out.println(minDist[N-1][1][1]!=MAX? minDist[N-1][1][1] : -1);
    }
}
