import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] LIS = new int[N];
        int cnt = 0;
        st = new StringTokenizer(br.readLine());
        LIS[0] = Integer.parseInt(st.nextToken());
        for(int i=1; i<N; i++){
            int val = Integer.parseInt(st.nextToken());
            if(val>LIS[cnt]) LIS[++cnt] = val;
            else{
                int idx = binarySearch(LIS, val, cnt);
                LIS[idx] = val;
            }
        }

        System.out.println(N-cnt-1);
    }

    static int binarySearch(int[] arr, int val, int right){
        int left = 0;
        while(left<right){
            int mid = (left+right)/2;

            if(arr[mid]<val){
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
