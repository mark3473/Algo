import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    static class Point{
        int i;
        int j;

        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        Point robot = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        int dir = Integer.parseInt(st.nextToken()); // 바라보는 방향

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while(true){
            boolean flag = false;
            // 1. 현재칸 청소
            if(!visited[robot.i][robot.j]){
                ans++;
                visited[robot.i][robot.j] = true;
            }
            // 2. 주위 칸에 청소안한 칸 확인
            // 있으면 반시계 회전 후 앞칸 청소 안됐으면 이동
            for(int d=0; d<4; d++){
                int ni = robot.i + di[(3+dir-d)%4];
                int nj = robot.j + dj[(3+dir-d)%4];
                if(0<=ni&&ni<N && 0<=nj&&nj<M && map[ni][nj]==0 && !visited[ni][nj]) {
                    flag = true;
                    robot.i = ni;
                    robot.j = nj;
                    dir = (3+dir-d)%4;
                    break;
                }
            }
            // 3. 주위 칸에 청소안한 칸 없으면 후진
            // 4. 후진 못하면 끝
            if(!flag){
                int ni = robot.i - di[dir];
                int nj = robot.j - dj[dir];

                if(ni<0||ni>=N || nj<0||nj>=M || map[ni][nj]==1) break;
                robot.i = ni;
                robot.j = nj;
            }
        }

        System.out.println(ans);
    }
}