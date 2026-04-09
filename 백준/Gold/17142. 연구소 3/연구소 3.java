import java.io.*;
import java.util.*;

public class Main {
    static class Virus{
        int i;
        int j;
        int time;

        Virus(int i, int j){
            this.i = i;
            this.j = j;
            this.time = 0;
        }

        Virus(int i, int j, int t){
            this.i = i;
            this.j = j;
            this.time = t;
        }
    }

    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0 ,-1 ,0};
    static int N, M, total, ans;
    static ArrayList<Virus> EveryV;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 연구소크기
        M = Integer.parseInt(st.nextToken()); // 활성화 수
        EveryV = new ArrayList<>();
        map = new int[N][N];
        total = 0;
        ans = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j]==2) EveryV.add(new Virus(i, j));
                else if(map[i][j]==0) total++;
            }
        }

        if(total==0) {
            System.out.println(0);
            return;
        }

        select(0, 0, new int[M]);

        System.out.println(ans==Integer.MAX_VALUE? -1 : ans);
    }

    static void select(int cnt, int idx, int[] bit){
        if(cnt==M){
            // 시뮬레이션 시작
            int countBlank = 0;
            // 1. 복사한 맵에 활성 바이러스 표시
            int[][] cmap = new int[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    cmap[i][j] = map[i][j];
                }
            }

            Queue<Virus> q = new LinkedList<>();
            for(int i : bit){
                Virus v = EveryV.get(i);
                q.add(v);
            }

            while(!q.isEmpty()){
                Virus curV = q.poll();
                if(cmap[curV.i][curV.j]==3) continue;

                if(cmap[curV.i][curV.j]==0) {
                    countBlank++;

                    if(countBlank==total){
                        ans = Math.min(ans, curV.time);

                        return;
                    }
                }

                cmap[curV.i][curV.j] = 3;

                for(int d=0; d<4; d++){
                    int ni = curV.i+di[d];
                    int nj = curV.j+dj[d];

                    if(0<=ni&&ni<N && 0<=nj&&nj<N){
                        if(cmap[ni][nj]==0 || cmap[ni][nj]==2){
                            q.add(new Virus(ni, nj, curV.time+1));
                        }
                    }
                }
            }

            return;
        }

        for(int i=idx; i<EveryV.size(); i++){
            bit[cnt] = i;
            select(cnt+1, i+1, bit);
        }
    }
}
