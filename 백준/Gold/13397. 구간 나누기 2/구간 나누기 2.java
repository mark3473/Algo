import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수 개수
        int M = Integer.parseInt(st.nextToken()); // 최대 구간 개수

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
            max = max > nums[i] ? max : nums[i];
            min = min < nums[i] ? min : nums[i];
        }

        int left = 0;
        int right = max - min; // 전체에서의 점수 (구간 내 최대값 - 최소값)
        while(left<=right){ // 기준이 될 점수에 대하여 이분 탐색 진행
            int mid = (left + right) / 2;
            if(check(mid, M, nums)){ // mid 값으로 구간 나누기 성공하면
                right = mid - 1; // 더 작은 mid 값을 위해 왼쪽을 이분탐색 진행
            } else{
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    static boolean check(int mid, int M, int[] nums){
        int setCnt = 1;

        int min = nums[0];
        int max = nums[0];

        for(int i=1; i<nums.length; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);

            if((max-min) > mid){ // 지금까지 살펴본 곳까지의 점수가 mid보다 크면
                min = nums[i]; // 그 곳부터 새로운 구간으로 설정
                max = nums[i];
                setCnt++;
                if(setCnt > M) return false; // 구간의 수가 M 초과하면 mid 값으로 구간 나누기 불가능
            }
        }
        return true;
    }
}
