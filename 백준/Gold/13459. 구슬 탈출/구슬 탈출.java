import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로

        Queue<Integer> balls = new LinkedList<>();
        int EndI, EndJ;
        int RI=0, RJ=0, BI=0, BJ=0;
        boolean[] record = new boolean[10000];

        char[][] map = new char[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'R'){
                    RI = i;
                    RJ = j;
                    map[i][j] = '.';
                }
                else if(map[i][j] == 'B'){
                    BI = i;
                    BJ = j;
                    map[i][j]='.';
                }
                else if(map[i][j] == 'O'){
                    EndI = i;
                    EndJ = j;
                }
            }
        }
        int initBall = RI*1000 + RJ*100 + BI*10 + BJ;
        balls.add(initBall);
        record[initBall] = true;

        int count = 0;
        while(count<10){
            count++;
            int size =balls.size();
            for(int i=0; i<size; i++){
                int full = balls.poll();
                int iri = full/1000; // 빨간 공 i 좌표
                int irj = (full/100)%10; // 빨간 공 j 좌표
                int ibi = (full/10)%10; // 파란 공 i 좌표
                int ibj = full%10; // 파란 공 j 좌표
                for(int d=0; d<4; d++){
                    // 빨간구슬 이동
                    int ri = iri;
                    int rj = irj;
                    int bi = ibi;
                    int bj = ibj;
                    while(map[ri+di[d]][rj+dj[d]]!='#'){
                        ri+=di[d];
                        rj+=dj[d];
                        if(map[ri][rj]=='O') break;
                    }
                    // 파란구슬 이동
                    while(map[bi+di[d]][bj+dj[d]]!='#'){
                        bi+=di[d];
                        bj+=dj[d];
                        if(map[bi][bj]=='O') break;
                    }
                    // 구슬 들어간것들 확인. 파란거 먼저, 그 후 빨간색
                    if(map[bi][bj]=='O') continue;
                    if(map[ri][rj]=='O') {
                        System.out.println(1);
                        return;
                    }
                    // 구슬들 위치가 똑같으면 이동거리 긴 애를 한칸 반대방향으로 이동
                    if(bi==ri && bj==rj){
                        int distanceR = Math.abs(iri-ri + irj-rj);
                        int distanceB = Math.abs(ibi-bi + ibj-bj);
                        if(distanceR<distanceB){
                            bi-=di[d];
                            bj-=dj[d];
                        }
                        else{
                            ri-=di[d];
                            rj-=dj[d];
                        }
                    }

                    // 이동 후의 최종 위치가 이전 기록에 없는지 체크
                    int cur = ri*1000 + rj*100 + bi*10 + bj;
                    if(!record[cur]){
                        record[cur] = true;
                        balls.add(cur);
                    }
                }
            }
        }

        System.out.println(0);
    }
}
