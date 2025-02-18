import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] nums = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Long.parseLong(st.nextToken());
        }

        ArrayList<Long> LIS = new ArrayList<>();
        LIS.add(Long.MIN_VALUE);

        for(int i=0; i<N; i++){
            // nums의 값을 하나씩 비교해서
            // LIS에 최근에 넣은 값보다 크면 바로 그냥 LIS에 넣기
            // 그렇지 않으면 이분탐색으로 넣을 곳 찾아서 교체하기
            if(LIS.get(LIS.size()-1) < nums[i]){
                LIS.add(nums[i]);
            } else{
                int left = 1;
                int right = LIS.size()-1;
                while(left < right){
                    int mid = (left + right)/2;
                    if(LIS.get(mid) >= nums[i]){
                        right = mid;
                    } else{
                        left = mid + 1;
                    }
                }

                LIS.set(left, nums[i]);
            }
        }

        System.out.println(LIS.size()-1);
    }
}
