import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()); // 행 : 문자의 길이
        int C = Integer.parseInt(st.nextToken()); // 열 : 문자의 개수

        String[] table = new String[C];
        Arrays.fill(table, "");

        for(int i=0; i<R; i++){
            String row = br.readLine();
            for(int j=0; j<C; j++){
                table[j] += row.charAt(j);
            }
        }

        int up = 1;
        int down = R-1;
        int ans = 0;

        l1 : while(up <= down){
            int mid = (up+down)/2;

            HashSet<String> set = new HashSet<>();
            for(int c=0; c<C; c++){
                if(!set.add(table[c].substring(mid, R))){ // 중복되면?
                    down = mid-1;
                    continue l1;
                }
            }
            // 중복 없으면?
            ans = mid;
            up = mid+1;
        }

        System.out.println(ans);
    }
}
