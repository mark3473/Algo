import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        int[] di = {0, 1, 0, -1};
        int[] dj = {1, 0, -1, 0};
        int size = maps.length;
        int width = maps[0].length();
        boolean[][] v = new boolean[size][width];
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<size; i++){
            for(int j=0; j<width; j++){
                if(maps[i].charAt(j)!='X' && !v[i][j]){
                    v[i][j] = true;
                    int sum = maps[i].charAt(j) - '0';
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[] {i,j});
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        for(int d=0; d<4; d++){
                            int ni = cur[0]+di[d];
                            int nj = cur[1]+dj[d];
                            
                            if(0<=ni&&ni<size && 0<=nj&&nj<width && !v[ni][nj]){
                                if(maps[ni].charAt(nj)=='X') continue;
                                sum += (maps[ni].charAt(nj)-'0');
                                v[ni][nj] = true;
                                q.add(new int[] {ni, nj});
                            }
                        }
                    }
                    arr.add(sum);
                }
            }
        }
        
        if(arr.isEmpty()) {
            return new int[] {-1};
        }
        else{
            int[] answer = new int[arr.size()];
            for(int i=0; i<arr.size(); i++){
                answer[i] = arr.get(i);
            }
            Arrays.sort(answer);
            return answer;
        }
    }
}