import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken()); // 가로
		int W = Integer.parseInt(st.nextToken()); // 세로
		
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로
		
		int n = H/(N+1);
		if(H%(N+1)!=0) n+=1;
		
		int m = W/(M+1);
		if(W%(M+1)!=0) m+=1;
		
		System.out.println(n*m);
	}

}
