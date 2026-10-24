import java.io.*;
import java.util.*;

class Solution {
    public int solution(String numbers) {
        int INF = Integer.MAX_VALUE;
        int answer = INF;
        
        // 0은 11로 치환
        // dp[left][right] = i번째 숫자를 입력했을 때 왼손, 오른손이 left, right에 위치했을때의 최소 누적비용
        int[][] dp = new int[10][10];
        for(int i=0; i<10; i++){
            Arrays.fill(dp[i], INF);
        }
        
        dp[4][6] = 0;
        
        for(int idx=0; idx<numbers.length(); idx++){
            int[][] nextDp = new int[10][10];
            for(int i=0; i<10; i++){
                Arrays.fill(nextDp[i], INF);
            }
            
            int n = numbers.charAt(idx) - '0';
            
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    if(dp[i][j]==INF) continue; // 지금까지 왼손, 오른손이 있었던 적이 없다면 pass
                    
                    // 왼손을 n으로 움직일 경우를 확인
                    if (n != j) { // 이때는 오른손이 n을 누르고 있으면 안됨
                        nextDp[n][j] = Math.min(nextDp[n][j], dp[i][j] + move(i, n));
                    }
                    // 오른손을 n으로 움직일 경우를 확인
                    if (n != i) { // 이때는 왼손이 n을 누르고 있으면 안됨
                        nextDp[i][n] = Math.min(dp[i][j] + move(j, n), nextDp[i][n]);
                    }
                }
            }
            
            dp = nextDp;
        }
        
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                answer = Math.min(dp[i][j], answer);
            }
        }
        return answer;
    }
    
    // 제자리 1, 상하좌우 2, 대각선 3
    private int move(int start, int end){
        if (start == end) return 1;
        if (start==0) start = 11;
        if (end==0) end = 11;
        
        int sI = (start-1)/3;
        int sJ = (start-1)%3;
        
        int eI = (end-1)/3;
        int eJ = (end-1)%3;
        
        // 즉, 상하좌우 2번보다는 대각선 1번이 이득 => 대각선을 우선으로 이동하는게 이득
        int difI = Math.abs(sI-eI);
        int difJ = Math.abs(sJ-eJ);

        int DG = Math.min(difI, difJ);
        int JS = Math.abs(difI - difJ);
        
        return DG*3 + JS*2;
    }
}