import java.io.*;
import java.util.*;


//
public class Main {
    static int[] di = {0, 0, -1, 1};
    static int[] dj = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // map의 크기
        int[][] map = new int[N][N];
        int min = 101;
        int max = 0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(map[i][j], min);
                max = Math.max(map[i][j], max);
            }
        }

        // min~max만큼 비가 왓을 때 안전구역 몇개 있는지 확인하기
        int ans=1;
        for(int h=min; h<=max; h++){
            boolean[][] visited = new boolean[N][N];
            int cnt=0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(visited[i][j] || map[i][j]<=h) continue;

                    visited[i][j] = true;
                    cnt++;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[] {i,j});
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        for(int d=0; d<4; d++){
                            int ni = cur[0] + di[d];
                            int nj = cur[1] + dj[d];

                            if(0<=ni&&ni<N && 0<=nj&&nj<N && !visited[ni][nj] && map[ni][nj]>h){
                                visited[ni][nj] = true;
                                q.add(new int[] {ni, nj});
                            }
                        }
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }
}
