import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 문제 수
        int M = Integer.parseInt(st.nextToken()); // 정보 수

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
        int[] inD = new int[N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            inD[B]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            if(inD[i]==0) pq.add(i);
        }

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for(int next : graph[cur]){
                inD[next]--;
                if(inD[next]==0) pq.add(next);
            }
        }

        System.out.println(sb);
    }
}
