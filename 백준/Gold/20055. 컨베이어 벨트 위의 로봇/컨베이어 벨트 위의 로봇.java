import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 길이
        int K = Integer.parseInt(st.nextToken()); // 종료 조건 : 내구도 0인 칸 수
        int step = 0; // 수행 중인 단계
        int in = 0; // 로봇 올리는 칸 인덱스
        int out = N-1; // 로봇 내리는 칸 인덱스
        int count = 0; // 내구도가 0인 칸 수
//        Queue<Integer> robots = new LinkedList<>();

        int[] belt = new int[N*2];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }
        boolean[] isRobot = new boolean[2*N];

        // 1. 칸 이동 : in, out -1. out에 로봇이 있으면 바로 내리기.
        // 2. 먼저 벨트에 올라간 순서대로 로봇 이동 : N-1칸부터 이동시키기. out에 로봇 있으면 바로 내리기
        // 2-1. 이동하려는 칸에 로봇이 없고 내구도가 1 이상이어야함
        // 2-2. 이동하면 칸 내구도 -1
        // 3. 올리는 위치 칸 내구도가 0이 아니면 로봇 올림 : in 체크
        // 3-1. 올리는 칸 내구도 -1
        // 4. 내구도가 0인 칸이 K개 이상이면 종료 : 내구도가 0되는 칸 생길때마다 count해주기
        while(true){
            step+=1;

            // 1. 회전
//            Systm.out.println(step + " 회전 전");
//            for(boolean b : isRobot){
//                System.out.print(b+" ");
//            }
//            System.out.println();e
//            for(int i : belt){
//                System.out.print(i+" ");
//            }
//            System.out.println();
            if(--in<0) in=2*N-1;
            if(--out<0) out = 2*N-1;
            if(isRobot[out]) isRobot[out] = false;

            // 2. 로봇 이동
//            System.out.println(step+" 회전 후");
//            for(boolean b : isRobot){
//                System.out.print(b+" ");
//            }
//            System.out.println();
//            for(int i : belt){
//                System.out.print(i+" ");
//            }
//            System.out.println();

            int cur = out-1;
            if(cur<0) cur=2*N-1;
            int end = in-1;
            if(end<0) end=2*N-1;
            while(cur!=end){
//                System.out.println(cur);
                int target = (cur+1)%(2*N);
//                System.out.println(target);
                if(isRobot[cur] && !isRobot[target] && belt[target]>0){
                    isRobot[cur] = false;
                    if(target!=out){
                        isRobot[target] = true;
                    }
                    belt[target]--;
                    if(belt[target]==0) count++;
                }
                cur--;
                if(cur<0) cur=2*N-1;
            }
//            int size = robots.size();
//            for(int i=0; i<size; i++){
//                int cur = robots.poll();
//                if(!isRobot[cur]) continue;
//                int target = (cur+1)%(2*N);
//                if(belt[target]!=0 && !isRobot[target]){
//                    belt[target]--;
//                    if(belt[target]==0) count++;
//                    isRobot[cur] = false;
//                    if(target!=out){
//                        isRobot[target] = true;
//                        robots.add(target);
//                    }
//                }
//            }
            // 3. 로봇 올리기
//            System.out.println(step+" 이동 후");
//            for(boolean b : isRobot){
//                System.out.print(b+" ");
//            }
//            System.out.println();
//            for(int i : belt){
//                System.out.print(i+" ");
//            }
//            System.out.println();
//            if(belt[in]>0){
//                isRobot[in] = true;
//                belt[in]-=1;
//                robots.add(in);
//                if(belt[in]==0) count++;
//            }
            if(belt[in]>0 && !isRobot[in]){
                isRobot[in] = true;
                belt[in]-=1;
                if(belt[in]==0) count++;
            }
            // 4. 내구도 체크
//            System.out.println(step+" 올린 후");
//            for(boolean b : isRobot){
//                System.out.print(b+" ");
//            }
//            System.out.println();
//            for(int i : belt){
//                System.out.print(i+" ");
//            }
//            System.out.println();

            if(count>=K) break;
        }
        System.out.println(step);
    }
}
