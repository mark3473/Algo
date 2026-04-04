import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 비교 수

        ArrayList<Integer>[] students = new ArrayList[N+1];
        for(int i=1; i<=N; i++) students[i] = new ArrayList<>();
        int[] wayIn = new int[N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            students[A].add(B);
            wayIn[B]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=N; i++){
            if(wayIn[i]==0) q.add(i); // 무조건 앞에 오는 사람들은 다 q에 넣기
        }

        ArrayList<Integer> ans = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur).append(" ");

            for(int next : students[cur]){
                wayIn[next]--;
                if(wayIn[next]==0) q.add(next);
            }
        }

        System.out.println(sb);
    }
}
