import java.io.*;
import java.util.*;

public class Main {
    static class Room{
        Map<String, Room> childRoom = new HashMap<>();

        void insert(String strs){
            Room room = this;
            String[] foods = strs.split(" ");
            for(String f : foods){
                room.childRoom.putIfAbsent(f, new Room()); // 다음 방 목록에 f가 없으면 새로 만듦
                room = room.childRoom.get(f); // f가 있는 방으로 현재 위치 최신화
            }
        }

        // depth만큼 -- 출력하고 현재 방의 이름 출력
        void print(Room cur, int depth){
            Room room = cur;
            if(room.childRoom != null){
                List<String> list = new ArrayList<>(room.childRoom.keySet());
                Collections.sort(list);
                for(String food : list){
                    for(int d=0; d<depth; d++) System.out.print("--");
                    System.out.println(food);

                    print(room.childRoom.get(food), depth+1);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Room room = new Room();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // 정보 수
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<k; j++){
                sb.append(st.nextToken()).append(" ");
            }

            room.insert(sb.toString());
        }

        room.print(room,0);
    }
}
