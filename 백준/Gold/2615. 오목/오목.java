import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[19][19];
        for(int i=0; i<19; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<19; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][][] visited = new boolean[19][19][4];
        int[] di = {0, 1, 1, 1};
        int[] dj = {1, 0, 1, -1};

        for(int i=0; i<19; i++){
            for(int j=0; j<19; j++){
                if(map[i][j]!=0){
                    for(int d=0; d<4; d++){ // 오른쪽, 아래쪽, 대각쪽 3방향 체크
                        if(visited[i][j][d]) continue;
                        int ni = i;
                        int nj = j;
                        int count = 0;
                        while(0<=ni&&ni<19 && 0<=nj&&nj<19 && map[ni][nj]==map[i][j]){
                            visited[ni][nj][d] = true;
                            count++;
                            ni+=di[d];
                            nj+=dj[d];
                        }

                        if(count==5){
                            System.out.println(map[i][j]);
                            if(d!=3) System.out.println((i+1) + " " + (j+1));
                            else System.out.println((i+5) + " " + (j-3));
                            return;
                        }
                    }
                }
            }
        }

        System.out.println(0);
    }
}
