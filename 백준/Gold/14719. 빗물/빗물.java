import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken()); // 전체 세로
        int W = Integer.parseInt(st.nextToken()); // 전체 가로

        st = new StringTokenizer(br.readLine());
        int[] map = new int[W];
        int maxH = 0;
        for(int i=0; i<W; i++){
            map[i] = Integer.parseInt(st.nextToken());
            if(maxH<map[i]) maxH = map[i];
        }

        int leftI = 0;
        int leftH = map[leftI];
        int sum = 0;
        int ans = 0;
        for(int i=1; i<W; i++){
            int curH = map[i];
            if(leftH>curH) sum += curH;
            else {
                ans += leftH * (i-leftI-1);
                ans -= sum;
                sum = 0;
                leftI = i;
                leftH = curH;
            }

            if(curH==maxH) break;
        }

        int rightI = W-1;
        int rightH = map[rightI];
        for(int i=W-2; i>=leftI; i--){
            int curH = map[i];
            if(rightH>curH) sum += curH;
            else {
                ans += rightH * (rightI-i-1);
                ans -= sum;
                sum = 0;
                rightI = i;
                rightH = curH;
            }
        }

        System.out.println(ans);


    }
}
