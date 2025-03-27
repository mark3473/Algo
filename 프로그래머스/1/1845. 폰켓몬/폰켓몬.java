import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        int N = nums.length;
        Set<Integer> set = new HashSet<>();
        for(int n : nums){
            if(set.add(n)) answer++;
        }
        // ans에는 포켓몬의 종류 수가 저장되어있음
        // N/2보다 작으면 그대로가 정답. 같거나 더 크면 N/2가 정답
        
        answer = answer < (N/2) ? answer : (N/2);
        
        return answer;
    }
}