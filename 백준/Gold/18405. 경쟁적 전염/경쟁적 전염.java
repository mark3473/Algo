import java.io.*;
import java.util.*;

public class Main {
    static class Virus implements Comparable<Virus>{
        int i;
        int j;
        int time;
        int num;

        Virus(int i, int j, int n){
            this.i = i;
            this.j = j;
            this.time = 0;
            this.num = n;
        }

        Virus(int i, int j, int n, int t){
            this.i = i;
            this.j = j;
            this.time = t;
            this.num = n;
        }

        public int compareTo(Virus o){
            return (this.time-o.time)==0? this.num-o.num : this.time-o.time;
        }
    }

    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 크기
        int K = Integer.parseInt(st.nextToken()); // 바이러스 수 (1~K번)

        int[][] map = new int[N][N];
        PriorityQueue<Virus> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0) pq.add(new Virus(i, j, map[i][j]));
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); //s초 뒤
        int X = Integer.parseInt(st.nextToken()) -1;
        int Y = Integer.parseInt(st.nextToken()) -1;

        while(!pq.isEmpty()){
            Virus v = pq.poll();
            if(v.time==S){
                break;
            }

            for(int d=0; d<4; d++){
                int ni = v.i + di[d];
                int nj = v.j + dj[d];
                if(0<=ni&&ni<N && 0<=nj&&nj<N && map[ni][nj]==0){
                    map[ni][nj] = v.num;
                    pq.add(new Virus(ni, nj, v.num, v.time+1));
                }
            }
        }

        System.out.println(map[X][Y]);
    }
}
