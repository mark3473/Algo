import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // n : 지점 수
        // paths : 등산로의 정보 담긴 2차원 배열
        // gates : 출입구 배열
        // summits : 산봉우리 배열
        
        // 출입구 배열에서 시작해 산봉우리 하나에 도착할 때 까지의 최소 intensity 구하기
        // 다익스트라를 모든 출발지점에서부터 시작
        
        int[] answer = {};
        int INF = 10000001;
        
        boolean[] isSummit = new boolean[n+1];
        for(int s : summits) isSummit[s] = true;
        
        
        ArrayList<Edge>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) graph[i] = new ArrayList<Edge>();
        for(int[] path : paths){
            int i = path[0];
            int j = path[1];
            int w = path[2];
            
            graph[i].add(new Edge(j, w));
            graph[j].add(new Edge(i, w));
        }
        
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        PriorityQueue<Edge> pq = new PriorityQueue<>(
            Comparator.comparingInt(e->e.cost)
        );
        
        for(int gate : gates){ // 출발 지점까지의 비용은 0으로 초기화
            dist[gate] = 0;
            pq.add(new Edge(gate, 0));
        }
        
        int summit = 0;
        while(!pq.isEmpty()){
            Edge cur = pq.poll(); // 현재까지의 최소 경로 꺼내기
            // 도착지가 산봉우리면 해당 경로는 거기까지.
            // 근데 같은 intensity여도 산봉우리의 번호가 최소여야하므로 break가 아닌 continue
            if(isSummit[cur.to]) continue;
            
            if(cur.cost > dist[cur.to]) continue; // 해당 경로의 비용이 경로의 도착지까지의 최저 거리보다 크면 버리기
            
            // 여전히 최소 경로라면 경로의 도착지에서 갈 수 있는 곳을 살펴보기
            for(Edge next : graph[cur.to]){
                int newCost = Math.max(cur.cost, next.cost);
                
                if(newCost < dist[next.to]){
                    dist[next.to] = newCost;
                    pq.add(new Edge(next.to, newCost));
                }
            }
        }
        
        Arrays.sort(summits);
        int minI = INF;
        int minS = 0;
        
        for(int s : summits){
            if(minI > dist[s]) {
                minI = dist[s];
                minS = s;
            }
        }
        
        answer = new int[] {minS, minI};
        
        
        return answer;
    }
    
    class Edge{
        int to;
        int cost;
        
        Edge(int t, int c){
            this.to = t;
            this.cost = c;
        }
    }
}