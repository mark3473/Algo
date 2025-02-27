import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            int n = Integer.parseInt(br.readLine());
            int ans = 0;
            for(int i=0; i<= (n/3); i++){
                int rest = n-(3*i); // 3을 1개씩 최대한 넣어보면서
                ans += rest/2 + 1; // 넣은 3만큼을 제외한 값에 2가 쓰이는 횟수 (2가 포함된 식) + 1 (1로만 구성된 식)
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }
}
