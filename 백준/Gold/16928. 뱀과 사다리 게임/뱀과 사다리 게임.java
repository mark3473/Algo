import java.io.*;
import java.util.*;
// 개선 : 사다리, 뱀 HashMap 통합 / PriorityQueue->Queue / Point 클래스 삭제
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사다리 수
        int M = Integer.parseInt(st.nextToken()); // 뱀 수

        HashMap<Integer, Integer> bridge = new HashMap<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            bridge.put(from, to);
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            bridge.put(from, to);
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[101];
        q.add(1);
        visited[1] = true;

        int ans = 0;
        while(!q.isEmpty()){
            ans++;
            int qSize = q.size();
            for(int i=0; i<qSize; i++){
                int cur = q.poll();
                for(int d=1; d<=6; d++){
                    int dest = cur+d;

                    if(dest>100) break;
                    if(dest==100) {
                        System.out.println(ans);
                        return;
                    }
                    if(visited[dest]) continue;
                    visited[dest] = true;
                    Integer destOfdest = bridge.get(dest);
                    if(destOfdest != null) {
                        q.add(destOfdest);
                        visited[destOfdest] = true;
                    } else {
                        q.add(dest);
                    }
                }
            }
        }
    }
}
