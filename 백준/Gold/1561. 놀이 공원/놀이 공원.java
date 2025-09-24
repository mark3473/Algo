import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Integer.parseInt(st.nextToken()); // 아이 수
        int M = Integer.parseInt(st.nextToken()); // 놀이기구 수
        if(N<=M) {
            System.out.println(N);
            return;
        }

        int[] times = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        long left = 1;
        long right = N * 30;
        while (left <= right) {
            long mid = (left + right) / 2;

            // mid 분까지 얼마나 많은 아이 태울 수 있는가
            long sum = 0;
            for (int t : times) {
                sum += (mid/t+1);
            }

            if(sum<N){
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }

        // right 분 = N명의 아이를 태울 수 없는 최대 시간
        // right 분까지 몇 명을 태울 수 있는지 세기
        long sum = 0;
        for(int t : times){
            sum += ((right)/t + 1);
        }
        // sum = 놀이기구 탑승한 아이의 수
        for(int i=0; i<M; i++){
            if((right+1)%times[i]==0) sum++;

            if(sum>=N) {
                System.out.println(i+1);
                break;
            }
        }
    }
}
