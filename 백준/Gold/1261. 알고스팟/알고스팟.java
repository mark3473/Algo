import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[M][N];

        for(int i=0; i<M; i++){
            String line = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = line.charAt(j)-'0';
            }
        }

        boolean[][] visited = new boolean[M][N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]);
        pq.add(new int[] {0,0,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[0]==M-1 && cur[1]==N-1) {
                System.out.println(cur[2]);
                return;
            }
            if(visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            for(int d=0; d<4; d++){
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                if(0<=ni&&ni<M && 0<=nj&&nj<N && !visited[ni][nj]){
                    pq.add(new int[] {ni, nj, map[ni][nj]==0? cur[2]:cur[2]+1});
                }
            }
        }
    }
}
