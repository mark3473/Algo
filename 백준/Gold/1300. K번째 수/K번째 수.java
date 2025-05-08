import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 배열 한변의 크기. 즉 N*N이 최대
        int K = Integer.parseInt(br.readLine()); // 찾을 B[K]번째 수 인덱스

        int left = 1;
        int right = K;
        while(left<right){
            int mid = (left+right)/2;
            int sum=0;
            for(int i=1; i<=N; i++){
                int cnt = Math.min(mid/i, N);
                if(cnt==0) break;
                sum+=cnt;
            }

            if(sum>=K){
                right = mid;
            } else{
                left = mid+1;
            }
        }
        System.out.println(right);
    }
}
