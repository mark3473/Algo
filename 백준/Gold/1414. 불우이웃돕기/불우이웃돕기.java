import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int from;
        int to;
        int val;

        Node(int f, int t, int v){
            this.from = f;
            this.to = t;
            this.val = v;
        }
    }
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.val-o2.val);
        int total = 0;
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<N; j++){
                graph[i][j] = change(line.charAt(j));
                if(graph[i][j]==0) continue;
                total+=graph[i][j];
                pq.add(new Node(i,j,graph[i][j]));
            }
        }

        int sum = 0;
        int connected = 1;
        parent = new int[N];
        for(int i=0; i<N; i++) parent[i] = i;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int pTo = find(cur.to);
            int pFrom = find(cur.from);

            // 부모가 같지 않으면 == 연결되어있지 않으면
            if(pTo != pFrom){
                sum+=cur.val;
                union(pTo, pFrom);
                connected++;
            }
        }

        if(connected!=N) System.out.println(-1);
        else System.out.println(total-sum);
    }

    static int change(char c){
        if(c=='0') return 0;
        else if('a'<=c && c<='z'){
            return c-'a'+1;
        }
        else{
            return c-'A'+27;
        }
    }

    static int find(int n){
        if(parent[n]==n) return n;
        else return parent[n] = find(parent[n]);
    }

    static void union(int a, int b){
        int pA = find(a);
        int pB = find(b);

        if(a<b) parent[pB] = pA;
        else parent[pA] = pB;
    }
}
