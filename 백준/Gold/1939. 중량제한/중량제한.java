import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int num;
        int limit;

        Node(int n, int l){
            this.num = n;
            this.limit = l;
        }
    }
    static PriorityQueue<Node>[] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 섬 개수
        int M = Integer.parseInt(st.nextToken()); // 다리 개수

        graph = new PriorityQueue[N];
        for(int i=0; i<N; i++) graph[i] = new PriorityQueue<Node>((o1, o2) -> o2.limit - o1.limit);

        int max = Integer.MIN_VALUE;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken())-1;
            int B = Integer.parseInt(st.nextToken())-1;
            int C = Integer.parseInt(st.nextToken()); // 다리 중량 제한
            max = Math.max(max, C);

            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }
        st = new StringTokenizer(br.readLine());
        // 공장이 세워진 두 섬
        int f1 = Integer.parseInt(st.nextToken())-1;
        int f2 = Integer.parseInt(st.nextToken())-1;

        int min = 0;
//        max++;
        while(min <= max){
            int mid = (min+max)/2;
//            if(min==mid) break;

            if(able(mid, f1, f2, N)){
                min = mid+1;
            } else{
                max = mid-1;
            }
        }

        System.out.println(max);
    }

    static boolean able(int value, int f1, int f2, int N){
        boolean[] v = new boolean[N];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(f1);
        v[f1] = true;
        while(!q.isEmpty()){
            int cur = q.poll();

            for(Node n : graph[cur]){
                if(n.limit >= value && !v[n.num]){
                    if(n.num == f2) return true;
                    else {
                        q.add(n.num);
                        v[n.num] = true;
                    }
                }
            }
        }
        return false;
    }
}
