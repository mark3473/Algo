import java.io.*;
import java.util.*;

public class Main {
    static class Top{
        int height;
        int idx;

        Top(int i, int h){
            this.idx = i;
            this.height = h;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 탑의 수

        st= new StringTokenizer(br.readLine());

        Stack<Top> stack = new Stack<>();

        for(int n=1; n<=N; n++){
            int h = Integer.parseInt(st.nextToken());

            // stack에서 h보다 큰 것을 발견할때까지 작은 요소들 다뺌
            while(!stack.isEmpty() && stack.peek().height<h) stack.pop(); // 일단 높이 같으면 수신 못한다 가정

            // stack 비었으면 h보다 높은 탑이 없었다는 뜻
            if(stack.isEmpty()) sb.append(0).append(" ");
            // stack이 안비었으면 남아있는 놈에게 레이저 쏨
            else sb.append(stack.peek().idx).append(" ");

            stack.add(new Top(n, h));
        }

        System.out.println(sb);
    }
}
