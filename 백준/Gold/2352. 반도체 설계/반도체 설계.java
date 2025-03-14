import java.io.*;
import java.util.*;

// 방법 2.
// 포트를 순서대로 확인하면서 지금까지 제일 큰값이면 dp에 넣고 아니면 해당 값을 이분탐색으로 넣을 위치 찾기

public class Main {
    static int N;
    static int[] ports;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 포트 수
        ports = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            ports[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];
        int len = 0;
        for(int i=0; i<N; i++){
            if(ports[i]>dp[len]){
                dp[++len] = ports[i];
            } else{
                int idx = binarySearch(dp, 0, len, ports[i]);
                dp[idx] = ports[i];
            }
        }

        System.out.println(len);
    }

    static int binarySearch(int[] dp, int left, int right, int val){
        while(left<right){
            int mid = (left+right)/2;

            if(dp[mid]<val){
                left = mid+1;
            } else{
                right = mid;
            }
        }
        return right;
    }
}
