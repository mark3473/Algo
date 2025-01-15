import java.io.*;
import java.util.*;

public class Main {
    static class info{
        int i;
        int j;
        int k;
        int cnt;

        info(int i, int j, int k, int c){
            this.i = i;
            this.j = j;
            this.k = k;
            this.cnt = c;
        }
    }

    static int N, M, K;

    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};

    static int[][] map;
    static boolean[][][] visited;
    static Queue<info> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        K = Integer.parseInt(st.nextToken()); // 부술 수 있는 경우의 수

        map = new int[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j) - '0'; // 0이면 이동가능공간, 1이면 벽
            }
        }
        visited = new boolean[N][M][K+1];

        int ans = bfs();
        System.out.println(ans);
    }

    static int bfs(){
        Queue<info> q = new ArrayDeque<>();
        q.add(new info(0, 0, 0, 1));
        visited[0][0][0] = true;
        while(!q.isEmpty()){
            info cur = q.poll();
            if(cur.i==N-1 && cur.j==M-1){
                return cur.cnt;
            }

            int time = cur.cnt%2; // 1이면 아침. 0이면 저녁.
            if(time==0){
                visited[cur.i][cur.j][cur.k] = true;
                q.add(new info(cur.i, cur.j, cur.k, cur.cnt+1));
            }

            for(int d=0; d<4; d++){
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if(0<=ni&&ni<N && 0<=nj&&nj<M){
                    if(map[ni][nj]==0 && !visited[ni][nj][cur.k]){
                        visited[ni][nj][cur.k] = true;
                        q.add(new info(ni, nj, cur.k, cur.cnt+1));
                    }
                    else if(map[ni][nj]==1 && cur.k<K && !visited[ni][nj][cur.k+1]) {
                        if(time==1){ // 낮이면 부수고 갈 수 있음
                            visited[ni][nj][cur.k+1] = true;
                            q.add(new info(ni, nj, cur.k+1, cur.cnt+1));
                        }
                    }
                }
            }
        }
        return -1;
    }
}