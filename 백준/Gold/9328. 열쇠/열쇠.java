import java.io.*;
import java.util.*;

public class Main {
    static int[] di = {0, 0, -1, 1};
    static int[] dj = {1, -1, 0, 0};

    static char[][] map;
    static int h, w;

    static boolean[][]v;

    static class Point{
        int i;
        int j;

        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            v = new boolean[h+2][w+2];
            map = new char[h+2][w+2];
            for(int j=0; j<w+2; j++){
                map[0][j] = '.';
                map[h+1][j] = '.';
            }
            for(int i=0; i<h+2; i++){
                map[i][0] = '.';
                map[i][w+1] = '.';
            }

            for(int i=0; i<h; i++){
                String line = br.readLine();
                for(int j=0; j<w; j++){
                    map[i+1][j+1] = line.charAt(j);
                }
            }

            // 획득한 열쇠로 통과 가능한 문은 true로 표시
            String keys = br.readLine();
            int bit = 1<<26;
            if(!keys.equals("0")) {
                for(char c : keys.toCharArray()){
                    bit = (bit | (1<<(c-'a')));
                }
            }

            int cnt = 0;
            Queue<Point> q = new LinkedList<>();
            Map<Integer, Queue<Point>> doors = new HashMap<>();
            q.add(new Point(0,0));
            v[0][0] = true;

            while(!q.isEmpty()){
                Point cur = q.poll();
                for(int d=0; d<4; d++){
                    int ni = cur.i + di[d];
                    int nj = cur.j + dj[d];
                    if(0<=ni&&ni<h+2 && 0<=nj&&nj<w+2 && !v[ni][nj] && map[ni][nj]!='*'){
                        if(map[ni][nj]=='.'){ // 빈곳일 때
                            v[ni][nj] = true;
                            q.add(new Point(ni, nj));
                        } else if(map[ni][nj]=='$'){ // 문서일 때
                            v[ni][nj] = true;
                            cnt++;
                            q.add(new Point(ni, nj));
                        } else if(map[ni][nj]>='a' && map[ni][nj]<='z'){ // 열쇠일 때
                            v[ni][nj] = true;
                            bit = bit | (1<<(map[ni][nj]-'a'));
                            q.add(new Point(ni, nj));

                            if(doors.get(map[ni][nj]-'a')!=null){ // 이전에 도달했던 문의 열쇠면 그 문의 위치를 queue에 넣어주기
                                Queue<Point> dQ = doors.get(map[ni][nj]-'a');
                                while(!dQ.isEmpty()){
                                    q.add(dQ.poll());
                                }
                            }
                        } else if(map[ni][nj]>='A' && map[ni][nj]<='Z'){ // 문일 때
                            v[ni][nj] = true;
                            Queue<Point> dQ = doors.getOrDefault(map[ni][nj]-'A', new LinkedList<>());
                            dQ.add(new Point(ni, nj));
                            doors.put(map[ni][nj]-'A', dQ);

                            if((bit & (1<<(map[ni][nj]-'A'))) == (1<<(map[ni][nj]-'A'))){ // 도달한 문의 열쇠를 갖고 있는 상태이면 queue에 넣기
                                while(!dQ.isEmpty()){
                                    Point door = dQ.poll();
                                    q.add(door);
                                }
                            }
                        }
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }
}
