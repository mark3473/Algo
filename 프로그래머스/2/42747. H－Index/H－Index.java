import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        int len = citations.length;
        int left = 0;
        int right = 0;
        for(int i : citations) right = Math.max(i, right);
        while(left<=right){
            int mid = (left + right)/2;
            int cnt = 0;
            for(int i:citations){
                if(i>=mid) cnt++;
            }
            if(cnt>=mid){
                answer = mid;
                left = mid+1;
            } else right = mid-1;
        }
        
        return answer;
    }
}