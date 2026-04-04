import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 작업 수
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        int[] inD = new int[N+1];
        int[] time = new int[N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
            for(int j=Integer.parseInt(st.nextToken()); j>0; j--){
                int from = Integer.parseInt(st.nextToken());

                graph[from].add(i);
                inD[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int[] ans = new int[N+1];
        for(int i=1; i<=N; i++){
            if(inD[i]==0) {
                ans[i] = time[i];
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next : graph[cur]){
                inD[next]--;
                ans[next] = Math.max(ans[next], ans[cur]+time[next]);
                if(inD[next]==0){
                    q.add(next);
                }
            }
        }

        int tmp = 0;
        for(int i : ans){
            tmp = Math.max(tmp, i);
        }
        System.out.println(tmp);
    }
}
