import java.util.*;
import java.io.*;

class Solution {
    public String solution(int[] numbers) {
        int len = numbers.length;
        String[] nums = new String[len];
        boolean zeroFlag = true;
        for(int i=0; i<len; i++){
            nums[i] = numbers[i]+"";
            if(numbers[i]!=0) zeroFlag = false;
        }
        Arrays.sort(nums, (o1,o2)->(o2+o1).compareTo(o1+o2));
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<len; i++){
            sb.append(nums[i]);
        }
        
        if(zeroFlag) return "0"; 
        return sb.toString();
    }
}