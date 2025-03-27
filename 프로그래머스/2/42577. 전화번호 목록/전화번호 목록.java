import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        PriorityQueue<String> pq = new PriorityQueue<>();
        for(String s : phone_book) pq.add(s);
        
        String prev = pq.poll();
        while(!pq.isEmpty()){
            String cur = pq.poll();
            if(cur.startsWith(prev)) return false;
            prev = cur;
        }
        
        return answer;
    }
}