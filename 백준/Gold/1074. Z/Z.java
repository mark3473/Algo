import java.io.*;
import java.util.*;

public class Main {
    static int N, r, c;
    static int cnt=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int size = (int)Math.pow(2, N);
        dfs(size, r, c);

        System.out.println(cnt);
    }

    static void dfs(int size, int row, int col) {
        if(size==1) return;

        if(row<size/2 && col<size/2){
            dfs(size/2, row, col);
        }
        else if(row<size/2 && col>=size/2){
            cnt += (size*size/4);
            dfs(size/2, row, col-size/2);
        }
        else if(row>=size/2 && col<size/2){
            cnt += size*size/4 * 2;
            dfs(size/2, row-size/2, col);
        }else if(row>=size/2 && col>=size/2){
            cnt += size*size/4 * 3;
            dfs(size/2, row-size/2, col-size/2);
        }
    }
}
