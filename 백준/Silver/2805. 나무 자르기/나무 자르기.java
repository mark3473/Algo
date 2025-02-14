import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 나무의 수
        long M = Integer.parseInt(st.nextToken()); // 가져가려는 나무의 길이

        long[] trees = new long[N];
        long right = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            trees[i] = Long.parseLong(st.nextToken());
            right = Math.max(right, trees[i]);
        }

        long left = 0;
//        right++;
        while(left < right){
            long mid = left + (right - left)/2;
            long sum = 0;
            for(long h : trees){
                if(h > mid){ // 나무 높이가 기계 높이보다 높으면
                    sum += (h-mid); // sum에 그만큼 더하기
                }
            }
            if(sum < M){ // M 길이만큼 못가져간다면 더 낮은 높이로 잘라야함
                right = mid;
            } else {
                left = mid+1;
            }
        }

        System.out.println(left-1);
    }
}
