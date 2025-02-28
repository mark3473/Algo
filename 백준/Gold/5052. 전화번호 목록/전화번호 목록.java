import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            int n = Integer.parseInt(br.readLine());
            PriorityQueue<String> pq = new PriorityQueue<>();
            for(int i=0; i<n; i++){
                pq.add(br.readLine());
            }

            String prev = pq.poll();
            int size = sb.length();
            while(!pq.isEmpty()) {
                String cur = pq.poll();
                if(cur.startsWith(prev)){
                    sb.append("NO").append("\n");
                    break;
                }
                prev = cur;
            }
            if(size!=sb.length()) continue;
            else sb.append("YES").append("\n");
        }

        System.out.println(sb.toString());
    }
}

