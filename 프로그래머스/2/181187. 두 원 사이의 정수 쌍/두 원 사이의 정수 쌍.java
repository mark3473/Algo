import java.math.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        Double D1 = Math.pow(r1,2);
        Double D2 = Math.pow(r2,2);
        
        for(long x=0; x<r2; x++){
            if(x<r1){
                answer+=Math.floor(Math.sqrt(D2-Math.pow(x,2)))-Math.ceil(Math.sqrt(D1-Math.pow(x,2))) + 1;
            }
            else{
                answer+=Math.floor(Math.sqrt(D2-Math.pow(x,2)));
            }
        }
        
        return answer*4;
    }
}