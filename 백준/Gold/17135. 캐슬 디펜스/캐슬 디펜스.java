import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, -1, 0};
    static int[] dj = {-1, 0, 1};
    static int N, M, D;
    static int[][] map;
    static int Max = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        D = Integer.parseInt(st.nextToken()); // 궁수 공격 거리 제한

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0, new int[] {-1,-1,-1});

        System.out.println(Max);
    }

    // 궁수의 위치 조합 생성
    static void comb(int cnt, int val, int[] arch){
        if(cnt==3){
            Max = Math.max(Max, simulate(arch));
            return;
        }

        for(int i=val; i<M; i++){
            arch[cnt] = i;
            comb(cnt+1, i+1, arch);
        }
    }

    // 궁수 위치에 따른 시뮬레이션 실행
    static int simulate(int[] arch){
        int castle = N;
        int count = 0;
        int[][] killed = new int[N][M];

        while(castle>=0){ // 각 턴마다 반복
            l1:for(int i=0; i<3; i++){ // 모든 궁수에 대하여 공격할 적 찾기
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[] {castle, arch[i], 0}); // i좌표, j좌표, 거리
                boolean[][] v = new boolean[N][M];
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    if(cur[2]>=D) continue;
                    for(int d=0; d<3; d++){
                        int ni = cur[0] + di[d];
                        int nj = cur[1] + dj[d];
                        if(0<=ni&&ni<castle && 0<=nj&&nj<M && !v[ni][nj]){ // 갈 수 있는 새로운 곳 도착했는데

                            if(map[ni][nj]==1 && (killed[ni][nj]==0 || killed[ni][nj]==N+1-castle)){ // 살아있는 적을 만났으면
                                if(killed[ni][nj]==0){ // 이전 시간에 죽은 적이면 패스
                                    count+=1;
                                    killed[ni][nj] = N+1-castle;
                                }
                                continue l1;
                            }

                            v[ni][nj] = true;
                            q.add(new int[] {ni, nj, cur[2]+1});
                        }
                    }
                }
            }
            castle-=1;
        }

        return count;
    }
}
