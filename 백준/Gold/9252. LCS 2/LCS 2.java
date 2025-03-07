import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		String a = br.readLine();
		String b = br.readLine();
		
		int asz = a.length();
		int bsz = b.length();
		
		int[][] dp = new int[asz+1][bsz+1];
		
		for(int i=1; i<=asz; i++) {
			for(int j=1; j<=bsz; j++) {
				if(a.charAt(i-1)==b.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1]+1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		int size = dp[asz][bsz];
		int i = asz;
		int j = bsz;
		while(sb.length()<size) {
			if(dp[i][j]==dp[i][j-1]) {
				j--;
				continue;
			}
			if(dp[i][j]==dp[i-1][j]) {
				i--;
				continue;
			}
			sb.append(a.charAt(i-1));
			i--;
			j--;
		}
		System.out.println(size);
		System.out.println(sb.reverse());
	}

}
