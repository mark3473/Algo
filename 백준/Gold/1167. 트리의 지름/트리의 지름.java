import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int idx;
        int dist;

        Node(int i, int t){
            this.idx = i;
            this.dist = t;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine()); // 정점의 개수

        List<Node>[] graph = new List[V];
        for(int i=0; i<V; i++) graph[i] = new ArrayList<>();

        StringTokenizer st;
        int v1 = 0;
        for(int i=0; i<V; i++){
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken())-1;

            while(st.hasMoreTokens()){
                int v2 = Integer.parseInt(st.nextToken())-1;
                if(v2==-2) break;
                int d = Integer.parseInt(st.nextToken());

                graph[v1].add(new Node(v2, d));
                graph[v2].add(new Node(v1, d));
            }
        }

        // 우선 v1에서 가장 먼 곳 찾기 = 트리 지름의 한 쪽 끝
        Queue<int[]> q = new LinkedList<>();
        boolean[] v = new boolean[V];
        q.add(new int[] {v1, 0});
        v[v1] = true;
        int farNode = v1;
        int farDist = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(Node next : graph[cur[0]]){
                if(!v[next.idx]){
                    v[next.idx]=true;
                    if(cur[1]+next.dist > farDist){
                        farDist = cur[1]+next.dist;
                        farNode = next.idx;
                    }
                    q.add(new int[] {next.idx, cur[1]+next.dist});
                }
            }
        }

        // 한쪽 끝에서 다른 지름의 끝 찾기
        q = new LinkedList<>();
        v = new boolean[V];
        q.add(new int[] {farNode, 0});
        v[farNode] = true;
        farDist = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(Node next : graph[cur[0]]){
                if(!v[next.idx]){
                    v[next.idx]=true;
                    if(cur[1]+next.dist > farDist){
                        farDist = cur[1]+next.dist;
                    }
                    q.add(new int[] {next.idx, cur[1]+next.dist});
                }
            }
        }

        // 최종 지름 구하기
        System.out.println(farDist);
    }
}