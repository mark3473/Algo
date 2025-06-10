import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수빈 위치
        int K = Integer.parseInt(st.nextToken()); // 동생 위치

        if(N>=K){
            System.out.println((N-K)+"\n"+1);
            return;
        }

        int[] time = new int[100001];
        int minTime = Integer.MAX_VALUE;
        int cnt = 0;
        time[N] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        while(!q.isEmpty()){
            int cur = q.poll(); // 현재 위치

            if(time[cur] > minTime) break;

            // +1
            int next = cur+1;
            if(0<=next && next<=100000){
                if(next==K){
                    minTime = time[cur];
                    cnt += 1;
                }

                if(time[next]==0 || time[next]==time[cur]+1){
                    time[next] = time[cur]+1;
                    q.add(next);
                }
            }

            // -1
            next = cur-1;
            if(0<=next && next<=100000){
                if(next==K){
                    minTime = time[cur];
                    cnt += 1;
                }

                if(time[next]==0 || time[next]==time[cur]+1){
                    time[next] = time[cur]+1;
                    q.add(next);
                }
            }

            // *2
            next = cur*2;
            if(0<=next && next<=100000){
                if(next==K){
                    minTime = time[cur];
                    cnt += 1;
                }

                if(time[next]==0 || time[next]==time[cur]+1){
                    time[next] = time[cur]+1;
                    q.add(next);
                }
            }
        }

        System.out.println(minTime+"\n"+cnt);
    }
}