import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 1, 0, -1, 0, 1, -1, -1};
    static int[] dj = {1, 0, -1, 0, 0, 1, 1, -1};

    static String[] map = new String[8];
    static String cleanLine = "........";

    static class Point{
        int i;
        int j;
        int step;

        Point(int i, int j, int s){
            this.i = i;
            this.j = j;
            this.step = s;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<8; i++){
            map[i] = br.readLine();
        }

        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(7,0, 0));

        while(!q.isEmpty()){
            Point cur = q.poll();

            if(cur.i == 0) {
                System.out.println(1);
                return;
            }

            for(int d=0; d<8; d++){
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if(0<=ni&&ni<8 && 0<=nj&&nj<8){
                    String line = ni-cur.step<0? cleanLine : map[ni-cur.step];
                    String above = ni-cur.step-1<0? cleanLine : map[ni-cur.step-1];
                    if(line.charAt(nj)=='.' && above.charAt(nj)=='.'){
                        q.add(new Point(ni, nj, cur.step+1));
                    }
                }
            }
        }

        System.out.println(0);
    }
}
