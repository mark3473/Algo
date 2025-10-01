import java.io.*;
import java.util.*;

public class Main {
   static int N, P;
   static int[] parent;
   static int[][] cap, flow;
   static List<Integer>[] graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점 수
        P = Integer.parseInt(st.nextToken()); // 간선 수

        // i번 in : 2*i, i번 out : 2*i+1
        cap = new int[2*N][2*N];
        flow = new int[2*N][2*N];
        graph = new List[2*N];
        for(int i=0; i<2*N; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<P; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            graph[2*a].add(2*a+1);
            graph[2*a+1].add(2*a);
            graph[2*a+1].add(2*b);
            graph[2*b].add(2*a+1);
            graph[2*b].add(2*b+1);
            graph[2*b+1].add(2*b);
            graph[2*b+1].add(2*a);
            graph[2*a].add(2*b+1);

            cap[2*a][2*a+1] = 1;
            cap[2*a+1][2*b]++;
            cap[2*b][2*b+1] = 1;
            cap[2*b+1][2*a]++;
        }

        System.out.println(maxFlow(1, 2));
    }

    static int maxFlow(int start, int end){
        int totalFlow = 0;
        parent = new int[2*N];
        while(true){
            Arrays.fill(parent, -1);
            parent[start] = start;
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            while(!q.isEmpty() && parent[end]==-1){
                int cur = q.poll();
                for(int next : graph[cur]){
                    if(parent[next]==-1 && cap[cur][next]-flow[cur][next]>0){
                        q.add(next);
                        parent[next] = cur;
                    }
                }
            }

            if(parent[end]==-1) break;

            int amount = Integer.MAX_VALUE;
            for(int i=end; i!=start; i=parent[i]){
                amount = Math.min(amount, cap[parent[i]][i]-flow[parent[i]][i]);
            }

            for(int i=end; i!=start; i=parent[i]){
                flow[parent[i]][i] += amount;
                flow[i][parent[i]] -= amount;
            }

            totalFlow += amount;
        }
        return totalFlow;
    }
}