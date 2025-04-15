import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int len = sequence.length;
        int sum = sequence[0];        
        int left = 0;
        int right = 0;
        int size = 1000000;
        while(right<len && left<len){
            if(sum==k){
                if(right-left<size){
                    answer[0] = left;
                    answer[1] = right;
                    size = right-left;
                }
                right++;
                if(right<len) sum+=sequence[right];
            }
            else if(sum>k){
                if(left<len) sum-=sequence[left];
                left++;
            }
            else if(sum<k){
                right+=1;
                if(right<len) sum+=sequence[right];
            }
        }
        
        return answer;
    }
}