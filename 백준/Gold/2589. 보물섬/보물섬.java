import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로

        int[] di = {0, 1, -1, 0};
        int[] dj = {1, 0, 0, -1};

        String[] map = new String[N];
        for(int i=0; i<N; i++){
             map[i] = br.readLine();
        }

        int ans = -1;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i].charAt(j)=='L'){
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[] {i,j,0});
                    boolean[][] v = new boolean[N][M];
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        if(v[cur[0]][cur[1]]) continue;
                        v[cur[0]][cur[1]] = true;
                        ans = Math.max(ans, cur[2]);

                        for(int d=0; d<4; d++){
                            int ni = cur[0]+di[d];
                            int nj = cur[1]+dj[d];

                            if(0<=ni&&ni<N && 0<=nj&&nj<M && map[ni].charAt(nj)=='L'){
                                q.add(new int[] {ni, nj, cur[2]+1});
                            }
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
