import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        
        for(String name : participant){
            map.put(name, map.getOrDefault(name, 0)+1);
        }
        
        for(String name : completion){
            int count = map.get(name)-1;
            if(count==0) map.remove(name);
            else map.put(name, count);
        }
        
        answer = map.keySet().iterator().next();
        
        return answer;
    }
}