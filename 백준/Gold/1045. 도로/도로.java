import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] g;
	static int[] parent;
	static class Edge{
		int a;
		int b;
		Edge(int a, int b){
			this.a=a;
			this.b=b;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent=new int[N];
		g = new char[N][N];
		int cnt=0;
		int[] con = new int[N];
		boolean[][] v = new boolean[N][N];
		Deque<Edge> dq = new ArrayDeque<>();
		for(int i=0; i<N; i++)parent[i]=i;
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=i+1; j<N; j++) {
				g[i][j] = s.charAt(j);
				if(s.charAt(j)=='Y') {
					if(union(i,j)) {
						cnt++;
						con[i]++;
						con[j]++;
					} else {
						dq.add(new Edge(i,j));
					}
				}
			}
		}
		
		if(cnt<N-1 || cnt+dq.size()<M) {
			System.out.println(-1);
		}else {
			int left = M-cnt;
			
			while(left>0) {
				Edge e = dq.poll();
				con[e.a]++;
				con[e.b]++;
				left--;
			}
			
			for(int i:con) System.out.print(i+" ");
		}
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		else {
			if(a<=b) parent[b]=a;
			else parent[a]=b;
			return true;
		}
	}
	static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean allconnected(boolean[] c) {
		for(boolean i:c) {
			if(!i) return false;
		}
		return true;
	}
}
