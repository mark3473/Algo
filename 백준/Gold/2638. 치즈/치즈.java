import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 0, -1, 1};
    static int[] dj = {1, -1, 0, 0};
    static int[][] map;
    static int N, M;
    static boolean[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 치즈가 모두 녹아 없어질 때까지 반복
        int ans = 0;
        while(true){
            // 외부 공기 확인
            v = new boolean[N][M];
            dfs(0,0);

            // 녹아 없어져야 할 치즈 기록
            Queue<int[]> toDelete = new LinkedList<>();
            Queue<int[]> q = new LinkedList<>();
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j]==1){
                        q.add(new int[] {i,j});
                        while(!q.isEmpty()){
                            int[] cur = q.poll();
                            int cnt=0;
                            for(int d=0; d<4; d++){
                                int ni = cur[0] + di[d];
                                int nj = cur[1] + dj[d];
                                if(0<=ni&&ni<N && 0<=nj&&nj<M && map[ni][nj]==2){
                                    cnt++;
                                    if(cnt==2) break;
                                }
                            }

                            if(cnt==2) toDelete.add(cur); //*
                        }
                    }
                }
            }
            // 지울 치즈가 없으면 끝
            if(toDelete.isEmpty()) break;

            while(!toDelete.isEmpty()){
                int[] chz = toDelete.poll();
                map[chz[0]][chz[1]] = 2;
            }

            ans++;
        }
        System.out.println(ans);
    }

    static void dfs(int i, int j){
        v[i][j] = true;
        map[i][j] = 2;

        for(int d=0; d<4; d++){
            int ni = i + di[d];
            int nj = j + dj[d];

            if(0<=ni&&ni<N && 0<=nj&&nj<M && map[ni][nj]!=1 && !v[ni][nj]){
                dfs(ni, nj);
            }
        }
    }
}
