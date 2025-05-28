import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = null;
        while(((input = br.readLine()))!= null){
            input = input.trim();
            StringTokenizer st;
            st = new StringTokenizer(br.readLine());

            int len = 0;
            int[] lis = new int[Integer.parseInt(input)+1];
            while(st.hasMoreTokens()){
                int p = Integer.parseInt(st.nextToken());
                if(lis[len]<p){
                    lis[++len] = p;
                }
                else {
                    lis[binarySearch(lis, len, p)] = p;
                }
            }

            sb.append(len).append("\n");
        }
        System.out.println(sb);
    }

    static int binarySearch(int[] lis, int right, int val){
        int left = 0;
        while(left<right){
            int mid = (left + right)/2;

            if(lis[mid]>=val) {
                right = mid;
            }
            else {
                left = mid+1;
            }
        }
        return right;
    }
}
