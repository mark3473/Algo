import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 나눌 값

        int[] values = new int[M];
        st = new StringTokenizer(br.readLine());

        int sum=0;
        for(int i=1; i<=N; i++){
            sum = (sum + Integer.parseInt(st.nextToken()))%M;
            values[sum]++;
        }
        // values[0] == 하나로도 나눠 떨어지는 구간
        long ans = values[0];
        for(int i=0; i<M; i++){
            ans+=((long)values[i] * (values[i]-1))/2;
        }

        System.out.println(ans);
    }
}
