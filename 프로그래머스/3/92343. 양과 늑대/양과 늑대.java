import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        // info[i] = i번 노드가 양인지 늑대인지 / 0 or 1
        // edges[i][2] = i개의 간선 정보
        // visited를 관리하되 양, 늑대 수에 따라 같은 노드여도 구분할 수 있도록
        int len = info.length;
        
        ArrayList<Integer>[] graph = new ArrayList[len];
        for(int i=0; i<len; i++) graph[i] = new ArrayList<>();
        for(int[] e : edges){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        boolean[][] visited = new boolean[len+1][1<<(len+1)];
        
        Queue<State> q = new ArrayDeque<>();
        q.add(new State(0, 1, 0, (1<<len) | (1<<0)));
        
        int answer = 0;
        
        while(!q.isEmpty()){
            State cur = q.poll();
            
            if(visited[cur.node][cur.mask]) continue;
            visited[cur.node][cur.mask] = true;
            
            answer = Math.max(answer, cur.sheep);
            
            for(int next : graph[cur.node]){
                int plus = taken(cur.mask, next) ? 0 : 1; // next가 들렸던 곳이면 동물 추가 안함
                
                if(info[next]==0 && !visited[next][cur.mask | (1<<next)]) {
                    q.add(new State(next, cur.sheep+plus, cur.wolf, cur.mask | (1<<next)));
                }
                else if(info[next]==1){
                    if(cur.sheep > cur.wolf+plus && !visited[next][cur.mask | (1<<next)]) { // 늑대면 양보다 작아야 갈 수 있음
                        q.add(new State(next, cur.sheep, cur.wolf+plus, cur.mask | (1<<next)));
                    }
                }
            }
        }
        
        return answer;
    }
    
    private boolean taken (int mask, int node){      
        return (mask & (1<<node)) == (1<<node);
    }
    
    class State{
        int node;
        int sheep;
        int wolf;
        int mask; // 어떤 곳을 들렸는지
        
        State(int n, int s, int w, int m){
            this.node = n;
            this.sheep = s;
            this.wolf = w;
            this.mask = m;
        }
    }
}