import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선 개수
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선 개수

        long[] lines = new long[K];
        for(int i=0; i<K; i++){
            lines[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(lines);

        System.out.println(check(lines, lines[K-1], N)-1);
    }

    static long check(long[] lines, long n, int N){
        long left = 0;
        long right = n+1; // max값 + 1
        while(left < right){
            long mid = left + (right - left) / 2;
            long count = 0;
            for(long line : lines){
                count+=(line/mid);
            }
            if(count < N){ // 이렇게 했는데도 N개만큼이 안된다면 더 작게 나눠야한다는것
                right = mid;
            } else{ // N개 이상이면 더 크게 시도해봐도 된다는 것.
                left = mid+1;
            }
        }

        return left;
    }
}
