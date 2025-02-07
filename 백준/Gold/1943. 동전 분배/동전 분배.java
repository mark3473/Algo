import java.io.*;
import java.util.*;

public class Main {
    static class Coin{
        int price;
        int amount;

        Coin(int p, int a){
            this.price = p;
            this.amount = a;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int t=0; t<3; t++){
            int N = Integer.parseInt(br.readLine()); // 동전 종류 수
            Coin[] coins = new Coin[N];
            int total = 0;
            for(int n=0; n<N; n++){
                st = new StringTokenizer(br.readLine());
                int price = Integer.parseInt(st.nextToken());
                int amount = Integer.parseInt(st.nextToken());
                coins[n] = new Coin(price, amount);
                total+= price*amount;
            }

            if(total%2!=0) sb.append(0).append("\n");
            else{
                total /= 2;
                boolean[][] dp = new boolean[N+1][total+1]; //dp[i][j] = i번째 동전까지 사용했을 때 j원의 가치를 만들 수 있는지 여부
                dp[0][0] = true; // 하나도 안쓰고 0원 만들기 = true
                for(int n=1; n<=N; n++){
                    Coin coin = coins[n-1]; // n번째 동전에 대하여 따지기
                    for(int i=0; i<=total; i++){
                        if(dp[n-1][i]){ // n-1번째 동전으로 i의 가치를 만들 수 있을 때
                            for(int a=0; a<=coin.amount; a++){
                                int sum = i + a*coin.price;
                                if(sum<=total){
                                    dp[n][sum] = true;
                                }
                            }
                        }
                    }
                }

                if(dp[N][total]) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
