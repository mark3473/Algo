import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> map = new HashMap<>();
        
        for(String[] c : clothes){
            map.put(c[1], map.getOrDefault(c[1], 0)+1);
        }
        
        System.out.println(map);
        
        Iterator<String> itr = map.keySet().iterator();
        while(itr.hasNext()){
            String s = itr.next();
            answer*=(map.get(s)+1);
        }
        
        return answer-1;
    }
}