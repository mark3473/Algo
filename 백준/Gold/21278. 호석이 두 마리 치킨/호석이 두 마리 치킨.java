import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int N, M;
    static int[] ans = {0, 0, Integer.MAX_VALUE};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for(int i=0; i<N; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken())-1;
            int B = Integer.parseInt(st.nextToken())-1;

            graph[A].add(B);
            graph[B].add(A);
        }

        for(int i=0; i<N-1; i++){
            for(int j=i+1; j<N; j++){
                search(i,j);
            }
        }

        System.out.println(ans[0]+" "+ans[1]+" "+ans[2]*2);
    }

    static void search(int a, int b){
        int sum=0;
        for(int i=0; i<N; i++){ // 모든 도시에 대하여 탐색
            if(i==a || i==b) continue; // 만약 치킨집이 있는 도시면 그냥 pass

            Queue<Node> q = new LinkedList<>();
            q.add(new Node(i,0));
            boolean[] v = new boolean[N];
            v[i] = true;
            while(!q.isEmpty()){
                Node cur = q.poll();

                // cur에서 갈 수 있는 도시에 대하여 탐색
                for(int next : graph[cur.num]){
                    if(v[next]) continue;
                    if(next==a || next==b){ // next가 치킨집 도시면
                        sum+=cur.val+1; // sum에 거리 더하기
                        q.clear(); // q 비우고 끝 :: 그 도시에 대하여 가까운 치킨집 탐색 끝
                        break;
                    }
                    v[next] = true;
                    q.add(new Node(next, cur.val+1));
                }
            }
        }

        if(ans[2]>sum){
            ans[0] = a+1;
            ans[1] = b+1;
            ans[2] = sum;
        }
    }

    static class Node{
        int num;
        int val;

        Node(int n, int v){
            this.num = n;
            this.val = v;
        }
    }
}
