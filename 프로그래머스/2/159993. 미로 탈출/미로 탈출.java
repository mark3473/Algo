import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int answer = 0;
        
        int[] di = {0, 1, 0, -1};
        int[] dj = {1, 0, -1, 0};
        
        int[] start = new int[3];
        int[] end = new int[2];
        int[] lever = new int[3];
        int size = maps.length;
        int width = maps[0].length();
        
        for(int i=0; i<size; i++){
            for(int j=0; j<width; j++){
                char c = maps[i].charAt(j);
                if(c=='S') {
                    start[0] = i;
                    start[1] = j;
                } else if(c=='E'){
                    end[0] = i;
                    end[1] = j;
                } else if(c=='L'){
                    lever[0] = i;
                    lever[1] = j;
                }
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[size][width];
        start[2] = 0;
        q.add(start);
        v[start[0]][start[1]] = true;
        l1:while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int ni = cur[0]+di[d];
                int nj = cur[1]+dj[d];
                
                if(0<=ni&&ni<size && 0<=nj&&nj<width && !v[ni][nj] && maps[ni].charAt(nj)!='X'){
                    v[ni][nj] = true;
                    if(ni==lever[0] && nj==lever[1]){
                        answer = cur[2]+1;
                        break l1;
                    }
                    else{
                        q.add(new int[] {ni, nj, cur[2]+1});
                    }
                }
            }
        }
        
        if(!v[lever[0]][lever[1]]) return -1;
        
        q = new LinkedList<>();
        v = new boolean[size][width];
        lever[2] = 0;
        q.add(lever);
        v[lever[0]][lever[1]] = true;
        l2:while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int d=0; d<4; d++){
                int ni = cur[0]+di[d];
                int nj = cur[1]+dj[d];
                
                if(0<=ni&&ni<size && 0<=nj&&nj<width && !v[ni][nj] && maps[ni].charAt(nj)!='X'){
                    v[ni][nj] = true;
                    if(ni==end[0] && nj==end[1]){
                        answer += (cur[2]+1);
                        break l2;
                    }
                    else{
                        q.add(new int[] {ni, nj, cur[2]+1});
                    }
                }
            }
        }
        
        if(v[end[0]][end[1]]) return answer;
        else return -1;
    }
}