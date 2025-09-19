import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[] liq = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            liq[i] = Long.parseLong(st.nextToken());
        }

        int left = 0;
        int right = N-1;
        long tmp = 2000000000;
        long ans1=0;
        long ans2=0;
        while(left < right){
            long sum = liq[left] + liq[right];
            if(Math.abs(tmp) > Math.abs(sum)){
                ans1 = liq[left];
                ans2 = liq[right];
                tmp = sum;
            }

            if(sum>0){ // 합이 너무 크면
                right--; // 덜 큰 것을 더해야함
            }
            else if(sum<0){ // 합이 너무 작으면
                left++; // 더 큰 것을 더해야함
            }
            else break;
        }

        System.out.println(ans1 + " " + ans2);
    }
}
