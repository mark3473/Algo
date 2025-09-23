import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        Queue<String> q = new LinkedList<>();
        q.add(br.readLine());
        while(!q.isEmpty()){
            String str = q.poll();
            int len = str.length();

            // 끝이 0이면 noise임
            if(str.charAt(len-1)=='0') continue;

            // len-1 부터 1이 몇개인지 체크하기
            int point = len-1;
            while(point>=0 && str.charAt(point)=='1'){
                point--;
            }
            // 이제 point가 가리키는 곳에는 0이 있거나 1로만 가득한 noise임
            if(point<0) continue;

            // 1이 1개이면
            if(len-1 - point == 1){
                // 그 다음부터 0 개수 새기
                int point2 = point;
                while(point2>=0 && str.charAt(point2)=='0'){
                    point2--;
                }
                // 그 뒤로 끝까지 0이 2개 이상 있으면 noise임
                if(point-point2>1 && point2<0) continue;
                // 01이면 잠수함
                else if(point2==-1 && point==0) {
                    System.out.println("SUBMARINE");
                    return;
                }
                // 0이 1개이면 01 제거한것 넣기
                else if(point==point2+1){
                    q.add(str.substring(0, point));
                }
                else if(point2==0 /*&& point==1*/){
                    System.out.println("SUBMARINE");
                    return;
                }
                // 0이 여러개 있으면 발견한 1까지 싸그리 없애기
                else {
                    q.add(str.substring(0, point2));
                }
            }
            // 1이 여러개면
            else {
                // 그 다음부터 0 개수 새기
                int point2 = point;
                while(point2>=0 && str.charAt(point2)=='0'){
                    point2--;
                }

                // 그 뒤로 0이 한개만 있거나 쭉 0이 2개 이상 있으면 noise임
                if(point-point2==1 || (point-point2>2 && point2<0)) continue;
                // ㅁ
                else if (point2==0) {
                    System.out.println("SUBMARINE");
                    return;
                }
                // 0이 여러개 있으면 발견한 1까지 싸그리 없애기
                else {
                    q.add(str.substring(0, point2));
                }
            }
        }

        System.out.println("NOISE");
    }
}
