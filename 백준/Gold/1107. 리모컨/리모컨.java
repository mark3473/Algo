import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 목표 채널
        int M = Integer.parseInt(br.readLine()); // 고장난 버튼 수
        boolean[] able = new boolean[10];
        Arrays.fill(able, true);
        StringTokenizer st;
        if(M>0) {
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++){
                able[Integer.parseInt(st.nextToken())] = false;
            }
        }

        int ans = Math.abs(100-N);
        for(int i=0; i<1000000; i++){
            // i번 채널에 숫자 패드로 갈 수 있는지 확인
            if(i<10 && !able[i]) continue;
            boolean flag = true;
            int cnt = i==0? 1:0;
            for(int j = i; j>0; j/=10){
                if(!able[j%10]) {
                    flag = false;
                    break;
                }
                cnt++; // 자리 수 = 누를 버튼 수
            }

            if(flag){ // 갈 수 있다면
                ans = Math.min(ans, cnt+Math.abs(i-N));
            }
        }

        System.out.println(ans);
    }
}
