import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String l1 = br.readLine();
        String l2 = br.readLine();
        String l3 = br.readLine();

        int s1 = l1.length();
        int s2 = l2.length();
        int s3 = l3.length();

        int[][][] dp = new int[s1+1][s2+1][s3+1];

        for(int i=1; i<=s1; i++){
            for(int j=1; j<=s2; j++){
                for(int k=1; k<=s3; k++){
                    if(l1.charAt(i-1)==l2.charAt(j-1) && l2.charAt(j-1)==l3.charAt(k-1)){
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    }
                    else {
                        dp[i][j][k] = Math.max(Math.max(dp[i-1][j][k], dp[i][j-1][k]), dp[i][j][k-1]);
                    }
                }
            }
        }

        System.out.println(dp[s1][s2][s3]);
    }
}
