import java.io.*;
import java.util.*;

public class Main {
    static class History{
        int time;
        int idx;

        History(int t, int c) {
            this.time = t;
            this.idx = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N==K) {
            System.out.println(0);
            return;
        }

        if(K==0) {
            System.out.println(N);
            return;
        }

        Set<Integer> sub = new HashSet<>();

        int tmp = 0;

        if(K%2==0){
            for(int k=K; k%2==0; k/=2){
                if(k==N) {
                    System.out.println(0);
                    return;
                }
                sub.add(k);
            }
        }

        else{
            tmp=1;
            for(int k=K+1; k%2==0; k/=2){
                if(k==N) {
                    System.out.println(1);
                    return;
                }
                sub.add(k);
            }

            for(int k=K-1; k%2==0; k/=2){
                if(k==N) {
                    System.out.println(1);
                    return;
                }
                if(sub.contains(k)) break;
                sub.add(k);
            }
        }

        PriorityQueue<History> pq = new PriorityQueue<>((o1, o2)-> o1.time-o2.time);
        pq.add(new History(0, N));

        boolean[] visited = new boolean[100001];

        while(!pq.isEmpty()){
            History cur = pq.poll();
            int time = cur.time;
            int idx = cur.idx;

            if(sub.contains(idx)) {
                System.out.println(time+tmp);
                return;
            }

            visited[idx] = true;

            if(idx*2<=100000 && !visited[idx*2]){
                pq.add(new History(time, idx*2));
            }

            if(idx>0 && !visited[idx-1]) {
                pq.add(new History(time+1, idx-1));
            }

            if(idx+1<=100000 && !visited[idx+1]){
                pq.add(new History(time+1, idx+1));
            }
        }
    }
}
