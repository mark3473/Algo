import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        int ans = 0;
        Queue<String> q = new LinkedList<>();
        q.add(T);
        while(!q.isEmpty()){
            String cur = q.poll();
            int size = cur.length();
            if(size==S.length()){
                if(cur.equals(S)) {
                    ans=1;
                    break;
                }
                else continue;
            }
            // 1. A로 끝나면 A 제거
            if(cur.charAt(size-1)=='A') q.add(cur.substring(0, size-1));

            // 2. B로 시작하면 B 제거 후 뒤집기
            if(cur.charAt(0)=='B') {
                StringBuffer sb = new StringBuffer(cur.substring(1, size)).reverse();
                q.add(sb.toString());
            }
        }
        System.out.println(ans);
    }
}
