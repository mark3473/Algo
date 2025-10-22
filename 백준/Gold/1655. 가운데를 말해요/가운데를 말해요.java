import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 정수의 수
        PriorityQueue<Integer> small = new PriorityQueue<>((o1, o2) -> o2-o1); // 반띵해서 작은 부분은 역순으로
        PriorityQueue<Integer> big = new PriorityQueue<>(); // 반띵해서 큰 부분은 정순으로

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            if(small.size()==big.size()) small.add(Integer.parseInt(br.readLine()));
            else big.add(Integer.parseInt(br.readLine()));

            if(!small.isEmpty() && !big.isEmpty()){
                if(small.peek() > big.peek()){
                    int tmp = small.poll();
                    big.add(tmp);
                    tmp = big.poll();
                    small.add(tmp);
                }
            }

            sb.append(small.peek()).append("\n");
        }

        System.out.println(sb);
    }
}
