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

            // val보다 작은 수 중 가장 큰 수의 오른쪽에 위치 시켜야함
            if(lis[mid]>=val) { // 따라서 lis[mid]값이 val보다 크거나 같으면 일단 그 위치를 용의선상에 놓음
                right = mid; // 그래서 right을 mid값으로 하고 나중에 return right을 하는거임
            }
            else {
                left = mid+1;
            }
        }
        return right;
    }
}
