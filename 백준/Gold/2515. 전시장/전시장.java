import java.io.*;
import java.util.*;

public class Main {
    static class Picture implements Comparable<Picture>{
        int height;
        int cost;

        Picture(int h, int c) {
            this.height = h;
            this.cost = c;
        }

        @Override
        public int compareTo(Picture o) {
            return this.height-o.height; // 오름차순
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 그림 수
        int S = Integer.parseInt(st.nextToken()); // 판매 가능 그림

        Picture[] list = new Picture[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            list[i] = new Picture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(list);

        int[] dp = new int[N+1]; // dp[i] = (오름차순) i번째 그림까지 따져봤을 때 최대합
        // dp[0] = 아무그림도 선택 안했을 때. 즉 실질적으로 i번째가 아니라 i-1번째 그림인 셈

        for(int i=1; i<=N; i++){
            int h = list[i-1].height; // 이번에 따져볼 그림의 높이

            // dp[i] = i번째 그림을 넣는 것 (dp[k] + cost) vs 안넣는 것 (dp[i-1])

            int left = 0;
            int right = i-1;
            while(left<right){
                int mid = (left+right)/2;

                if(h-list[mid].height>=S){ // 보이는 부분의 높이가 S 이상이면
                    left = mid+1;
                }
                else {
                    right = mid;
                }
            }

            dp[i] = Math.max(dp[left]+list[i-1].cost, dp[i-1]);
        }

        System.out.println(dp[N]);
    }
}
