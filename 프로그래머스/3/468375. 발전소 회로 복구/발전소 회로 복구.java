import java.io.*;
import java.util.*;

class Solution {
    
    int[] di = {0, 1, 0, -1};
    int[] dj = {1, 0, -1, 0};
    int[] dh = {1, -1};
    int INF = 1_000_000_000;
    
    public int solution(int H, String[] grid, int[][] panels, int[][] seqs) {
        int answer = INF;
        
        int N = grid.length;
        int M = grid[0].length();
        int K = panels.length; // 패널 개수
        Queue<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[K + 1][K + 1];

        for (int i = 0; i <= K; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        
        // 2차원 맵으로 전환 :: 벽은 -1, 통로는 0, 패널은 bit
        // 엘리베이터 위치는 ell로 따로 저장
        int[][] map = new int[N+1][M+1];
        for(int n=1; n<=N; n++){
            for(int m=1; m<=M; m++){
                char c = grid[n-1].charAt(m-1);
                
                if(c=='#') map[n][m] = -1;
                else if(c=='@') q.add(new int[] {n, m, 0});
            }
        }
        
        for(int k=1; k<=K; k++){
            int[] panel = panels[k-1];
            
            map[panel[1]][panel[2]] |= (1<<k);
        }
        
        // 엘베 ~ 패널 사이 거리 구하기
        int cnt=0;
        boolean[][] visited = new boolean[N+1][M+1];
        visited[q.peek()[0]][q.peek()[1]] = true;
        l1: while(!q.isEmpty()){
            int[] cur = q.poll();
            // 패널이면 몇번 패널인지 확인 후 dist 채우기
            if(map[cur[0]][cur[1]]>0){
                for(int k=1; k<=K; k++){
                    if((map[cur[0]][cur[1]] & (1<<k)) == (1<<k)){
                        dist[0][k] = cur[2];
                        dist[k][0] = cur[2];
                        
                        if(++cnt == K) break l1; // 모든 패널 다 확인했으면 while문 끝
                    }
                }
            }
            
            // 4방향 탐색
            for(int d=0; d<4; d++){
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                if(0<ni&&ni<=N && 0<nj&&nj<=M && map[ni][nj]>=0 && !visited[ni][nj]){
                    visited[ni][nj] = true;
                    q.add(new int[] {ni, nj, cur[2]+1});
                }
            }
        }
        
        
        // 패널 a와 b 사이 거리 구하기
        for(int a=1; a<=K; a++){
            for(int b=a+1; b<=K; b++){
                
                // 다른 층이면 a~엘베 + 엘베~b + 층 차이
                if(panels[a-1][0] != panels[b-1][0]){
                    if (dist[a][0] != INF && dist[0][b] != INF) {
                        dist[a][b] =
                                dist[a][0]
                                + dist[0][b]
                                + Math.abs(
                                        panels[a - 1][0]
                                        - panels[b - 1][0]
                                );

                        dist[b][a] = dist[a][b];
                    }
                }
                // 같은 층이면 bfs로 길찾아보기
                else{
                    q.clear();
                    q.add(new int[] {panels[a-1][1], panels[a-1][2], 0});
                    visited = new boolean[N+1][M+1];
                    visited[panels[a-1][1]][panels[a-1][2]] = true;
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        
                        if((map[cur[0]][cur[1]] & (1<<b)) == (1<<b)){
                            dist[a][b] = cur[2];
                            dist[b][a] = cur[2];
                            
                            break;
                        }
                        
                        for(int d=0; d<4; d++){
                            int ni = cur[0] + di[d];
                            int nj = cur[1] + dj[d];
                            if(0<ni&&ni<=N && 0<nj&&nj<=M && map[ni][nj]>=0 && !visited[ni][nj]){
                                visited[ni][nj] = true;
                                q.add(new int[] {ni, nj, cur[2]+1});
                            }
                        }
                    }    
                }
            }
        }
        
        // 이제 가능한 활성화 순서로 최소 시간 구하기
        
        int[][] dp = new int[1 << K][K];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        
        int[] pre = new int[K];
        for(int[] s : seqs){
            int before = s[0]-1;
            int after = s[1]-1;
            
            pre[after] |= (1<<before);
        }
        
        // 일단 활성 가능한 패널들 찾기
        for(int next=0; next<K; next++){
            if(pre[next]==0){
                dp[1<<next][next] = dist[1][next+1];
            }
        }
        
        for(int mask=0; mask<(1<<K); mask++){
            for(int cur=0; cur<K; cur++){
                // mask 상태에서 cur을 온 적이 없으면 continue
                if(dp[mask][cur]==INF) continue;
                
                for(int next=0; next<K; next++){
                    // 이미 갔던 곳이면 continue
                    if((mask & (1<<next)) != 0 ) continue;
                    
                    // next에 갈 수 있는 상태인지 (활성화 패널 상태 확인)
                    if( (mask&pre[next]) != pre[next] ) continue;
                    
                    int moveDistance = dist[cur + 1][next + 1];

                    if (moveDistance == INF) {
                        continue;
                    }

                    int nextMask = mask | (1 << next);

                    dp[nextMask][next] = Math.min(
                            dp[nextMask][next],
                            dp[mask][cur] + moveDistance
                    );
                }
            }
        }
        
        // 이제 dp를 확인하며 모든 마스크가 체크됐을 때의 최소값 찾기
        int fullMask = (1<<K)-1;
        for(int time : dp[fullMask]) answer = Math.min(time, answer);
        
        
        return answer;
    }
}