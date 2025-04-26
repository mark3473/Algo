import java.io.*;
import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        Stack<int[]> Dstack = new Stack<>();
        Stack<int[]> Pstack = new Stack<>();
        for(int i=0; i<n; i++){
            if(deliveries[i]!=0) Dstack.push(new int[] {i+1, deliveries[i]});
            if(pickups[i]!=0) Pstack.push(new int[] {i+1, pickups[i]});
        }
        
        while(!Dstack.isEmpty() || !Pstack.isEmpty()){
            int distance = 0;
            int count=0;
            
            while(!Dstack.isEmpty() && count<cap){
                int[] d = Dstack.pop();
                if(distance==0) distance = d[0];
                
                count += d[1];
                if(count>cap) {
                    Dstack.push(new int[] {d[0], count-cap});
                }
            }
            
            count = 0;
            while(!Pstack.isEmpty() && count<cap){
                int[] p = Pstack.pop();
                distance = Math.max(p[0], distance);
                
                count += p[1];
                if(count>cap) {
                    Pstack.push(new int[] {p[0], count-cap});
                }
            }
            
            answer += distance;
        }
        
        return answer*2;
    }
}