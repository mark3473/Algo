import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            int left = 0;
            int right = N-1;

            int value = Integer.MAX_VALUE;
            int count = 0;

            while(left<right){
                int sum = arr[left]+arr[right];
                int dif = Math.abs(sum-K);
                if(dif < value){
                    value = dif;
                    count = 1;
                }
                else if(dif == value){
                    count+=1;
                }


                if(sum>K){
                    right-=1;
                }
                else {
                    left+=1;
                }
            }

            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
