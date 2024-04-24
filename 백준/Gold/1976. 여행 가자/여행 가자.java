import java.io.*;
import java.util.*;

public class Main{
	static int N;
	static int[] parent;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 도시의 수
		int M = Integer.parseInt(br.readLine()); // 여행계획에 속한 도시의 수
		
		parent = new int[N];
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		
		int[][] g = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				if(Integer.parseInt(st.nextToken())==1) union(i,j);
			}
		}
		
		int[] dest = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			dest[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		String ans = "YES";
		for(int i=0; i<M-1; i++) {
			if(find(dest[i])!=find(dest[i+1])) {
				ans="NO";
				break;
			}
		}
		System.out.println(ans);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return;
		if(a>=b) parent[a]=b;
		else parent[b]=a;
	}
	
	static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a] = find(parent[a]);
		
	}
}
