import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] len = new int[3];
		
			len[0] = Integer.parseInt(st.nextToken());
			len[1] = Integer.parseInt(st.nextToken());
			len[2] = Integer.parseInt(st.nextToken());
			
			if(len[0]==0 && len[1]==0 && len[2]==0) break;
			
			Arrays.sort(len);
			
			if(len[2]>=len[1]+len[0]) sb.append("Invalid").append("\n");
			else if(len[2]==len[1] && len[1]==len[0]) sb.append("Equilateral").append("\n");
			else if(len[2]==len[1] || len[1]==len[0]) sb.append("Isosceles").append("\n");
			else sb.append("Scalene").append("\n");
		}
		
		System.out.println(sb);
	}
}
