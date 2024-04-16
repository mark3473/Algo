// arraydeque ver.
import java.io.*;
import java.util.*;

public class Main {
	static int M,S, eatcnt;
	static int si, sj;
	static int[][] smell;
	// 좌 좌상 상 우상 우 우하 하 좌하
	static int[] di = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dj = {-1, -1, 0, 1, 1, 1, 0, -1};
	// 상 좌 하 우
	static int[] sdi = {-1,0,1,0};
	static int[] sdj = {0,-1,0,1};
	
	static class fish{
		int i;
		int j;
		int d;
		int time;
		
		fish(int i, int j, int d, int t){
			this.i=i;
			this.j=j;
			this.d=d;
			this.time=t;
		}
	}
	
	static Deque<fish>[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 물고기 수
		S = Integer.parseInt(st.nextToken()); // 마법 횟수
		
		map = new ArrayDeque[4][4];
		Deque<fish>[][] copy = new ArrayDeque[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				map[i][j] = new ArrayDeque<fish>();
				copy[i][j] = new ArrayDeque<>();
			}
		}
		
		smell = new int[4][4];
		
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int fi = Integer.parseInt(st.nextToken())-1;
			int fj = Integer.parseInt(st.nextToken())-1;
			int fd = Integer.parseInt(st.nextToken())-1;
			
			fish f = new fish(fi,fj,fd,0);
			map[fi][fj].add(f);
		}
		
		st = new StringTokenizer(br.readLine());
		si = Integer.parseInt(st.nextToken())-1;
		sj = Integer.parseInt(st.nextToken())-1;
		
		for(int s=1; s<=S; s++) {
			
			// 복제마법 시전
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					for(fish f:map[i][j]) {
						copy[i][j].add(new fish(f.i, f.j, f.d, f.time));
					}
				}
			}
			
			
			// 모든 물고기 이동
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					int mapSize = map[i][j].size();
					for(int ms=0; ms<mapSize; ms++){
						fish f = map[i][j].poll();
						if(f.time==s) {
							map[i][j].add(f);
							continue;
						}
						boolean added=false;
						for(int d=0; d<8; d++) {
							int nd = (f.d-d+8)%8;
							int ni = f.i+di[nd];
							int nj = f.j+dj[nd];
							// 격자 안 + 상어가 없는 칸 + 물고기 냄새가 없는 칸
							if(0<=ni&&ni<4 && 0<=nj&&nj<4 && !(ni==si && nj==sj) && smell[ni][nj]==0) {
								f.i = ni;
								f.j = nj;
								f.d = nd;
								f.time=s;
								map[ni][nj].add(f);
								added=true;
								break;
							}
						}
						if(!added) map[i][j].add(f);
					} 
				}
			}
			
			// 상어 이동
			maxEat = -1;
			v = new boolean[4][4];
			moveShark(si, sj, 0, 0, new int[3]);
			for(int i=0; i<3; i++) {
				si+=sdi[dir2move[i]];
				sj+=sdj[dir2move[i]];
				int ms = map[si][sj].size();
				if(ms>0) {
					smell[si][sj]=3;
//					map[si][sj] = new LinkedList<>();
					while(!map[si][sj].isEmpty()) map[si][sj].remove();
				}
			}
			
			// 냄새 제거
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(smell[i][j]>0) smell[i][j]--;
				}
			}
			
			// 복제마법 완성
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					while(!copy[i][j].isEmpty()) {
						map[i][j].add(copy[i][j].poll());
					}
				}
			}
		}
		
		int ans=0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				ans+=map[i][j].size();
			}
		}
		System.out.println(ans);
	}
	
	static int maxEat;
	static int[] dir2move = new int[3];
	
	static void moveShark(int ci, int cj, int cnt, int fcnt, int[] dir) {
		if(cnt==3) {
			if(fcnt>maxEat) {
				for(int i=0; i<3; i++) {
					dir2move[i] = dir[i];
				}
				maxEat = fcnt;
			}
			return;
		}
		for(int d=0; d<4; d++) {
			int ni = ci+sdi[d];
			int nj = cj+sdj[d];
			if(0<=ni&&ni<4 && 0<=nj&&nj<4) {
				if(!v[ni][nj]) {
					v[ni][nj]=true;
					dir[cnt]=d;
					moveShark(ni, nj, cnt+1, fcnt+map[ni][nj].size(),dir);
					v[ni][nj]=false;
				} else {
					dir[cnt]=d;
					moveShark(ni, nj, cnt+1, fcnt, dir);
				}
			}
		}
	}
}