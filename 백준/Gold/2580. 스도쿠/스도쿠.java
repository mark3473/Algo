import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    static int[][] map;
    static ArrayList<Point> q;

    static class Point{
        int i;
        int j;

        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    static boolean[][] ver = new boolean[9][10];
    static boolean[][] hor = new boolean[9][10];
    static boolean[][][] area = new boolean[3][3][10];

    static int size;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[9][9];
        q = new ArrayList<>();

        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                ver[j][map[i][j]] = true;
                hor[i][map[i][j]] = true;
                area[i/3][j/3][map[i][j]] = true;
                if(map[i][j]==0) q.add(new Point(i, j));
            }
        }

        size = q.size();

        if(size==0){
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }
        }
        else {
            fill(0);
        }
    }

    static boolean flag = false;
    static void fill(int cnt){
        if(cnt==size){
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }
            flag = true;
            return;
        }

        Point cur = q.get(cnt);
        for(int num=1; num<10; num++){
            // 사분면 세로줄 가로줄 체크
            if(!hor[cur.i][num] && !ver[cur.j][num] && !area[cur.i/3][cur.j/3][num]){
                hor[cur.i][num] = true;
                ver[cur.j][num] = true;
                area[cur.i/3][cur.j/3][num] = true;
                map[cur.i][cur.j] = num;

                fill(cnt+1);
                if(flag) return;

                hor[cur.i][num] = false;
                ver[cur.j][num] = false;
                area[cur.i/3][cur.j/3][num] = false;
                map[cur.i][cur.j] = 0;
            }
        }
    }
}
