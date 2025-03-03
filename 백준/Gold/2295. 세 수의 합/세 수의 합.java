import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] nums = new long[N];
        long limit = 0;
        for(int i=0; i<N; i++){
            nums[i] =  Long.parseLong(br.readLine());
        }
        Arrays.sort(nums);

        ArrayList<Long> sums = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=i; j<N; j++){
                sums.add(nums[i]+nums[j]);
            }
        }
        Collections.sort(sums); // 세 수들의 합 집합

        for(int ans=N-1; ans>=0; ans--){
            for(int c=0; c<ans; c++){
                int left = 0;
                int right = sums.size()-1;

                while(left<=right){
                    int mid = (left+right)/2;

                    if(sums.get(mid) == nums[ans]-nums[c]){
                        System.out.println(nums[ans]);
                        return;
                    }
                    else if(sums.get(mid)<nums[ans]-nums[c]){
                        left = mid+1;
                    }else{
                        right = mid-1;
                    }
                }
            }
        }
    }
}
