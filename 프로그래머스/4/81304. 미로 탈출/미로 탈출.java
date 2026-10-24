import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        int INF = Integer.MAX_VALUE;
        
        int[] trapIndex = new int[n+1];
        Arrays.fill(trapIndex, -1);
        
        // trapIndex[함정방 번호] = traps 내 인덱스
        for(int i=0; i<traps.length; i++){
            trapIndex[traps[i]] = i;
        }
        
        
        // 다익스트라 진행
        ArrayList<Edge>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] r : roads){
            int P = r[0];
            int Q = r[1];
            int S = r[2];
            
            // 원래 방향
            graph[P].add(new Edge(Q, S, false));
            // 반대 방향
            graph[Q].add(new Edge(P, S, true));
        }
        
        PriorityQueue<State> pq = new PriorityQueue<>(
            Comparator.comparingInt(e->e.cost)
        );
        
        // 최단거리 기록할 때는 정점 + 함정 상태별로 기록
        int stateCount = 1<<traps.length;
        // dist[i][j] = 함정 활성 상태가 j(비트마스크)일 때 i까지의 최단 거리
        int[][] dist = new int[n+1][stateCount];
        for(int i=1; i<=n; i++) Arrays.fill(dist[i], INF);
        
        dist[start][0] = 0;
        pq.add(new State(start, 0, 0));
        
        while(!pq.isEmpty()){
            State now = pq.poll();
            
            // 현재 상태의 현재 지점까지 이미 최단 경로가 존재하면 pass
            if(dist[now.node][now.mask] < now.cost) continue;
            
            // end 도착했으면 끝
            if(now.node == end) return now.cost;
            
            for(Edge next : graph[now.node]){
                // 일단 now에 도착했을 때의 함정 활성 여부
                boolean nowActive = isActive(now.node, now.mask, trapIndex);
                
                // next에 도착했을 때의 함정 활성 여부
                boolean nextActive = isActive(next.to, now.mask, trapIndex);
                
                // 둘 중 하나만 활성 상태면 방향 반전 (XOR 이용)
                boolean reversed = nowActive ^ nextActive;
                
                // 실제 이동 가능한 방향인지 확인
                if(next.isReverse != reversed) {
                    continue;
                }
                
                // next가 함정이면 함정 활성 상태 변경
                int nextMask = now.mask;
                if(trapIndex[next.to] != -1){
                    nextMask ^= (1<<trapIndex[next.to]);
                }
                
                int newCost = now.cost + next.cost;
                if(newCost < dist[next.to][nextMask]){
                    dist[next.to][nextMask] = newCost;
                    
                    pq.add(new State(next.to, newCost, nextMask));
                }
            }
        }
        
        return answer;
    }
    
    // node가 활성화된 함정인지 확인
    private boolean isActive(int node, int mask, int[] trapIndex){
        int index = trapIndex[node];
        
        if(index==-1) return false;
        
        // node와 mask를 대조했을 때 다르면 (비활성화 상태이면) 0이 됨
        return (mask & (1<<index)) != 0; // 0이 아니면 활성 상태라는 뜻
    }
    
    // 간선 정보
    class Edge{
        int to;
        int cost;
        boolean isReverse;
        
        Edge(int t, int c, boolean r){
            this.to = t;
            this.cost = c;
            this.isReverse = r;
        }
    }
    
    // 다익스트라 탐색 상태
    static class State {
        int node;
        int cost;
        int mask;

        State(int node, int cost, int mask) {
            this.node = node;
            this.cost = cost;
            this.mask = mask;
        }
    }
}