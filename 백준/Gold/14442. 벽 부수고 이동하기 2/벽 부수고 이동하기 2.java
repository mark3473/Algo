import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 경찬이형 코드 참고해서 개선 버전
// visited 3차원 배열 말고 부순 벽 2차원 배열 사용
// >> info에 k 생략 가능
// >> 3차원 배열 대신 2차원 배열을 사용
public class Main {
    static class info{
        int i;
        int j;
        int cnt;

        info(int i, int j,int c){
            this.i = i;
            this.j = j;
            this.cnt = c;
        }
    }

    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로
        int K = Integer.parseInt(st.nextToken()); // 부술 수 있는 경우의 수

        int[][] map = new int[N][M];
        int[][] breakCnt = new int[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j) - '0'; // 0이면 이동가능공간, 1이면 벽
                breakCnt[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<info> q = new LinkedList<>();
        q.add(new info(0, 0, 1));
        breakCnt[0][0] = 0;
        while(!q.isEmpty()){
            info cur = q.poll();
            if(cur.i==N-1 && cur.j==M-1){
                System.out.println(cur.cnt);
                return;
            }

            for(int d=0; d<4; d++){
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if(0<=ni&&ni<N && 0<=nj&&nj<M){
                    int newBreak = breakCnt[cur.i][cur.j] + map[ni][nj];
                    if(newBreak<=K && newBreak<breakCnt[ni][nj]){
                        breakCnt[ni][nj] = newBreak;
                        q.add(new info(ni,nj, cur.cnt+1));
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
