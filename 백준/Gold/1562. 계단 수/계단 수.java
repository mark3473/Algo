import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long[][][] dp = new long[N+1][11][1<<10]; // dp[i][j][k] = 길이가 i, 끝자리 수가 j, k비트 안의 수를 활용. 세 조건을 만족하는 계단수의 개수
		for(int i=1; i<10; i++) {
			dp[1][i][1<<i]=1; // 길이가 1인 수, 즉 i들은 다 1.
		}
		
		for(int i=2; i<N+1; i++) {
			for(int j=0; j<10; j++) {
				for(int k=0; k<1024; k++) {
					// k = 지금까지 사용한 숫자들
					// j = 이번에 뒤에 붙일 숫자
					int bit = k | (1<<j);
					
					if(j==0) {
						//길이가 i인 0로 끝나는, bit내의 수를 사용한 계단수의 수 = 길이가 i-1이고 1로 끝나는 수이자 j(=0)을 붙이기 전인 k만큼의 수만 사용한 계단수
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]) %  1000000000;
					}
					else if(j==9) {
						// 9로 끝나는 계단수 = 8로 끝나는 계단수 (9를 붙이기 전이므로 길이는 -1)
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k]) %  1000000000;
					}
					else {
						// j로 끝나는 계단수 = j+1 혹은 j-1로 끝나는 계단수에 j 붙이기.
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k] + dp[i-1][j-1][k]) %  1000000000;
					}
				}
			}
		}
		
		//최종적으로는 길이가 N이며 모든 수를 쓴 계단수들의 수의 합을 구하면 정답.
		long ans=0;
		for(int j=0; j<10; j++) {
			ans = (ans+dp[N][j][1023])% 1000000000;
		}
		System.out.println(ans);
	}
}

// thanks to
// https://loosie.tistory.com/171