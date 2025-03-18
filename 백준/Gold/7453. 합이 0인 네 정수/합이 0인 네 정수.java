import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n];
        long[] B = new long[n];
        long[] C = new long[n];
        long[] D = new long[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        int size = n*n;
        long[] AB = new long[size];
        long[] CD = new long[size];

        int cnt=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                AB[cnt] = A[i]+B[j];
                CD[cnt++] = C[i]+D[j];
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);

        long ans = 0;
        int p1 = 0;
        int p2 = size-1;
        while(p1<size && p2>=0){
            if(AB[p1]+CD[p2]>0){
                p2--;
            } else if(AB[p1]+CD[p2]<0){
                p1++;
            } else{
                long ab = AB[p1];
                long count1 = 0;
                long cd = CD[p2];
                long count2 = 0;
                while(p1<size && AB[p1]==ab){
                    count1++;
                    p1++;
                }
                while(p2>=0 && CD[p2]==cd){
                    count2++;
                    p2--;
                }

                ans += (count1*count2);
            }
        }
        System.out.println(ans);
    }
}
