import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static class Point{
        int i;
        int j;
        int time;

        Point(int i, int j){
            this.i = i;
            this.j = j;
        }

        Point(int i, int j, int t){
            this.i = i;
            this.j = j;
            this.time = t;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        boolean[][] v = new boolean[R][C];

        Point jh = null;
        Queue<Point> fire = new LinkedList<>();

        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j]=='J') {
                    if(i==0 || i==R-1 || j==0 || j==C-1) {
                        System.out.println(1);
                        return;
                    }

                    jh = new Point(i,j,0);
                    map[i][j] = '.';
                    v[i][j] = true;
                }
                else if(map[i][j]=='F') fire.add(new Point(i,j));
            }
        }

        Queue<Point> q = new LinkedList<>();
        q.add(jh);
        while(!q.isEmpty()){
            // 불 확산
            int size = fire.size();
            for(int i=0; i<size; i++){
                Point f = fire.poll();

                for(int d=0; d<4; d++){
                    int ni = f.i+di[d];
                    int nj = f.j+dj[d];

                    if(0<=ni&&ni<R && 0<=nj&&nj<C && map[ni][nj]=='.'){
                        map[ni][nj] = 'F';
                        fire.add(new Point(ni,nj));
                    }
                }
            }

            // 지훈 시뮬레이션
            size = q.size();
            for(int i=0; i<size; i++){
                Point cur = q.poll();

                for(int d=0; d<4; d++){
                    int ni = cur.i+di[d];
                    int nj = cur.j+dj[d];

                    if(0<=ni&&ni<R && 0<=nj&&nj<C && map[ni][nj]=='.' && !v[ni][nj]){
                        if(ni==0 || ni==R-1 || nj==0 || nj==C-1) {
                            System.out.println(cur.time+2);
                            return;
                        }

                        v[ni][nj] = true;
                        q.add(new Point(ni, nj, cur.time+1));
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
        return;
    }
}
