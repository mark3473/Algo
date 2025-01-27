import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 길이

        long[][][] dp = new long[N+1][10][1<<10]; // dp[i][j][k] = 길이가 i인 수 & 제일 마지막 숫자가 j & 사용한 숫자 bit 정보

        // 길이가 1인 경우에는 그 자체가 계단수이므로 1로 dp 테이블 초기화
        for(int i=1; i<10; i++){
            dp[1][i][1<<i] = 1;
        }

        for(int i=2; i<=N; i++){
            for(int j=0; j<10; j++){ // j : 오른쪽에 새로 붙일 숫자
                for(int b=0; b<1024; b++){ // b : 사용한 숫자 정보 bit
                    int bit = b | (1<<j); // bit : 이번에 j를 사용한 후 갱신된 사용한 숫자 bit 정보

                    if(j==0){
//                        dp[i][j][bit] += dp[i-1][1][b] % 1000000000;
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][b]) % 1000000000;
                    }
                    else if(j==9){
//                        dp[i][j][bit] += dp[i-1][8][b] & 1000000000;
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][b]) % 1000000000;
                    }
                    else{
//                        dp[i][j][bit] += (dp[i-1][j-1][b] + dp[i-1][j+1][b]) % 1000000000;
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][b] + dp[i-1][j-1][b]) % 1000000000;
                    }
                }
            }
        }


        long ans=0;
        for(int i=0; i<10; i++){
//            ans += dp[N][i][1023] % 1000000000;
            ans = (ans + dp[N][i][1023]) % 1000000000;
        }

        System.out.println(ans);
    }
}
