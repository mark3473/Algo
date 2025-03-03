import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 0, -1, 1};
    static int[] dj = {1, -1, 0, 0};
    static int R, C;
    static char[][] map;
    static int max = 1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = line.charAt(j);
            }
        }

        dfs(0, 0, 1, (1<<26) | (1<<(map[0][0]-'A')));

        System.out.println(max);
    }

    static void dfs(int i, int j, int cnt, int bit){

        for(int d=0; d<4; d++){
            int ni = i+di[d];
            int nj = j+dj[d];

            if(0<=ni&&ni<R && 0<=nj&&nj<C){
                if((bit & (1<<(map[ni][nj]-'A'))) != (1<<(map[ni][nj]-'A'))){
                    dfs(ni, nj, cnt+1, bit | (1<<(map[ni][nj]-'A')));
                }
                else max = Math.max(max, cnt);
            }
        }
    }
}
