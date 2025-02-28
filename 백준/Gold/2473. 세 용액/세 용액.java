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
        Arrays.sort(liq);

        int a1=0;
        int a2=0;
        int a3=0;
        long tmp = Long.MAX_VALUE;
        for(int p1=0; p1<N-2; p1++){
            int p2 = p1+1;
            int p3 = N-1;
            while(p2<p3){
                if(Math.abs(liq[p1]+liq[p2]+liq[p3])<tmp){
                    tmp = Math.abs(liq[p1]+liq[p2]+liq[p3]);
                    a1 = p1;
                    a2 = p2;
                    a3 = p3;
                }

                if(liq[p1]+liq[p2]+liq[p3] > 0){
                    p3--;
                }
                else if(liq[p1]+liq[p2]+liq[p3] < 0){
                    p2++;
                } else {
                    System.out.println(liq[p1]+" "+liq[p2]+" "+liq[p3]);
                    return;
                }
            }
        }
        System.out.println(liq[a1]+" "+liq[a2]+" "+liq[a3]);
    }
}
