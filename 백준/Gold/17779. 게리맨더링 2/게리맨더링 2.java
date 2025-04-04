import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N;
    static int total = 0;
    static int Min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        total=0;
        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int d1=1; d1<N; d1++){
                    for(int d2=1; d2<N; d2++){
                        if(0<=j-d1&&j+d2<N && i+d1+d2<N){
                            check(i,j,d1,d2);
                        }
                    }
                }
            }
        }

        System.out.println(Min);
    }

    static void check(int x, int y, int d1, int d2){
        int[] popul = new int[5];
        // 경계선 체크하기
        boolean[][] border = new boolean[N][N];
        for(int i=0; i<=d1; i++){
            border[x+i][y-i] = true;
            border[x+d2+i][y+d2-i] = true;
        }
        for(int i=0; i<=d2; i++){
            border[x+i][y+i] = true;
            border[x+d1+i][y-d1+i] = true;
        }

        // 지역1 인원수 구하기
        for(int i=0; i<x+d1; i++){
            for(int j=0; j<=y; j++){
                if(border[i][j]) break;
                popul[0]+=map[i][j];
            }
        }
        // 지역2 인원수 구하기
        for(int i=0; i<=x+d2; i++){
            for(int j=N-1; j>y; j--){
                if(border[i][j]) break;
                popul[1]+=map[i][j];
            }
        }
        // 지역3 인원수 구하기
        for(int i=x+d1; i<N; i++){
            for(int j=0; j<y-d1+d2; j++){
                if(border[i][j]) break;
                popul[2]+=map[i][j];
            }
        }
        // 지역4 인원수 구하기
        for(int i=x+d2+1; i<N; i++){
            for(int j=N-1; j>=y-d1+d2; j--){
                if(border[i][j]) break;
                popul[3]+=map[i][j];
            }
        }
        // total에서 지역1~4 인원수 빼기 = 지역5 인원수 구하기
        popul[4] = total;
        for(int i=0; i<4; i++){
            popul[4]-=popul[i];
        }

        // 인원수 정렬
        Arrays.sort(popul);

        Min = Math.min(Min, popul[4]-popul[0]);
    }
}
