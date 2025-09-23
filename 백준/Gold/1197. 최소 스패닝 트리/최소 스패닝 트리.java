import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int V, E;

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        int w;

        Edge(int a, int b, int c){
            this.v1 = a;
            this.v2 = b;
            this.w = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w-o.w;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 정점의 개수
        E = Integer.parseInt(st.nextToken()); // 간선의 개수

        parent = new int[V];
        for(int i=0; i<V; i++){
            parent[i] = i;
        }
        PriorityQueue<Edge> graph = new PriorityQueue<>();

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken())-1; // 정점
            int B = Integer.parseInt(st.nextToken())-1; // 정점
            int C = Integer.parseInt(st.nextToken()); // 가중치

            graph.add(new Edge(A,B,C));
        }

        int sum = 0;
        int cnt = 0;

        while(cnt<V-1){
            Edge e = graph.poll();

            // 연결하면 사이클이 형성되는지 확인
            if(union(e.v1, e.v2)){
                sum+=e.w;
                cnt++;
            }
        }

        System.out.println(sum);
    }

    static boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(pA==pB) return false;
        else {
            parent[pA] = pB;
            return true;
        }
    }

    static int find(int a){
        if(a==parent[a]){
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}
