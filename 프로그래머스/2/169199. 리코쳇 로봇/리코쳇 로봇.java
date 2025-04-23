import java.io.*;
import java.util.*;

class Solution {
    int Min = Integer.MAX_VALUE;
    int[] di = {0, 1, 0, -1};
    int[] dj = {1, 0, -1, 0};
    
    public int solution(String[] board) {
        int answer = 0;
        int depth = board.length;
        int width = board[0].length();
        
        int[] Start = new int[2];
        int[] End = new int[2];
        for(int i=0; i<depth; i++){
            for(int j=0; j<width; j++){
                if(board[i].charAt(j)=='R') {
                    Start[0] = i;
                    Start[1] = j;
                }
                else if(board[i].charAt(j)=='G'){
                    End[0] = i;
                    End[1] = j;
                }
            }
        }
        boolean[][] v = new boolean[depth][width];
        v[Start[0]][Start[1]] = true;
        // dfs(End, board, depth, width, Start, v, 0);
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {Start[0], Start[1], 0});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            for(int d=0; d<4; d++){
                int ni = cur[0];
                int nj = cur[1];
                
                while(0<=ni+di[d]&&ni+di[d]<depth && 0<=nj+dj[d]&&nj+dj[d]<width && board[ni+di[d]].charAt(nj+dj[d])!='D'){
                    ni += di[d];
                    nj += dj[d];
                }
                
                if(ni==End[0] && nj==End[1]) return cur[2]+1;
                
                if(!v[ni][nj]){
                    v[ni][nj] = true;
                    q.add(new int[] {ni, nj, cur[2]+1});
                }
            }
        }
        
        return -1;
    }
}