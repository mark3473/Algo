import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기 개수

        long[] points = new long[N];
        for(int i=0; i<N; i++){
            points[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(points);

        long[] distances = new long[N-1]; // 집들 사이 간격을 저장할 것이므로
        long left = 1;
        long right = 0;
        long before = points[0];
        for(int i=0; i<N-1; i++){
            long cur = points[i+1];
            distances[i] = cur - before;
            before = cur;
            right += distances[i];
        }
        right++;
        while(left < right){
            long mid = left + (right - left)/2;
            int cnt = 1;
            long sum = 0;
//            long min = Long.MAX_VALUE;
            // mid 간격으로 설치하면 설치 가능한 공유기 개수 찾기
            for(int i=0; i<N-1; i++){
                sum += distances[i];
                if(sum >= mid){ // 공유기를 설치할 때 이전의 설치한 곳과의 거리를 살펴봐
                    // 가장 인접한 두 공유기 사이의 거리를 갱신해야함
                    cnt++;
//                    min = Math.min(min, sum);
                    sum = 0;
                }
            }

            // 설치 가능한 공유기 개수가 C보다 작으면 더 좁게 설치해야함
            if(cnt < C){
                right = mid;
            }
            else { // C 이상이면 더 과감하게 설치 가능
                left = mid + 1;
            }
        }

        System.out.println(left - 1);
    }
}
