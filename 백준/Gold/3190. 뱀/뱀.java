import java.io.*;
import java.util.*;

public class Main {
    // 우, 하, 좌, 상
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 보드 크기
        int[][] map = new int[N][N];
        map[0][0] = 1;

        int K = Integer.parseInt(br.readLine()); // 사과 개수
        StringTokenizer st;
        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;
            map[row][col] = 2; // 2은 사과
        }

        int sd = 0; // 처음에는 우측보고 있음
        Deque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[] {0, 0});
        int L = Integer.parseInt(br.readLine());
        Queue<int[]> moves = new LinkedList<>();
        for(int l=0; l<L; l++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            if(st.nextToken().equals("L")) moves.add(new int[] {t, -1});
            else moves.add(new int[] {t, 1});
        }

        int ans=0;
        while(true){
            ans++;

            int[] head = snake.pollLast();
            int si = head[0]+di[sd];
            int sj = head[1]+dj[sd];

            // 이동했는데 범위 내이고 몸 칸이 아니면
            if(0<=si&&si<N && 0<=sj&&sj<N && map[si][sj]!=1){
                //head와 새로 이동한 칸 넣기
                snake.add(head);
                snake.add(new int[] {si, sj});

                // 사과를 안먹었으면 꼬리칸 제거
                if(map[si][sj]!=2) {
                    int[] tail = snake.pollFirst();
                    map[tail[0]][tail[1]] = 0;
                }

                map[si][sj] = 1;
            }
            else break;

            // 시간 증가 및 뱀 방향 전환
            int[] m = moves.peek();
            if(m!=null && m[0]==ans){
                sd = (sd+m[1]+4)%4;
                moves.poll();
            }
        }

        System.out.println(ans);
    }
}
