import java.io.*;
import java.util.*;

public class Main {
    // 북동남서
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    static int Ri, Rj, Rd;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        // 방의 크기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        Ri = Integer.parseInt(st.nextToken());
        Rj = Integer.parseInt(st.nextToken());
        Rd = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {Ri, Rj, Rd});
        int ans = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(map[cur[0]][cur[1]]!=2){
                map[cur[0]][cur[1]]=2;
                ans++;
            }

            // 주변 칸의 청소 상태 확인
            for(int d=0; d<4; d++){
                int nd = (cur[2]-d+3)%4;
                int ni = cur[0]+di[nd];
                int nj = cur[1]+dj[nd];
                if(0<=ni&&ni<N && 0<=nj&&nj<M){
                    // 청소되어있지 않는 칸이면
                    if(map[ni][nj]==0){
                        // 반시계 돌고
                        // 그 방향이 청소되지 않은 칸이면 전진
                        q.add(new int[] {ni, nj, nd});
                        break;
                    }
                }
            }
            
            // 주변이 다 청소한 상태이면
            if(q.isEmpty()){
                int ni = cur[0] - di[cur[2]];
                int nj = cur[1] - dj[cur[2]];
                if(0<=ni&&ni<N && 0<=nj&&nj<M && map[ni][nj]!=1){
                    q.add(new int[] {ni, nj, cur[2]});
                }
                else break;
            }
        }

        System.out.println(ans);
    }
}
