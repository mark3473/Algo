import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 심사대 수
        int M = Integer.parseInt(st.nextToken()); // 사람 수
        long[] table = new long[N];
        long left = 0;
        long right = 0;
        for(int i=0; i<N; i++){
            table[i] = Long.parseLong(br.readLine());
            right = Math.max(right, table[i]);
        }
        right*=M;
        long ans = Long.MAX_VALUE;
        while(left<=right){
            long mid = (left+right)/2;

            long cnt = 0;
            for(long i : table){
                cnt+=(mid/i);
                if(cnt>=M){
                    break;
                }
            }
            if(cnt>=M){
                right = mid-1;
                ans = Math.min(ans, mid);
            } else{
                left = mid+1;
            }
        }

        System.out.println(ans);
    }
}
