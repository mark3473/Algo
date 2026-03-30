import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int idx;
        int cow;

        Node(int i, int c){
            this.idx = i;
            this.cow = c;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 헛간
        int M = Integer.parseInt(st.nextToken()); // 길

        ArrayList<Node>[] graph = new ArrayList[N];
        for(int i=0; i<N; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        int[] minD = new int[N];
        for(int i=1; i<N; i++) minD[i] = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.cow-o2.cow);
        pq.add(new Node(0, 0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node next : graph[cur.idx]){
                if(minD[cur.idx]+next.cow < minD[next.idx]){
                    minD[next.idx] = minD[cur.idx]+next.cow;
                    pq.add(new Node(next.idx, minD[next.idx]));
                }
            }
        }

        System.out.println(minD[N-1]);
    }
}
