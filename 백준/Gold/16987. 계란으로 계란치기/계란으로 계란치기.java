import java.io.*;
import java.util.*;

public class Main{
	static class egg{
		int life;
		int w;
		
		egg(int l, int w){
			this.life = l;
			this.w = w;
		}
	}
	static int N;
	static egg[] eggs;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		eggs = new egg[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i] = new egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		hit(0, eggs, 0);
		
		System.out.println(ans);
	}
	static int ans=0;
	static void hit(int idx, egg[] egs, int cnt) {
		if(idx==N) {
			if(cnt>ans) ans=cnt;
			return;
		}
		
		egg e = egs[idx];
		if(e.life<=0) hit(idx+1, egs, cnt); // 손에 든 계란이 깨진 계란이면 다음 계란으로
		else {
			e = egs[idx];
			boolean used = false;
			for(egg eg : egs) { // 타겟 계란 찾기.
				if(eg!=e) { // 타겟 계란은 본 계란이면 안됌.
					if(eg.life>0) { // 타겟 계란이 안깨져있으면
						used = true;
						int add=0;
						eg.life-=e.w; // 내구도 처리 1.
						if(eg.life<=0) add++;
						e.life-=eg.w; // 내구도 처리 2.
						if(e.life<=0) add++;
						hit(idx+1, egs, cnt+add); // 다음 타겟으로 출발
						eg.life+=e.w; // 내구도 복구 1.
						e.life+=eg.w; // 내구도 복구 2.
					}
				}
			}
			if(!used) {
				hit(N, egs, cnt);
			}
		}
	}
}
