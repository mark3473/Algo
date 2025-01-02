import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 트리의 크기
        int R = Integer.parseInt(st.nextToken()); // 빨간색 개수
        int G = Integer.parseInt(st.nextToken()); // 초록색 개수
        int B = Integer.parseInt(st.nextToken()); // 파란색 개수

        long[][][][] dp = new long[N+1][R+1][G+1][B+1];

        for(int n=0; n<=N; n++){
            for(int r=0; r<=R; r++){
                for(int g=0; g<=G; g++){
                    for(int b=0; b<=B; b++){
                        if(n==0) {
                            dp[n][r][g][b] = 1;
                            continue;
                        }

                        if(r-n>=0){
                            dp[n][r][g][b] += dp[n-1][r-n][g][b];
                        }
                        if(g-n>=0){
                            dp[n][r][g][b] += dp[n-1][r][g-n][b];
                        }
                        if(b-n>=0){
                            dp[n][r][g][b] += dp[n-1][r][g][b-n];
                        }

                        if(n%2==0){
                            int amount = n/2;
                            int combVal = comb(n, amount);
                            if(r-amount>=0 && g-amount>=0){
                                dp[n][r][g][b] += dp[n-1][r-amount][g-amount][b]*combVal;
                            }
                            if(r-amount>=0 && b-amount>=0){
                                dp[n][r][g][b] += dp[n-1][r-amount][g][b-amount]*combVal;
                            }
                            if(b-amount>=0 && g-amount>=0){
                                dp[n][r][g][b] += dp[n-1][r][g-amount][b-amount]*combVal;
                            }
                        }

                        if(n%3==0){
                            int amount = n/3;
                            int combVal = comb(n, amount) * comb(n-amount, amount);
                            if(r-amount>=0 && g-amount>=0 && b-amount>=0){
                                dp[n][r][g][b] += dp[n-1][r-amount][g-amount][b-amount]*combVal;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(dp[N][R][G][B]);
    }

    static int comb(int n, int r){
        return fact(n) / (fact(r)*fact(n-r));
    }

    static int fact(int n){
        if(n==1) return 1;
        return n*fact(n-1);
    }
}
