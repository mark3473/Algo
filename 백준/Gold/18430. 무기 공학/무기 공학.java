import java.io.*;
import java.util.*;

public class Main {
    // ㄱ, 좌우반전 ㄱ, ㄴ, 좌우반전 ㄴ
    static int[] di_1 = {0, 1};
    static int[] dj_1 = {-1, 0};
    static int[] di_2 = {0, 1};
    static int[] dj_2 = {1, 0};
    static int[] di_3 = {-1, 0};
    static int[] dj_3 = {0, 1};
    static int[] di_4 = {-1, 0};
    static int[] dj_4 = {0, -1};

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] v = new boolean[N][M];

        dfs(0, v, 0, 0);

        System.out.println(maxSum);
    }

    static int maxSum = 0;

    static void dfs(int value, boolean[][] visited, int x, int y){
//        if(x==N-1 && y==M-1){
//            maxSum = Integer.max(maxSum, value);
//            return;
//        }

        boolean[][] copied = null;

        for(int i=x; i<N; i++){
            for(int j=i==x? y : 0; j<M; j++){
                if(visited[i][j]) continue;

                int sum = map[i][j]*2;
                int tmp = 0;
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[] {i, j});

                // 1번 모양으로 부메랑 놓기 시도
                for(int d=0; d<2; d++){
                    int ni = i+di_1[d];
                    int nj = j+dj_1[d];

                    if(0<=ni&&ni<N && 0<=nj&&nj<M && !visited[ni][nj]){
                        tmp+=map[ni][nj];
                        q.add(new int[] {ni, nj});
                    }
                    else{
                        tmp=0;
                        q.clear();
                        break;
                    }
                }
                // 부메랑 놓을 수 있으면 확정
                if(tmp!=0) {
                    copied = copy(visited);
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        copied[cur[0]][cur[1]] = true;
                    }
                    dfs(value+sum+tmp, copied, i, j);
                    tmp = 0;
                }

                // 2번 모양으로 부메랑 놓기 시도
                q.add(new int[] {i, j});
                for(int d=0; d<2; d++){
                    int ni = i+di_2[d];
                    int nj = j+dj_2[d];

                    if(0<=ni&&ni<N && 0<=nj&&nj<M && !visited[ni][nj]){
                        tmp+=map[ni][nj];
                        q.add(new int[] {ni, nj});
                    }
                    else{
                        tmp=0;
                        q.clear();
                        break;
                    }
                }
                // 부메랑 놓을 수 있으면 확정
                if(tmp!=0) {
                    copied = copy(visited);
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        copied[cur[0]][cur[1]] = true;
                    }
                    dfs(value+sum+tmp, copied, i, j);
                    tmp = 0;
                }

                // 3번 모양으로 부메랑 놓기 시도
                q.add(new int[] {i, j});
                for(int d=0; d<2; d++){
                    int ni = i+di_3[d];
                    int nj = j+dj_3[d];

                    if(0<=ni&&ni<N && 0<=nj&&nj<M && !visited[ni][nj]){
                        tmp+=map[ni][nj];
                        q.add(new int[] {ni, nj});
                    }
                    else{
                        tmp=0;
                        q.clear();
                        break;
                    }
                }
                // 부메랑 놓을 수 있으면 확정
                if(tmp!=0) {
                    copied = copy(visited);
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        copied[cur[0]][cur[1]] = true;
                    }
                    dfs(value+sum+tmp, copied, i, j);
                    tmp = 0;
                }

                // 4번 모양으로 부메랑 놓기 시도
                q.add(new int[] {i, j});
                for(int d=0; d<2; d++){
                    int ni = i+di_4[d];
                    int nj = j+dj_4[d];

                    if(0<=ni&&ni<N && 0<=nj&&nj<M && !visited[ni][nj]){
                        tmp+=map[ni][nj];
                        q.add(new int[] {ni, nj});
                    }
                    else{
                        tmp=0;
                        q.clear();
                        break;
                    }
                }
                // 부메랑 놓을 수 있으면 확정
                if(tmp!=0) {
                    copied = copy(visited);
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        copied[cur[0]][cur[1]] = true;
                    }
                    dfs(value+sum+tmp, copied, i, j);
                    tmp = 0;
                }
            }
        }

        maxSum = Integer.max(maxSum, value);
        return;
    }

    static boolean[][] copy(boolean[][] v){
        boolean[][] c = new boolean[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                c[i][j] = v[i][j];
            }
        }

        return c;
    }
}