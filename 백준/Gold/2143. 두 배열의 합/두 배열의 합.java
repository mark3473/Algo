import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); // 목표 수

        int n = Integer.parseInt(br.readLine()); // A 원소 수
        int[] A = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            A[i] = A[i-1] + Integer.parseInt(st.nextToken());
        }
        Map<Integer, Integer> Amap = new HashMap<>();
        for(int i=1; i<=n; i++){
            for(int j=0; j<i; j++){
                int value = A[i]-A[j];
                if(Amap.containsKey(value)){
                    Amap.put(value, Amap.get(value)+1);
                } else{
                    Amap.put(value, 1);
                }
            }
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=m; i++){
            B[i] = B[i-1] + Integer.parseInt(st.nextToken());
        }
        Map<Integer, Integer> Bmap = new HashMap<>();
        for(int i=1; i<=m; i++){
            for(int j=0; j<i; j++){
                int value = B[i]-B[j];
                if(!Amap.containsKey(T-value)) continue;
                if(Bmap.containsKey(value)){
                    Bmap.put(value, Bmap.get(value)+1);
                } else{
                    Bmap.put(value, 1);
                }
            }
        }

        long ans = 0;
//        for(int i= -1*T; i<=T; i++){
//            int j = T-i;
//            if(Amap.containsKey(i) && Bmap.containsKey(j)){
//                ans += Amap.get(i) * Bmap.get(j);
//            }
//        }
        for(int i : Amap.keySet()){
            ans += (long)Amap.get(i) * Bmap.getOrDefault(T-i, 0);
        }

        System.out.println(ans);
    }
}
