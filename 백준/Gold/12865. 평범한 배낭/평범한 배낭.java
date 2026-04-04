import java.io.*;
import java.util.*;

public class Main {
    static class Thing{
        int weight;
        int value;

        Thing(int w, int v){
            this.weight = w;
            this.value= v;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 물품 수
        int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        int[] bag = new int[K+1];
        Thing[] things = new Thing[N+1];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            things[i] = new Thing(W, V);
        }

        for(int i=0; i<N; i++){
            Thing t = things[i];
            for(int k=K; k>=t.weight; k--){
                bag[k] = Math.max(bag[k], bag[k-t.weight]+t.value);
            }
        }

        System.out.println(bag[K]);
    }
}
