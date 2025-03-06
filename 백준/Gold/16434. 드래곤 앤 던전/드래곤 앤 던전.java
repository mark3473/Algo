import java.io.*;
import java.util.*;

public class Main {
    static class Room{
        int type;
        int attack;
        int hp;

        Room(int t, int a, int h){
            this.type = t;
            this.attack = a;
            this.hp = h;
        }
    }

    static Room[] rooms;
    static int N, hATK;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 방 개수
        hATK = Integer.parseInt(st.nextToken()); // 초기 공격력
        long left = 0;
        long right = 0;
        rooms = new Room[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if(t==1){
                right+=(long)a * (long)h;
            }
            rooms[i] = new Room(t, a, h);
        }

        while(left<right){
            long mid = left+(right-left)/2;

            if(check(mid)){ // mid 체력으로 성공하면 더 작은 피로도 확인해보기
                right = mid;
            } else { // mid 체력으로 실패하면 더 큰 피로 확인해야함
                left = mid+1;
            }
        }

        System.out.println(left);
    }

    static boolean check(long hp){
        long cATK = hATK;
        long cHP = hp;

        for(int cur=0; cur<N; cur++){
            if(rooms[cur].type==1){ // 몬스터방
                long turn = rooms[cur].hp/cATK; // 몬스터 피 / 공격력만큼 턴이 진행됌
                if(turn*cATK!=rooms[cur].hp) turn+=1; // 딱 안떨어지면 1 더하기

                cHP-=(turn-1)*rooms[cur].attack; // 내가 공격한 턴 - 1만큼 맞음
                if(cHP<=0) return false;
            }else{ // 포션방
                cHP+=rooms[cur].hp;
                if(cHP>hp) cHP = hp;
                cATK+=rooms[cur].attack;
            }
        }

        return true;
    }
}
