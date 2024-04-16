import java.io.*;
import java.lang.reflect.Array;
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
		
		fish(int i, int j, int d){
			this.i=i;
			this.j=j;
			this.d=d;
		}
	}
	
	static ArrayList<fish>[][] map, copy;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 물고기 수
		S = Integer.parseInt(st.nextToken()); // 마법 횟수
		
		map = new ArrayList[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				map[i][j] = new ArrayList<fish>();
			}
		}
		
		smell = new int[4][4];
		
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int fi = Integer.parseInt(st.nextToken())-1;
			int fj = Integer.parseInt(st.nextToken())-1;
			int fd = Integer.parseInt(st.nextToken())-1;
			
			fish f = new fish(fi,fj,fd);
			map[fi][fj].add(f);
		}
		
		st = new StringTokenizer(br.readLine());
		si = Integer.parseInt(st.nextToken())-1;
		sj = Integer.parseInt(st.nextToken())-1;
		
		for(int s=1; s<=S; s++) {
			
			// 복제마법 시전
			copy = new ArrayList[4][4];
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					copy[i][j] = new ArrayList<fish>();
				}
			}
			
			// 모든 물고기 이동
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					for(fish f : map[i][j]) {
						boolean moved = false;
						for(int d=0; d<8; d++) {
							int nd = (f.d-d+8)%8;
							int ni = f.i+di[nd];
							int nj = f.j+dj[nd];
							// 격자 안 + 상어가 없는 칸 + 물고기 냄새가 없는 칸
							if(0<=ni&&ni<4 && 0<=nj&&nj<4 && !(ni==si && nj==sj) && smell[ni][nj]==0) {
								copy[ni][nj].add(new fish(ni, nj, nd));
								moved = true;
								break;
							}
						}
						if(!moved) copy[i][j].add((new fish(f.i, f.j, f.d)));
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
				int ms = copy[si][sj].size();
				if(ms>0) {
					smell[si][sj]=3;
					copy[si][sj].clear();
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
					map[i][j].addAll(copy[i][j]);
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
					moveShark(ni, nj, cnt+1, fcnt+copy[ni][nj].size(),dir);
					v[ni][nj]=false;
				} else {
					dir[cnt]=d;
					moveShark(ni, nj, cnt+1, fcnt, dir);
				}
			}
		}
	}
}