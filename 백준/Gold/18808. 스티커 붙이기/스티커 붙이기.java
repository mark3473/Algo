import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};
    static BufferedReader br;
    static StringTokenizer st;


    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로
        int K = Integer.parseInt(st.nextToken()); // 스티커

        int[][] map = new int[N][M];

        // 스티커 그리기 정보 저장
        ArrayList<ArrayList<Integer>>[] stickers = new ArrayList[K];
        for(int k=0; k<K; k++){
            stickers[k] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            for(int r=0; r<R; r++){
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<C; c++){
                    if(Integer.parseInt(st.nextToken())==1){
                        // r = 아래로 몇칸, c = 오른쪽으로 몇칸
                        ArrayList<Integer> point = new ArrayList<>();
                        for(int i=0; i<r; i++){
                            point.add(1);
                        }
                        for(int j=0; j<c; j++){
                            point.add(0);
                        }
                        stickers[k].add(point);
                    }
                }
            }
        }

        int ans = 0;

        l2:for(ArrayList<ArrayList<Integer>> stk : stickers){
            for(int t=0; t<4; t++){
                for(int i=0; i<N; i++){
                    l1:for(int j=0; j<M; j++){
//                        if(map[i][j]==0){
                            // 스티커 놓기 시도

                            // 1. 맵 복사하기
                            int[][] copy = new int[N][M];
                            for(int r=0; r<N; r++){
                                for(int c=0; c<M; c++){
                                    copy[r][c] = map[r][c];
                                }
                            }
                            // 붙이기 시도
                            for(ArrayList<Integer> point : stk){
                                if(point.isEmpty()){ // 영점일 때
                                    if(copy[i][j]==0) copy[i][j] = 1;
                                    else continue l1;
                                }
                                else{
                                    int ni = i;
                                    int nj = j;
                                    for(int d:point){
                                        ni+=di[(d+t)%4];
                                        nj+=dj[(d+t)%4];
                                    }
                                    if(0<=ni&&ni<N && 0<=nj&&nj<M && copy[ni][nj]==0){
                                        copy[ni][nj]=1;
                                    }
                                    else{ // 스티커를 못붙인다는 뜻
                                        continue l1;
                                    }
                                }
                            }
                            // 다 붙엿다면??
                            // 붙은 스티커의 칸 수 더하기
                            ans += stk.size();
                            map = copy; // 붙인 결과의 copy을 map에 적용해 최신화
                            continue l2;
                        }
//                    }
                }
            }
        }

        System.out.println(ans);
    }
}
