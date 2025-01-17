import java.io.*;
import java.util.*;

public class Main {
    static class Point{
        int i;
        int j;
        int distance;

        Point(int i, int j){
            this.i = i;
            this.j = j;
        }

        Point(int i, int j, int d){
            this.i = i;
            this.j = j;
            this.distance = d;
        }
    }

    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, -1, 1, 0};

    static int N, M, K;
    static int[][] map;
    static Point taxi;
    static Point[] client;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 맵 크기
        M = Integer.parseInt(st.nextToken()); // 받을 손님 수
        K = Integer.parseInt(st.nextToken()); // 초기 연료 양

        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken()); // 0은 길, 1은 벽
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

        client = new Point[M];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int si = Integer.parseInt(st.nextToken())-1;
            int sj = Integer.parseInt(st.nextToken())-1;
            int ei = Integer.parseInt(st.nextToken())-1;
            int ej = Integer.parseInt(st.nextToken())-1;

            map[si][sj]=2+i; // map에 손님 번호+2로 표시
            client[i] = new Point(ei, ej); // client배열에 손님 번호를 index로 목적지 정보 저장
        }

        while(true){
            // 1. 제일 가까운 손님을 찾아가서 태운다
            int nextClient = findClient();
            if(nextClient == -1) {
                System.out.println(-1);
                return;
            }
            // 2. 최단 경로로 목적지로 델다준다
            // 3. 목적지에 도착하면 <최단 경로 길이 * 2> 만큼 연료 충전한다
            if(!takeClient(nextClient)) {
                System.out.println(-1);
                return;
            }
            // 4. 1~3 반복
            count++; // 데려다 준 손님 카운트
            if(count==M) {
                System.out.println(K);
                return;
            }
        }
    }

    static int findClient(){
        boolean[][] visited = new boolean[N][N];
        visited[taxi.i][taxi.j]=true;

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(taxi.i, taxi.j, 0));
        while(!q.isEmpty()){
            int qSize = q.size();
            int distance=0;
            int ci=N, cj=N;
            int cnum=-1;
            for(int i=0; i<qSize; i++){
                Point p = q.poll();

                if(map[p.i][p.j]>1){ // 손님 찾았으면 반환
                    if((p.i==ci && p.j<cj) || p.i<ci){
                        ci = p.i;
                        cj = p.j;
                        distance = p.distance;
                        cnum = map[ci][cj]-2;
                    }
//                    K-=p.distance; // 연료도 빼주고
//                    int num = map[p.i][p.j]-2;
//                    map[p.i][p.j]=0; // 손님 위치는 헷갈리지 않게 map에서 제거
//                    taxi.i = p.i; // 택시 이동시키기
//                    taxi.j = p.j; // 택시 이동시키기
//
//                    System.out.println("제일 가까운 손님 : "+num);
//                    System.out.println("손님에게 가고 남은 연료 : "+K);
//
//                    return num; // 반환 값은 손님 번호
                }

                if(p.distance >= K) continue; // 현재 연료로 더 갈 수 없으면 끝

                for(int d=0; d<4; d++){
                    int ni = p.i + di[d];
                    int nj = p.j + dj[d];

                    if(0<=ni&&ni<N && 0<=nj&&nj<N && map[ni][nj]!=1 && !visited[ni][nj]){
                        visited[ni][nj]=true;
                        q.add(new Point(ni, nj, p.distance+1));
                    }
                }
            }

            if(cnum!=-1){
                K-=distance; // 연료도 빼주고
                map[ci][cj]=0; // 손님 위치는 헷갈리지 않게 map에서 제거
                taxi.i = ci; // 택시 이동시키기
                taxi.j = cj; // 택시 이동시키기

                return cnum; // 반환 값은 손님 번호
            }
        }

        return -1;
    }

    static boolean takeClient(int clientNum){
        boolean[][] visited = new boolean[N][N];
        Point dest = client[clientNum];

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(taxi.i, taxi.j, 0));
        while(!q.isEmpty()){
            Point cur = q.poll();

            if(cur.distance >= K) continue;

            for(int d=0; d<4; d++){
                int ni = cur.i + di[d];
                int nj = cur.j + dj[d];

                if(0<=ni&&ni<N && 0<=nj&&nj<N && map[ni][nj]!=1 && !visited[ni][nj]){

                    if(ni==dest.i && nj==dest.j){
                        K += cur.distance+1;
                        taxi.i = dest.i;
                        taxi.j = dest.j;

                        return true;
                    }

                    visited[ni][nj]=true;
                    q.add(new Point(ni, nj, cur.distance+1));
                }
            }
        }

        return false;
    }
}
