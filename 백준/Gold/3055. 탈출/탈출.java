import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static class Point{
        int i;
        int j;

        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        Point hedge = null; // 고슴도치 위치
        Point dest = null; // 목적지 위치
        Queue<Point> water = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        char[][] map = new char[R][C];
        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j]=='*'){
                    water.add(new Point(i, j));
                }
                else if(map[i][j]=='S'){
                    hedge = new Point(i, j);
                    visited[i][j] =true;
                    map[i][j] = '.';
                }
                else if(map[i][j]=='D'){
                    dest = new Point(i, j);
                }
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.add(hedge);
        int time = 0;
        while(!q.isEmpty()){
            time++;
            // 1. 물 확장
            int cnt = water.size();
            for(int c=0; c<cnt; c++){
                Point w = water.poll();
                for(int d=0; d<4; d++){
                    int ni = w.i+di[d];
                    int nj = w.j+dj[d];

                    if(0<=ni&&ni<R && 0<=nj&&nj<C && map[ni][nj]=='.'){
                        map[ni][nj] = '*';
                        water.add(new Point(ni, nj));
                    }
                }
            }

            // 2. 고슴도치 이동
            cnt = q.size();
            for(int c= 0; c<cnt; c++){
                Point cur = q.poll();

                for(int d=0; d<4; d++){
                    int ni = cur.i+di[d];
                    int nj = cur.j+dj[d];

                    if(0<=ni&&ni<R && 0<=nj&&nj<C && !visited[ni][nj] && map[ni][nj]!='*' && map[ni][nj]!='X'){
                        if(map[ni][nj]=='D'){
                            System.out.println(time);
                            return;
                        }

                        visited[ni][nj] = true;
                        q.add(new Point(ni, nj));
                    }
                }
            }

            // 3. 목적지 근처에 길이 끊겨있으면 종료
            cnt = 0;
            for(int d=0; d<4; d++){
                int ni = dest.i+di[d];
                int nj = dest.j+dj[d];

                if(0<=ni&&ni<R && 0<=nj&&nj<C){
                    if(map[ni][nj]=='.'){
                        cnt++;
                    }
                }
            }
            if(cnt==0){
                System.out.println("KAKTUS");
                return;
            }
        }

        System.out.println("KAKTUS");
        return;
    }
}