import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // ab+ = a뒤에 b 복사
        // (ab)+ = ab 복사
        // ab+cd = a뒤에 b 복사 후 cd 붙이기
        // (ab+c)+ = (ab 뒤에 b 복사 후 c 붙이기) 복사
        // a|b = a혹은 b

        // 찾아야 할 조건은 (100+1+ | 01)+
        //  { 10[0..][1..] or 01 }+
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            String string = br.readLine();
            int size = string.length();
            boolean able = false;
            Queue<Integer> q = new LinkedList<>();
            q.add(0);
            while(!q.isEmpty()){
                int cur = q.poll();
                if(cur>=size) {
                    able = true;
                    break;
                }

                if(string.charAt(cur)=='0' && cur+1<size && string.charAt(cur+1)=='1'){
                    q.add(cur+2);
                }
                else if(string.charAt(cur)=='1' && cur+2<size && string.charAt(cur+1)=='0'
                && string.charAt(cur+2)=='0'){
                    // 1이 나올때까지 cur 증가
                    cur+=2;
                    while(cur<size && string.charAt(cur)=='0') cur++;
                    // 10[0..] 뒤에 1이 안나오면 끝
                    if(cur>=size) {
                        able = false;
                        continue;
                    }
                    // 1이 나오면 모든 1 읽을때 까지 cur 증가
                    while(cur<size && string.charAt(cur)=='1') cur++;
                    if(cur==size) {
                        able = true;
                        break;
                    }
                    // 0 나오면 다음에는 무조건 01 이어야하니 cur+1이 범위 내일 때만 add
                    if(cur+1<size) q.add(cur);
                    // 마지막 1의 idx에서 다음 숫자는 최소 1001이므로 cur+2가 범위 내여야함
                    if(string.charAt(cur-2)=='1' && cur+2<size) q.add(cur-1);
                }
                else{
                    able = false;
                }
            }

            if(able) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.println(sb.toString());
    }
}
