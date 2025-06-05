import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static int[] hi = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] hj = {-2, -1, 1, 2, 2, 1, -1, -2};

    static class Point{
        int i;
        int j;
        int h;
        int time;

        Point(int i, int j, int h, int t){
            this.i = i;
            this.j = j;
            this.h = h;
            this.time = t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine()); // 말 움직임 횟수
        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken()); // 가로
        int H = Integer.parseInt(st.nextToken()); // 세로

        if(W==1 && H==1) {
            System.out.println(0);
            return;
        }

        int[][] map = new int[H][W];
        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][][] v = new boolean[H][W][K+1];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0,0));
        while(!q.isEmpty()){
            Point cur = q.poll();

            if(cur.h<K){
                for(int d=0; d<8; d++){
                    int ni = cur.i+hi[d];
                    int nj = cur.j+hj[d];

                    if(0<=ni&&ni<H && 0<=nj&&nj<W && map[ni][nj]==0 && !v[ni][nj][cur.h+1]){
                        if(ni==H-1 && nj==W-1){
                            System.out.println(cur.time+1);
                            return;
                        }
                        v[ni][nj][cur.h+1] = true;
                        q.add(new Point(ni, nj, cur.h+1, cur.time+1));
                    }
                }
            }

            for(int d=0; d<4; d++){
                int ni = cur.i+di[d];
                int nj = cur.j+dj[d];

                if(0<=ni&&ni<H && 0<=nj&&nj<W && map[ni][nj]==0 && !v[ni][nj][cur.h]){
                    if(ni==H-1 && nj==W-1){
                        System.out.println(cur.time+1);
                        return;
                    }
                    v[ni][nj][cur.h] = true;
                    q.add(new Point(ni, nj, cur.h, cur.time+1));
                }
            }
        }

        System.out.println(-1);
    }
}
