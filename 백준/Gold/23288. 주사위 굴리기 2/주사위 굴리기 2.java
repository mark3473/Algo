import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dj = {1, 0, -1, 0};

    static int[][] dice = {{0,2,0},{4,1,3},{0,5,0},{0,6,0}};
    static int dir = 0; // 처음에는 우 방향

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 세로크기
        int M = Integer.parseInt(st.nextToken()); // 가로크기
        int K = Integer.parseInt(st.nextToken()); // 이동 횟수

        int[][] map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ci = 0;
        int cj = 0;
        int sum = 0;

        for(int k=0; k<K; k++){
            // 1. 방향대로 한칸 움직임
            ci+=di[dir];
            cj+=dj[dir];

            if(ci<0 || ci>=N || cj<0 || cj>=M){
                ci-=2*di[dir];
                cj-=2*dj[dir];
                dir = (dir+2)%4;
            }

            // 2. 다이스 눈금 위치 변경
            roll(dir);

            // 3. 점수 합산
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] {ci, cj});
            boolean[][] v = new boolean[N][M];
            v[ci][cj] = true;
            int cnt = 1;
            while(!q.isEmpty()){
                int[] cur = q.poll();
                for(int d=0; d<4; d++){
                    int ni = cur[0] + di[d];
                    int nj = cur[1] + dj[d];
                    if(0<=ni&&ni<N && 0<=nj&&nj<M && map[ni][nj]==map[ci][cj] && !v[ni][nj]){
                        v[ni][nj] = true;
                        cnt++;
                        q.add(new int[] {ni, nj});
                    }
                }
            }
            sum += (map[ci][cj]*cnt);

            // 4. 방향 변경
            int val = dice[3][1];

            if(val>map[ci][cj]) dir = (dir+1)%4;
            else if(val<map[ci][cj]) dir = (dir+3)%4;
        }

        System.out.println(sum);
    }

    static void roll(int dd){
        // dir : 0 우, 1 하, 2 좌, 3 상
        int[] tmp = new int[4];
        if(dd==0){
            for(int i=0; i<3; i++){
                tmp[i] = dice[1][i];
            }
            tmp[3] = dice[3][1];

            dice[1][0] = tmp[3];
            dice[1][1] = tmp[0];
            dice[1][2] = tmp[1];
            dice[3][1] = tmp[2];
        }
        else if(dd==1){
            for(int i=0; i<4; i++){
                tmp[i] = dice[i][1];
            }

            for(int i=0; i<4; i++){
                dice[i][1] = tmp[(i+3)%4];
            }
        }
        else if(dd==2){
            for(int i=0; i<3; i++){
                tmp[i] = dice[1][i];
            }
            tmp[3] = dice[3][1];

            dice[1][0] = tmp[1];
            dice[1][1] = tmp[2];
            dice[1][2] = tmp[3];
            dice[3][1] = tmp[0];
        }
        else if(dd==3){
            for(int i=0; i<4; i++){
                tmp[i] = dice[i][1];
            }

            for(int i=0; i<4; i++){
                dice[i][1] = tmp[(i+1)%4];
            }
        }
    }
}
