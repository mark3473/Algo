import java.io.*;
import java.util.*;

public class Main {
    // 메모리 초과 발생
    static class Point{
        int num;
        int cnt;

        Point(int n, int c){
            this.num = n;
            this.cnt = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사다리 수
        int M = Integer.parseInt(st.nextToken()); // 뱀 수

        HashMap<Integer, Integer> sadari = new HashMap<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            sadari.put(from, to);
        }

        HashMap<Integer, Integer> snake = new HashMap<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            snake.put(from, to);
        }

        PriorityQueue<Point> q = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        boolean[] visited = new boolean[101];
        q.add(new Point(1, 0));
        visited[1] = true;
        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i=1; i<=6; i++){
                int dest = cur.num + i;
                if(dest > 100) break;
                if(dest == 100) {
                    System.out.println(cur.cnt+1);
                    return;
                }
                if(visited[dest]) continue;
                visited[dest] = true;
                if(sadari.containsKey(dest)){
                    q.add(new Point(sadari.get(dest), cur.cnt+1));
                }
                else if(snake.containsKey(dest)){
                    q.add(new Point(snake.get(dest), cur.cnt+1));
                }
                else{
                    q.add(new Point(dest, cur.cnt+1));
                }
            }
        }
    }
}
