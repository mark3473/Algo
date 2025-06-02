import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int root = 0;
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i=0; i<N; i++) graph[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int to=0; to<N; to++){
            int from = Integer.parseInt(st.nextToken());

            if(from==-1) root = to;
            else {
                graph[from].add(to);
            }
        }

        int x = Integer.parseInt(br.readLine());
        if(x==root) {
            System.out.println(0);
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        int count = 0;
        while(!q.isEmpty()){
            int from = q.poll();

            if(graph[from].isEmpty()) count++;
            else if(graph[from].size()==1 && graph[from].get(0)==x) count++;

            for(int to : graph[from]){
                if(to==x) continue;
                q.add(to);
            }
        }

        System.out.println(count);
    }
}
