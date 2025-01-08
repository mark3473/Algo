import java.io.*;
import java.util.*;

public class Main {
    static class Cal{
        int num;
        String command;

        Cal(int n, String c){
            num = n;
            command = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            boolean[] record = new boolean[10000];

            Queue<Cal> q = new LinkedList<>();
            q.add(new Cal(A, ""));
            record[A] = true;
            while(true){
                Cal cur = q.poll();
                // D : 곱하기 2
                int val = (cur.num*2)%10000;
                String comm = cur.command + "D";

                if(!record[val]){
                    record[val]=true;

                    if(val==B) {
                        System.out.println(comm);
                        break;
                    }

                    q.add(new Cal(val, comm));
                }


                // S : 빼기 1
                val = cur.num - 1;
                if(val==-1) val = 9999;
                comm = cur.command + "S";

                if(!record[val]){
                    record[val]=true;

                    if(val==B) {
                        System.out.println(comm);
                        break;
                    }

                    q.add(new Cal(val, comm));
                }

                // L : 왼쪽으로 회전
                int left = cur.num / 1000;
                val = (cur.num - left*1000) * 10 + left;
                comm = cur.command + "L";

                if(!record[val]){
                    record[val]=true;

                    if(val==B) {
                        System.out.println(comm);
                        break;
                    }

                    q.add(new Cal(val, comm));
                }

                // R : 오른쪽으로 회전
                int right = cur.num%10;
                val = (cur.num-right) / 10 + right * 1000;
                comm = cur.command + "R";

                if(!record[val]){
                    record[val]=true;

                    if(val==B) {
                        System.out.println(comm);
                        break;
                    }

                    q.add(new Cal(val, comm));
                }
            }
        }
    }
}
