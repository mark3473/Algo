import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while((s=br.readLine())!=null){
            int x = Integer.parseInt(s) * 10000000; // 구멍의 너비
            int n = Integer.parseInt(br.readLine()); // 레고의 수
            int[] legos = new int[n];
            for(int i=0; i<n; i++){
                legos[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(legos);

            int left = 0;
            int right = n-1;
            while(left<right){
                int sum = legos[left]+legos[right];
                if(sum>x) right--;
                else if(sum<x) left++;
                else break;
            }

            if(left<right) sb.append("yes "+legos[left]+" "+legos[right]);
            else sb.append("danger");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
